package com.gcgenome.lims.test

data class Sanger (
    val code: String,
    val name: String,
    val serialGroup: String,
    val nationalInsurance: Boolean = false
): HasCode, HasName, HasSerialGroup, HasCategory, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.SANGER
    val interpretationCategory: Interpretable.Category = Interpretable.Category.SANGER
    val reportCategory: Reportable.Category = Reportable.Category.SANGER
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun title() = name
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    companion object {
        private val N147 = Sanger(
            code="N147", name="DGS Proband/Family test", serialGroup = "PN-F")
        private val S004 = Sanger(
            code="S004", name="DES Proband/Family test", serialGroup = "DES-F")
        private val S022 = Sanger(
            code="S022", name="Familial mutation", serialGroup = "PN-F")
        private val S121 = Sanger(
            code="S121", name="Familial mutation_study", serialGroup = "WES-F")
        private val T036 = Sanger(
            code="T036", name="WES Family Test", serialGroup = "WES-F")
        private val ON147 = Sanger(
            code="ON147", name="DGS Proband/Family Test Report", serialGroup = "PN-F")
        private val OT036 = Sanger(
            code="OT036", name="WES Family Test Report", serialGroup = "WES-F")
        private val OS004 = Sanger(
            code="OS004", name="DES Family Test Report", serialGroup = "DES-F")
        private val OS022 = Sanger(
            code="OS022", name="Familial Mutation Test Report", serialGroup = "PN-F")
        fun values() = listOf(
            N147, S004, S022, S121, T036, ON147, OT036, OS004, OS022
        )
    }
}