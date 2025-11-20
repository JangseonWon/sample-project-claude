package com.gcgenome.lims.service

import com.gcgenome.lims.entity.Request
import com.gcgenome.lims.entity.Sample
import com.gcgenome.lims.entity.Service
import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.time.LocalDate
import java.util.function.BiFunction

@Component
class Dao(private val template: R2dbcEntityTemplate, private val client: DatabaseClient) {
    fun findById(sample: Long): Mono<Sample> = template.selectOne (Query.query (Criteria.where("id").`is`(sample)), Sample::class.java)
    private val MAPPING_FUNCTION: BiFunction<Row, RowMetadata, Sample> = BiFunction<Row, RowMetadata, Sample> { row, meta ->
        Sample(
            id=row["sample", BigInteger::class.java]!!.longValueExact(),
            organization=row["organization", String::class.java]!!,
            organizationName = row["organization_name", String::class.java]!!,
            sampleType = row["sample_type", String::class.java],
            age = row["age", java.lang.Short::class.java]?.toInt(),
            patientName = row["patient_name", String::class.java]!!,
            patientCode = row["patient_code", String::class.java],
            mrn = row["mrn", String::class.java],
            sex = row["sex", String::class.java],
            birth = row["birth", LocalDate::class.java],
            info = row["remark", String::class.java]
        )
    }
    fun findSiblings(id: Long): Flux<Sample> = client.sql(
        "SELECT sibling.* FROM panel.sample000 s, panel.sample000 sibling " +
                "WHERE s.organization = sibling.organization AND s.mrn = sibling.mrn AND s.patient_name = sibling.patient_name " +
                "AND s.mrn is not null and s.mrn != '' AND s.sample = :id")
        .bind("id", id)
        .map(MAPPING_FUNCTION).all()

    fun findRequest(id: Long): Flux<Request> = template.select(Request::class.java)
        .matching(Query.query(Criteria.where("sample").`is`(id)))
        .all()

    fun findServices(id: Long): Flux<Service> = client.sql(
        "SELECT s.id, s.name FROM public.request r, public.service s WHERE r.service = s.id AND r.sample = :id"
    ).bind("id", id).fetch().all()
        .map { row ->
            val id = row.get("id").toString()
            val name = row.get("name").toString()
            Service(id, name)
        }
    fun findService(code: String): Mono<Service> =template.select(Service::class.java)
        .matching(Query.query(Criteria.where("id").`is`(code)))
        .one()
}