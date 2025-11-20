package com.gcgenome.lims.test

class `FLT3-ITD`(
    val code: String,
    val name: String,
    val serialGroup: String = "AML-I",
    val title: String = "$name 결과보고서",
    val method: String = "PCR & Fragment length analysis",
    val nationalInsurance: Boolean = true
): HasCode, HasName, HasCategory, HasSerialGroup, ContainsGenes, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.ETC
    val interpretationCategory: Interpretable.Category = Interpretable.Category.ETC
    val reportCategory: Reportable.Category = Reportable.Category.ETC
    val gene = "FLT3 유전자"
    val genes = listOf(gene)
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
    override fun code(): String  = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun genes() = genes
    override fun isNationalInsuranceTest(): Boolean = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun title() = title
    companion object {
        private val S051 = `FLT3-ITD`(code="S051", name="FLT3-ITD [Fragment analysis]")
        fun values() = listOf(S051)
    }
}