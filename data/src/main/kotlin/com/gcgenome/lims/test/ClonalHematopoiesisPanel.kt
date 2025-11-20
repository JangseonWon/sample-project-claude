package com.gcgenome.lims.test

class ClonalHematopoiesisPanel(
    val code: String,
    val name: String,
    val serialGroup: String = "", // TODO: serialGroup 확인 후 추가 필요
    val title: String = "클론성 조혈증 패널 검사(연구 검사) 결과보고서",
    val method: String = "클론성 조혈증 패널 검사",
    val nationalInsurance: Boolean = false
): HasCode, HasName, HasCategory, HasSerialGroup, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.ClonalHematopoiesis
    val interpretationCategory: Interpretable.Category = Interpretable.Category.ClonalHematopoiesis
    val reportCategory: Reportable.Category = Reportable.Category.ClonalHematopoiesis
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
    override fun code(): String  = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun isNationalInsuranceTest(): Boolean = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun title() = title
    companion object {
        private val N159 = ClonalHematopoiesisPanel(code="N159", name="클론성 조혈증 패널 검사(연구 검사) 결과보고서")
        fun values() = listOf(N159)
    }
}