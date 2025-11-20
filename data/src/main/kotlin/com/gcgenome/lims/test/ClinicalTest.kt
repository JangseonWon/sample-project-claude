package com.gcgenome.lims.test

data class ClinicalTest (
    val code: String,
    val name: String,
): HasCode, HasName, HasCategory {
    val category: HasCategory.Category = HasCategory.Category.ClinicalTest
    override fun code() = code
    override fun name() = name
    override fun category() = category
    companion object {
        private val G0012401 = ClinicalTest (
            code="G0012401", name="GIC-102101_Chimerism분석")
        private val R0032401 = ClinicalTest (
            code="R0032401", name="Bacteriophage WGS(연구용)")
        fun values() = listOf(
            G0012401, R0032401
        )
    }
}