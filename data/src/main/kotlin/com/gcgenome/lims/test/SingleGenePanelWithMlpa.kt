package com.gcgenome.lims.test

import com.fasterxml.jackson.annotation.JsonUnwrapped

class SingleGenePanelWithMlpa (
    @JsonUnwrapped val mlpa: Mlpa,
    @JsonUnwrapped val singleGenePanel: SingleGenePanel.Companion.SingleGenePanelHasReferralDefault,
    val method: IsSingleGeneWithMlpaMethod
): SingleGenePanel by singleGenePanel, Mlpa by mlpa, HasSerialGroup, HasReferralDefault by singleGenePanel {
    val category: HasCategory.Category = HasCategory.Category.SingleWithMLPA
    val interpretationCategory: Interpretable.Category = Interpretable.Category.SingleWithMLPA
    val reportCategory: Reportable.Category = Reportable.Category.SingleWithMLPA
    override fun code(): String = singleGenePanel.code()
    override fun name(): String = singleGenePanel.name()
    override fun serialGroup(): String = singleGenePanel.serialGroup()
    override fun title() = singleGenePanel.title()
    override fun category(): HasCategory.Category = category
    override fun interpretationCategory(): Interpretable.Category = interpretationCategory
    override fun reportCategory(): Reportable.Category = reportCategory
    override fun isNationalInsuranceTest(): Boolean = singleGenePanel.isNationalInsuranceTest()
    override fun specimen(): String = singleGenePanel.specimen()
    override fun targetDisease(): String = mlpa.targetDisease()
    override fun target(): String = mlpa.target()
    override fun genes(): MutableList<String> = mlpa.genes()
    override fun method(): String = String.format("%s, %s", singleGenePanel.sequencing(), mlpa.method())
    companion object {
        val N257 = SingleGenePanelWithMlpa(
            singleGenePanel = SingleGenePanel.N257,
            mlpa = Mlpa.N257,
            method = object: IsSingleGeneWithMlpaMethod, IsSingleGenePanelAnalysis by SingleGenePanel.N257.method, IsMlpaAnalysis by Mlpa.N257.method {
                @JsonUnwrapped val mlpa = Mlpa.N257.method
                @JsonUnwrapped val single = SingleGenePanel.N257.method
                override fun specimen(): String = single.specimen()
                override fun target(): String = mlpa.target()
            }
        )
        fun values() = listOf(N257)
    }
    interface IsSingleGeneWithMlpaMethod: IsSingleGenePanelAnalysis, IsMlpaAnalysis
}