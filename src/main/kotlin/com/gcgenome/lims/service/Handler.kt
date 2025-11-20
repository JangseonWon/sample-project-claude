package com.gcgenome.lims.service

import com.gcgenome.lims.SecurityContextRepository
import com.gcgenome.lims.dto.Organization
import com.gcgenome.lims.dto.Patient
import com.gcgenome.lims.dto.Request
import com.gcgenome.lims.dto.Sample
import com.gcgenome.lims.test.*
import com.gcgenome.lims.test.HasCategory.Category
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.function.Function
import java.util.stream.Collectors

@Service
class Handler(private val dao: Dao, private val testRepo: TestRepository) {
    data class Quintuple<out A, out B, out C, out D, out E>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E
    )
    fun findSampleById(sample: Long): Mono<Sample> = ReactiveSecurityContextHolder.getContext().zipWith(dao.findById(sample)).map {
        val context = it.t1
        val smp = it.t2
        val org = Organization(smp.organization, smp.organizationName)
        val (patientName, mrn, patientCode, age, birth) = if(context.authentication.authorities.contains(SecurityContextRepository.Companion.RoleManager)) Quintuple(smp.patientName, smp.mrn, smp.patientCode, smp.age, smp.birth)
        else Quintuple("*", "*", "*", null, null)
        val pat = Patient(org, mrn, patientName, patientCode, smp.sex, age, birth)
        Sample(smp.id, pat, smp.sampleType).apply {

        }
    }
    fun findRequestById(sample: Long, service: String): Mono<Request> = findSampleById(sample)
        .zipWith(dao.findRequest(sample).filter {
            it.service == service }.single())
        .map {
            val request = it.t2
            Request(
                it.t1,
                request.service,
                request.dateRequest?.toLocalDate(),
                request.dateReception?.toLocalDate(),
                request.dateDue?.toLocalDate(),
                request.dateDuePublish?.toLocalDate(),
                request.info,
                request.register,
                request.cancel,
                request.delete
            )
        }
    fun findSiblings(sample: Long): Flux<Sample> = ReactiveSecurityContextHolder.getContext().flatMapMany { context ->
        dao.findSiblings(sample).map { smp->
            val (patientName, mrn, patientCode, age, birth) = if(context.authentication.authorities.contains(SecurityContextRepository.Companion.RoleManager)) Quintuple(smp.patientName, smp.mrn, smp.patientCode, smp.age, smp.birth)
            else Quintuple("*", "*", "*", null, null)
            val org = Organization(smp.organization, smp.organizationName)
            val pat = Patient(org, mrn, patientName, patientCode, smp.sex, age, birth)
            Sample(smp.id, pat, smp.sampleType)
        }
    }
    private val services: Map<String, Any> = (
            RareDiseasePanel.values() +
                    SingleGenePanel.values() +
                    SingleGenePanelWithMlpa.values() +
                    GenePlusPanel.values() +
                    GenomeScreen.values() +
                    BloodCancerPanel.values() +
                    SolidTumorPanel.values() +
                    NonTSO.values() +
                    Sanger.values() +
                    Cancerch.values() +
                    AlloSeq.values() +
                    Guardant.values() +
                    Hrd.values() +
                    Mrd.values() +
                    Wes.values() +
                    Des.values() +
                    WesWithSingleGene.values() +
                    `FLT3-ITD`.values() +
                    Ballondor.values() +
                    ClonalHematopoiesisPanel.values() +
                    ClinicalTest.values())
        .stream().collect(Collectors.toMap(HasCode::code, Function.identity()))
    fun findServices(sample: Long): Flux<Any> = dao.findRequest(sample).flatMap {
        if(services[it.service]!=null) Mono.justOrEmpty(services[it.service]!!)
        else findServiceFromHandbook(it.dateRequest?:LocalDateTime.now(), it.service).
        switchIfEmpty(dao.findService(it.service).map { service -> mapOf(
            "code" to service.id,
            "name" to service.name
        ) })
    }
    private fun findServiceFromHandbook(date: LocalDateTime, code: String): Mono<Any> =
        testRepo.findByCodeLike("%-$code")
            .filter { it.effectiveDate <= date }
            .filter { it.expiryDate == null || it.expiryDate > date }
            .singleOrEmpty().map {
                mapOf(
                    "code" to code,
                    "name" to it.name
                )
            }
    fun subjects(sample: Long, service: String): Flux<String> {
        val interpretation1Panels: List<HasCode> =
            GenomeScreen.values() +
                    Des.values() +
                    NonTSO.values() +
                    RareDiseasePanel.values().filter { it.category() == Category.Cancer } +
                    SingleGenePanel.values().filter { it.category() == Category.Cancer } +
                    GenePlusPanel.values().filter { it.category() == Category.Cancer } +
                    ClonalHematopoiesisPanel.values()

        val interpretation2Panels =
            RareDiseasePanel.values() +
                    Wes.values() +
                    WesWithSingleGene.values() +
                    SingleGenePanel.values() +
                    GenePlusPanel.values() +
                    SingleGenePanelWithMlpa.values() +
                    BloodCancerPanel.values() +
                    SolidTumorPanel.values() +
                    Hrd.values()


        val etcPanels = `FLT3-ITD`.values() + Ballondor.values()

        return when {
            interpretation1Panels.any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(panelForInterpretation1())
            interpretation2Panels.any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(panelForInterpretation2())
            Mrd.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(mrd(sample, service))
            Sanger.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(sanger(sample, service))
            Cancerch.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(cancerch(sample, service))
            AlloSeq.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(alloseq(sample, service))
            Guardant.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(outsourcing(sample, service))
            ClinicalTest.values().any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(outsourcing(sample, service))
            etcPanels.any { it.code().equals(service, ignoreCase = true) } -> Flux.fromArray(
                arrayOf(
                    "/panel-service/samples/request.html",
                    "/panel-service/samples/interpretation.html",
                    "/panel-service/samples/report.html"
                )
            )
            else -> Flux.fromArray(
                arrayOf(
                    "/panel-service/samples/request.html",
                    "/worklist-service/sample.html",
                    "/panel-service/samples/report.html"
                )
            )
        }
    }

    private fun panelForInterpretation1(): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/sequencing-service/sample.html",
            "/panel-service/samples/analysis.html",
            "/panel-service/snv2.html",
            "/panel-service/samples/interpretation.html",
            "/panel-service/samples/report.html"
        )

    private fun panelForInterpretation2(): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/sequencing-service/sample.html",
            "/panel-service/samples/analysis.html",
            "/panel-service/snv2.html",
            "/panel-service/interpretation2.html",
            "/panel-service/samples/report.html",
            "/panel-service/samples/interpretation.html"
        )

    private fun mrd(sample: Long, service: String): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/panel-service/samples/analysis.html",
            "/panel-service/interpretation2.html",
            "/panel-service/samples/report.html"
        )

    private fun sanger(sample: Long, service: String): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/panel-service/samples/interpretation.html",
            "/panel-service/samples/report.html"
        )

    private fun cancerch(sample: Long, service: String): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/sequencing-service/sample.html",
            "/avoid-service/analysisInfo.html",
            "/avoid-service/reportInfo.html"
        )

    private fun alloseq(sample: Long, service: String): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/worklist-service/sample.html",
            "/sequencing-service/sample.html",
            "/alloseq-service/analysisInfo.html",
            "/alloseq-service/reportInfo.html"
        )

    private fun outsourcing(sample: Long, service: String): Array<String> =
        arrayOf(
            "/panel-service/samples/request.html",
            "/outsourcing-service/reportInfo.html"
        )
}
