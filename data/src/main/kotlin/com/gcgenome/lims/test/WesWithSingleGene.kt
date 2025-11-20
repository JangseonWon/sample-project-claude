package com.gcgenome.lims.test

import com.fasterxml.jackson.annotation.JsonUnwrapped

class WesWithSingleGene (
    @JsonUnwrapped val wes: Wes,
    @JsonUnwrapped val singleGenePanel: SingleGenePanel
): SingleGenePanel by singleGenePanel, Wes by wes {
    val category: HasCategory.Category = HasCategory.Category.WesWithSingle
    val interpretationCategory: Interpretable.Category = Interpretable.Category.WesWithSingle
    val reportCategory: Reportable.Category = Reportable.Category.WesWithSingle
    override fun code(): String = singleGenePanel.code()
    override fun name(): String = singleGenePanel.name()
    override fun serialGroup(): String = wes.serialGroup()
    override fun title(): String = singleGenePanel.title()
    override fun category(): HasCategory.Category = category
    override fun interpretationCategory(): Interpretable.Category = interpretationCategory
    override fun reportCategory(): Reportable.Category = reportCategory
    override fun isNationalInsuranceTest(): Boolean = singleGenePanel.isNationalInsuranceTest()
    override fun specimen(): String = singleGenePanel.specimen()
    override fun genes(): List<String> = singleGenePanel.genes()
    override fun i18n(): Reportable.I18N = singleGenePanel.i18n()
    companion object {
        val N058 = WesWithSingleGene(
            singleGenePanel = SingleGenePanel.N058,
            wes = Wes.N058
        )
        fun values() = listOf(N058)
        val GENE_INCIDENTAL_FINDINGS = listOf(
            "ACTA2","ACTC1","ACVRL1","APC","APOB","ATP7B","BAG3","BMPR1A","BRCA1","BRCA2","BTD","CACNA1S","CALM1",
            "CALM2", "CALM3","CASQ2","COL3A1","DES","DSC2","DSG2","DSP", "ENG","FBN1","FLNC","GAA","GLA","HFE",
            "HNF1A","KCNH2", "KCNQ1","LDLR","LMNA","MAX","MEN1","MLH1","MSH2","MSH6","MUTYH","MYBPC3","MYH11",
            "MYH7","MYL2","MYL3","NF2", "OTC","PALB2","PCSK9","PKP2","PMS2","PRKAG2","PTEN","RB1","RBM20","RET",
            "RPE65","RYR1","RYR2","SCN5A", "SDHAF2", "SDHB","SDHC","SDHD","SMAD3","SMAD4","STK11","TGFBR1","TGFBR2",
            "TMEM127","TMEM43","TNNC1","TNNI3","TNNT2", "TP53","TPM1","TRDN","TSC1","TSC2","TTN","TTR","VHL","WT1"
        )
    }
}