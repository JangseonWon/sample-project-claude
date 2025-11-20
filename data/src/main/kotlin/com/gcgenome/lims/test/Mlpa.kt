package com.gcgenome.lims.test

interface Mlpa: HasCode, HasCategory, HasName, ContainsGenes, IsMlpaAnalysis, MayBeNationalInsurance{
    companion object {
        val N257 = MlpaImpl(code="N257", gene = "NF1",
            method = MlpaAnalysisMethod(
                targetDisease = "Neurofibromatosis, type 1",
                target = "NF1 on Chromosome 17q11.2",
            ),
            name = "NF1 [Sequencing&MLPA]",
            title = "NF1 [Sequencing&MLPA] Analysis 결과보고서"
        )

        open class MlpaImpl (
            val code: String,
            val gene: String,
            val method: MlpaAnalysisMethod,
            val name: String,
            val title: String,
            val nationalInsurance: Boolean = true,
            val category: HasCategory.Category = HasCategory.Category.MLPA
        ): Mlpa, IsMlpaAnalysis by method {
            val genes = listOf(gene)
            override fun code() = code
            override fun genes() = genes
            override fun category() = category
            override fun name() = name
            override fun toString() = name
            override fun isNationalInsuranceTest() = nationalInsurance
        }
        data class MlpaAnalysisMethod (
            private val specimen: String = "Genomic DNA isolated from peripheral blood leukocytes",
            private val targetDisease: String,
            private val target: String,
            private val method: String= "MLPA (Multiplex Ligation-dependent Probe Amplification)"
        ) : IsMlpaAnalysis {
            override fun specimen() = specimen
            override fun targetDisease() = targetDisease
            override fun target() = target
            override fun method() = method
        }
        fun values() = listOf(
            N257
        )
    }
}