package com.gcgenome.lims.test.method

import com.gcgenome.lims.test.IsPanelAnalysis

data class PanelAnalysisMethod (
    val panel: String,
    val region: String,
    val probe: String,
    val sequencing: String,
    val reference: String,
    val pipeline: String,
    val abbreviation: String = "",
    val subpanel: String = "",
    val limitation: String = "",
    val guideline: List<String> = emptyList()
): IsPanelAnalysis {
    override fun panel() = panel
    override fun region() = region
    override fun probe() = probe
    override fun sequencing() = sequencing
    override fun reference() = reference
    override fun pipeline() = pipeline
    override fun abbreviation() = abbreviation
    override fun subpanel() = subpanel
    fun limitation() = limitation
    fun guideline() = guideline
    companion object {
        fun CancerAnalysisMethod(
            panel: String,
            region: String,
            probe: String,
            sequencing: String = "Sequencing by synthesis (Illumina)",
            reference: String = "GRCh37/hg19",
            pipeline: String ="BI_GRM v2.2 (Alignment: BWA, Variant calling: GATK)",
            limitation: String = "본 검사는 2개 이상의 exon에 걸쳐서 발생한 large deletion/duplication은 대부분 검출 가능하나, single exon deletion/duplication, deep intronic mutation, repeat expansion, imprinting defect, genomic rearrangement, low-level mosaicism 및 염색체 레벨의 copy number variation 검출은 제한적입니다. Target region이 capture되지 않았을 가능성도 있으며, homologous region, GC-rich region, low coverage region이 존재하는 유전자 혹은 exon의 경우 변이 검출 정확도가 떨어질 수 있습니다."
        ) = PanelAnalysisMethod(panel, region, probe, sequencing, reference, pipeline, limitation)
        fun RareDiseaseAnalysisMethod(
            panel: String,
            region: String,
            probe: String,
            sequencing: String = "Sequencing by synthesis (Illumina)",
            reference: String = "GRCh37/hg19",
            pipeline: String = "BI_GRM v2.2 (Alignment: BWA, Variant calling: GATK)",
            limitation: String = "본 검사는 2개 이상의 exon에 걸쳐서 발생한 large deletion/duplication은 대부분 검출 가능하나, single exon deletion/duplication, deep intronic mutation, repeat expansion, imprinting defect, genomic rearrangement, low-level mosaicism 및 염색체 레벨의 copy number variation 검출은 제한적입니다. Target region이 capture되지 않았을 가능성도 있으며, homologous region, GC-rich region, low coverage region이 존재하는 유전자 혹은 exon의 경우 변이 검출 정확도가 떨어질 수 있습니다."
        ) = PanelAnalysisMethod(panel, region, probe, sequencing, reference, pipeline, limitation)
        fun WesAnalysisMethod(
            panel: String = "WES(Sequence analysis of whole exome of human genes)",
            region: String = "",
            probe: String = "",
            sequencing: String = "",
            reference: String = "",
            pipeline: String = "",
            limitation: String = "본 검사로 검출에 제한이 있는 유전적 이상의 존재 가능성을 배제할 수 없으므로, 환자에서 질환 관련 변이가 검출되지 않았더라도 유전 질환 가능성을 배제할 수 없습니다. 또한 현재의 지식으로는 인식이 불가능한 유전자 변이가 존재할 가능성도 있으며 본 검사는 현재까지 연구용 검사입니다. 환자의 질환 관련 변이의 genome 영역이 capture 되지 않거나 낮은 품질로 인해 충분히 염기서열분석이 되지 않을 수 있습니다. 다인자성 질환과 반복염기서열의 증가, 비정상적 DNA 메틸화 및 기타 기전에 의한 유전 질환은 본 검사에서 확인이 어렵습니다. 또한 본 검사는 모자이시즘, 염색체 구조적 이상 및 20bp 이상의 삽입 및 결손은 정확한 검출이 어렵습니다. 일부 유전자들은 최적의 데이터 결과를 얻기 어려운 염기서열 특징(예: 반복서열, 상동성, 높은 GC 함량 등)을 가지고 있으며 해당 영역의 변이는 안정적으로 검출되지 못할 수 있습니다.",
            guideline: List<String> = listOf(
                "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 xGen™ Exome Hybridization Panel 을 이용하여 전체 exon을 capture하여 Novaseq 6000DX 장비의 2 x 150 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
                "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
            ),
        ) = PanelAnalysisMethod(
            panel = panel,
            region = region,
            probe = probe,
            sequencing = sequencing,
            reference = reference,
            pipeline = pipeline,
            limitation = limitation,
            guideline = guideline
        )
        fun DgsAnalysisMethod(
            panel: String = "DGS (Diagnostic Genome Sequencing)",
            region: String = "",
            probe: String = "",
            sequencing: String = "",
            reference: String = "",
            pipeline: String = "",
            limitation: String = "본 검사로 검출에 제한이 있는 유전적 이상의 존재 가능성을 배제할 수 없으므로, 환자에서 질환 관련 변이가 검출되지 않았더라도 유전질환 가능성을 배제할 수 없습니다. 또한 현재의 지식으로는 인식이 불가능한 유전자 변이가 존재할 가능성도 있으며 본 검사는 현재까지 연구용 검사입니다. 환자의 질환 관련 변이의 genome 영역이 capture 되지 않거나 낮은 품질로 인해 충분히 염기서열분석이 되지 않을 수도 있습니다. 다인자성 질환과 반복염기서열의 증가, 비정상적 DNA 메틸화 및 기타 기전에 의한 유전 질환은 본 검사에서 검출되지 않을 수 있습니다. 또한 본 검사는 모자이시즘, 염색체 구조적 이상 및 20bp 이상의 삽입 및 결손은 정확한 검출이 어려울 수 있습니다. 일부 유전자들은 최적의 데이터 결과를 얻기 어려운 염기서열 특징(예: 반복서열, 상동성, 높은 GC함량 등)을 가지고 있으며 해당 영역의 변이는 안정적으로 검출되지 못할 수 있습니다.",
            guideline: List<String> = listOf(
                "검사 대상자의 EDTA 전혈에서 DNA를 추출한 후 일루미나의 NovaSeq6000을 이용하여 paired-end reads 방법으로 염기서열 분석을 시행하였습니다. GRCh37/UCSC hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였습니다. 자체 설정한 생물학적 분석 기준에 따라, 서열 변이를 확인하기 위해 정보가 분류 및 분석되었습니다. CNV calling은 parliament2 파이프라인을 기반으로 합니다.",
                "변이 검출 및 해석은 검사에 포함된 유전자들의 coding exon과 인접 intron 영역을 주로 분석하나, 특정 질환 후보 유전자의 경우나 상염색체 열성 유전 질환에서 하나의 Pathogenic variant가 발견된 경우 두번째 변이를 찾기 위해 전체 유전자 영역으로 확장될 수 있습니다. 검사에서 발견된 변이는 ACMG/AMP guidelines(Richards et al, 2015)에 따라 분류됩니다. 환자의 임상 증상과 관련된 Phathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 번이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 내부 QC기준에 따라 Sanger sequencing을 생략할 수 있습니다."
            )
        ) = PanelAnalysisMethod(
            panel = panel,
            region = region,
            probe = probe,
            sequencing = sequencing,
            reference = reference,
            pipeline = pipeline,
            limitation = limitation,
            guideline = guideline
        )
    }
}