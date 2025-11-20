package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.PanelAnalysisMethod
import com.gcgenome.lims.test.method.PanelAnalysisMethod.Companion.WesAnalysisMethod

class Des(
    val code: String,
    val name: String,
    val serialGroup: String = "DES",
    val title: String = "$name 결과보고서",
    val method: PanelAnalysisMethod = WesAnalysisMethod(
        panel = "DES (Sequence analysis of 5,870 Mendelian genes)",
        guideline = listOf(
            "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 Celemics G-Mendeliome DES Panel을 이용하여 총 5,870개 유전자의 약 80,962개의 표적 exon 및 839개의 SNV 및 Indel 을 capture하고 DNBSEQ-G400 (MGI) 장비의 2 x 100 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
            "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
        ),
        limitation =  "본 검사로 검출에 제한이 있는 유전적 이상의 존재 가능성을 배제할 수 없으므로, 환자에서 질환 관련 변이가 검출되지 않았더라도 유전 질환 가능성을 배제할 수 없습니다. 또한 현재의 지식으로는 인식이 불가능한 유전자 변이가 존재할 가능성도 있으며 본 검사는 현재까지 연구용 검사입니다. 환자의 질환 관련 변이의 genome 영역이 capture 되지 않거나 낮은 품질로 인해 충분히 염기서열분석이 되지 않을 수 있습니다. 다인자성 질환과 반복염기서열의 증가, 비정상적 DNA 메틸화 및 기타 기전에 의한 유전 질환은 본 검사에서 확인이 어렵습니다. 또한 본 검사는 모자이시즘, 염색체 구조적 이상 및 20bp 이상의 삽입 및 결손은 정확한 검출이 어렵습니다. 일부 유전자들은 최적의 데이터 결과를 얻기 어려운 염기서열 특징(예: 반복서열, 상동성, 높은 GC 함량 등)을 가지고 있으며 해당 영역의 변이는 안정적으로 검출되지 못할 수 있습니다."
    ),
    val nationalInsurance: Boolean = false,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr,
    val disposed: Boolean = true,
) : HasCode, HasCategory, HasSerialGroup, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.DES
    val interpretationCategory: Interpretable.Category = Interpretable.Category.DES
    val reportCategory: Reportable.Category = Reportable.Category.DES
    override fun code() = code
    override fun category() = category
    override fun serialGroup(): String = serialGroup
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun title() = title

    companion object {
        private val GUIDELINE_ENUS = listOf(
            "Genomic DNA was extracted from EDTA whole blood and all the exons of 5,870 genes were captured using Celemics G-Mendeliome DES Panel. Sequencing was " +
                    "performed on DNBSEQ-G400 (MGI) platform generating 2 × 100 bp paired-end reads. The DNA sequence reads were aligned to reference sequence based on public " +
                    "human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants.",
            "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic " +
                    "variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Depending on the " +
                    "results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass internal QC criteria are not " +
                    "validated by Sanger sequencing."
        )
        private val LIMITATION_ENUS = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable with " +
                "this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently sequenced with low " +
                "quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, abnormal DNA methylation, " +
                "and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal aberrations, and deletions/insertions " +
                "of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, rare polymorphisms) that may result in " +
                "suboptimal data, and variants in those regions may not be reliably identified."
        private val N003 = Des(code = "N003", name = "DES", title = "Diagnostic Exome Sequencing Test 결과보고서")
        private val N085 = Des(code = "N085", name = "DES (영문)", title = "Diagnostic Exome Sequencing Test 결과보고서",
            method = WesAnalysisMethod(
                guideline = GUIDELINE_ENUS,
                limitation = LIMITATION_ENUS
            )
        )
        private val N131 = Des(code = "N131", name = "DES Trio(Proband)")
        private val N132 = Des(code = "N132", name = "DES Trio(부)")
        private val N133 = Des(code = "N133", name = "DES Trio(모)")
        private val ON003 = Des(code = "ON003", name = "Diagnostic Exome Sequencing (DES)", title = "Diagnostic Exome Sequencing Test Report",
            method = WesAnalysisMethod(
                guideline = GUIDELINE_ENUS,
                limitation = LIMITATION_ENUS
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON131 = Des(code = "ON131", name = "DES Trio(Proband)", title = "DES Trio(Proband) Test Report",
            method = WesAnalysisMethod(
                guideline = GUIDELINE_ENUS,
                limitation = LIMITATION_ENUS
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON132 = Des(code = "ON132", name = "DES Trio(Father)", title = "DES Trio(Father) Test Report",
            method = WesAnalysisMethod(
                guideline = GUIDELINE_ENUS,
                limitation = LIMITATION_ENUS
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON133 = Des(code = "ON133", name = "DES Trio(Mother)", title = "DES Trio(Mother) Test Report",
            method = WesAnalysisMethod(
                guideline = GUIDELINE_ENUS,
                limitation = LIMITATION_ENUS
            ),
            i18n = Reportable.I18N.EnUs
        )
        fun values() = listOf(
            N003, N085,
            N131, N132, N133,
            ON003,
            ON131, ON132, ON133
        )
        val GENE_INCIDENTAL_FINDINGS = arrayOf(
            "ACTA2","ACTC1","ACVRL1","APC","APOB","ATP7B","BAG3","BMPR1A","BRCA1","BRCA2","BTD","CACNA1S","CALM1",
            "CALM2", "CALM3","CASQ2","COL3A1","DES","DSC2","DSG2","DSP", "ENG","FBN1","FLNC","GAA","GLA","HFE",
            "HNF1A","KCNH2", "KCNQ1","LDLR","LMNA","MAX","MEN1","MLH1","MSH2","MSH6","MUTYH","MYBPC3","MYH11",
            "MYH7","MYL2","MYL3","NF2", "OTC","PALB2","PCSK9","PKP2","PMS2","PRKAG2","PTEN","RB1","RBM20","RET",
            "RPE65","RYR1","RYR2","SCN5A", "SDHAF2", "SDHB","SDHC","SDHD","SMAD3","SMAD4","STK11","TGFBR1","TGFBR2",
            "TMEM127","TMEM43","TNNC1","TNNI3","TNNT2", "TP53","TPM1","TRDN","TSC1","TSC2","TTN","TTR","VHL","WT1"
        )
    }
}