package com.gcgenome.lims.test

import com.gcgenome.lims.test.Reportable.I18N
import com.gcgenome.lims.test.method.MrdAnalysisMethod
import com.gcgenome.lims.test.method.MrdAnalysisMethod.Companion.lymphotrackIghIgkMethod
import com.gcgenome.lims.test.method.MrdAnalysisMethod.Companion.lymphotrackIghMethod
import com.gcgenome.lims.test.method.MrdAnalysisMethod.Companion.lymphotrackTrbTrgMethod

data class Mrd ( // siblings 필드 미추가
    val code: String,
    val serialGroup: String,
    val name: String,
    val method: MrdAnalysisMethod,
    val nationalInsurance: Boolean = true,
    val i18n: I18N = I18N.KoKr,
    val screens: List<Mrd> = listOf()
): HasCode, HasName, HasCategory, ContainsGenes by method, MayBeNationalInsurance, IsPanelAnalysis by method, Interpretable, Reportable, HasSerialGroup {
    val category: HasCategory.Category = HasCategory.Category.MRD
    val interpretationCategory: Interpretable.Category = Interpretable.Category.MRD
    val reportCategory: Reportable.Category = Reportable.Category.MRD
    val genes: List<String> = method.genes
    val panel: String = method.panel
    val isScreen: Boolean = screens.isEmpty()
    override fun code() = code
    override fun name() = name
    override fun title() = name
    override fun genes(): List<String> = genes
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory(): Reportable.Category = reportCategory
    override fun serialGroup(): String = serialGroup
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): I18N = i18n
    companion object {
        private fun mrd(code: String, serialGroup: String, name: String, method: MrdAnalysisMethod, nationalInsurance: Boolean = true, i18n: I18N = I18N.KoKr, screens: List<Mrd>):Mrd = Mrd(code, serialGroup, name, method, nationalInsurance, i18n, screens)
        private fun screen(code: String, serialGroup: String, name: String, method: MrdAnalysisMethod, nationalInsurance: Boolean = true, i18n: I18N = I18N.KoKr):Mrd = Mrd(code, serialGroup, name, method, nationalInsurance, i18n)

        private val N144    = screen(code="N144",   serialGroup = "SH",  name="(Screen) IGH 유전자 재배열 검사",                method=lymphotrackIghMethod)
        private val N145    = screen(code="N145",   serialGroup = "SHK", name="(Screen) IGH/IGK 유전자 재배열 검사",            method=lymphotrackIghIgkMethod)
        private val N146    = screen(code="N146",   serialGroup = "SBG", name="(Screen) TRB/TRG 유전자 재배열 검사",            method=lymphotrackTrbTrgMethod)

        private val N150    = mrd(code="N150",      serialGroup = "MH",  name="(MRD) IGH 유전자 재배열 검사",                method=lymphotrackIghMethod,            screens = listOf(N144, N145))
        private val N151    = mrd(code="N151",      serialGroup = "MHK", name="(MRD) IGH/IGK 유전자 재배열 검사",            method=lymphotrackIghIgkMethod,         screens = listOf(N145))
        private val N152    = mrd(code="N152",      serialGroup = "MBG", name="(MRD) TRB/TRG 유전자 재배열 검사",            method=lymphotrackTrbTrgMethod,         screens = listOf(N146))
        fun values() = listOf(
            N144,
            N145,
            N146,

            N150,
            N151,
            N152,
        )
    }
}