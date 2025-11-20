package com.gcgenome.lims.test

import com.gcgenome.lims.test.Reportable.I18N

data class Hrd (
    val code: String,
    val name: String,
    val serialGroup: String = "HRD",
    val tiers: List<String> = listOf("BRCA", "TIER1", "TIER2"),
    val essential: List<Exon>,
    val selective: List<Exon>,
    val nationalInsurance: Boolean = false,
    val i18n: I18N = I18N.KoKr,
): HasCode, HasName, HasCategory, HasSerialGroup, ContainsExons, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.HRD
    val interpretationCategory: Interpretable.Category = Interpretable.Category.HRD
    val reportCategory: Reportable.Category = Reportable.Category.HRD
    val groups = intArrayOf(1, 2)
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun title() = name
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory

    override fun groups() = groups
    override fun exons(group: Int): List<Exon> {
        require(group in groups())
        return when(group) {
            1       -> essential
            else    -> selective
        }
    }
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): I18N = i18n


    companion object {
        private val N140 = Hrd(code="N140", name="상동 재조합 결핍 검사(그린플랜 HRD)",
            essential = listOf(
                Exon("BRCA1","All coding region","NM_007294"),
                Exon("BRCA2","All coding region","NM_000059"),
            ),
            selective = listOf(
                Exon("ATM","All coding region","NM_000051"),
                Exon("BARD1","All coding region","NM_000465"),
                Exon("BRIP1","All coding region","NM_032043"),
                Exon("CDK12","All coding region","NM_016507"),
                Exon("CHEK1","All coding region","NM_001114121"),
                Exon("CHEK2","All coding region","NM_007194"),
                Exon("FANCC","All coding region","NM_000136"),
                Exon("FANCD2","All coding region","NM_033084"),
                Exon("FANCE","All coding region","NM_021922"),
                Exon("FANCF","All coding region","NM_022725"),
                Exon("FANCG","All coding region","NM_004629"),
                Exon("FANCI","All coding region","NM_001113378"),
                Exon("FANCL","All coding region","NM_018062"),
                Exon("FANCM","All coding region","NM_020937"),
                Exon("MRE11","All coding region","NM_005591"),
                Exon("NBN","All coding region","NM_002485"),
                Exon("PALB2","All coding region","NM_024675"),
                Exon("PPP2R2A","All coding region","NM_002717"),
                Exon("RAD50","All coding region","NM_005732"),
                Exon("RAD51","All coding region","NM_002875"),
                Exon("RAD51B","All coding region","NM_133509"),
                Exon("RAD51C","All coding region","NM_058216"),
                Exon("RAD51D","All coding region","NM_002878"),
                Exon("RAD52","All coding region","NM_134424"),
                Exon("RAD54L","All coding region","NM_003579"),
                Exon("XRCC2","All coding region","NM_005431")
            ))

        private val ON140 = Hrd(code="ON140", name="Homologous Recombination Deficiency Test(Green Plan) Report",
            essential = listOf(
                Exon("BRCA1","All coding region","NM_007294"),
                Exon("BRCA2","All coding region","NM_000059"),
            ),
            selective = listOf(
                Exon("ATM","All coding region","NM_000051"),
                Exon("BARD1","All coding region","NM_000465"),
                Exon("BRIP1","All coding region","NM_032043"),
                Exon("CDK12","All coding region","NM_016507"),
                Exon("CHEK1","All coding region","NM_001114121"),
                Exon("CHEK2","All coding region","NM_007194"),
                Exon("FANCC","All coding region","NM_000136"),
                Exon("FANCD2","All coding region","NM_033084"),
                Exon("FANCE","All coding region","NM_021922"),
                Exon("FANCF","All coding region","NM_022725"),
                Exon("FANCG","All coding region","NM_004629"),
                Exon("FANCI","All coding region","NM_001113378"),
                Exon("FANCL","All coding region","NM_018062"),
                Exon("FANCM","All coding region","NM_020937"),
                Exon("MRE11","All coding region","NM_005591"),
                Exon("NBN","All coding region","NM_002485"),
                Exon("PALB2","All coding region","NM_024675"),
                Exon("PPP2R2A","All coding region","NM_002717"),
                Exon("RAD50","All coding region","NM_005732"),
                Exon("RAD51","All coding region","NM_002875"),
                Exon("RAD51B","All coding region","NM_133509"),
                Exon("RAD51C","All coding region","NM_058216"),
                Exon("RAD51D","All coding region","NM_002878"),
                Exon("RAD52","All coding region","NM_134424"),
                Exon("RAD54L","All coding region","NM_003579"),
                Exon("XRCC2","All coding region","NM_005431")
            ),
            i18n = I18N.EnUs)
        fun values() = listOf(
            N140, ON140
        )
    }
}