package com.gcgenome.lims.test

class Cancerch(
    val code: String,
    val name: String,
    val nationalInsurance: Boolean = false
): HasCode, HasName, HasCategory, MayBeNationalInsurance {
    val category: HasCategory.Category = HasCategory.Category.Cancer
    override fun code() = code
    override fun name() = name
    override fun category() = category
    override fun isNationalInsuranceTest() = nationalInsurance
    companion object {
        private val N201 = Cancerch(
            code="N201", name="AVOID 검사")
        private val N203 = Cancerch(
            code="N203", name="캔서치")
        private val N205 = Cancerch(
            code="N205", name="캔서치검사(연구용)")
        fun values() = listOf(
            N201, N203, N205
        )
    }
}