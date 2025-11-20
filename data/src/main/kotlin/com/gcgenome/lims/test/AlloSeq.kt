package com.gcgenome.lims.test

data class AlloSeq (
    val code: String,
    val name: String,
    val nationalInsurance: Boolean = false
): HasCode, HasName, HasCategory, MayBeNationalInsurance {
    val category: HasCategory.Category = HasCategory.Category.ALLOSEQ
    override fun code() = code
    override fun name() = name
    override fun category() = category
    override fun isNationalInsuranceTest() = nationalInsurance
    companion object {
        private val N195 = AlloSeq(
            code="N195", name="신장이식 공여자유래 세포유리핵산 단일염기다형성 검사[염기서열검사]")
        private val N196 = AlloSeq(
            code="N196", name="심장이식 공여자유래 세포유리핵산 단일염기다형성 검사[염기서열검사]")
        private val N197 = AlloSeq(
            code="N197", name="폐이식 공여자유래 세포유리핵산 단일염기다형성 검사[염기서열검사]")
        fun values() = listOf(
            N195, N196, N197
        )
    }
}