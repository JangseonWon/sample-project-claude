package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.NiptAnalysisMethod
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.AutosomalChromosomeMonosomyTarget.Companion.all
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.AutosomalChromosomeTrisomyTarget.Companion.allWithoutT13T18T21
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.Target
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.cnvTargetEssential
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.cnvTargetPrime
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.performanceKo
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.performanceOversea

data class NIPT (
    val code: String,
    val name: String,
    val serialGroup: String = "GN",
    val method: NiptAnalysisMethod,
    val nationalInsurance: Boolean = true,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr,
    val report: Reportable.Category = Reportable.Category.NIPT,
    val limitation: List<String>,
    val references: List<String> = REFERENCES
): HasCode, HasName, HasSerialGroup, HasCategory, MayBeNationalInsurance, IsNiptAnalysis by method, Reportable {
    val category: HasCategory.Category = HasCategory.Category.NIPT
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun title() = name
    override fun category() = category
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun reportCategory(): Reportable.Category = report
    companion object {
        private val LIMITATION_KOKR = listOf(
            "본 검사는 태아 염색체 이수성을 선별하는 데 높은 정확도를 보이는 산전 선별 검사이지만, 확진검사로 간주되지 않으며 위양성 및 위음성 결과가 발생할 수 있습니다. ",
            "검사 결과가 고위험(High Risk)으로 판정될 경우 양수검사 또는 융모막 검사와 같은 침습적 확진검사를 통한 확진이 반드시 필요합니다. 또한, 저위험(Low Risk)으로 판정된 경우에도 염색체 이상 가능성을 완전히 배제할 수 없으므로, 의사의 판단에 따라 추가적인 검사가 권장될 수 있습니다.",
            "본 검사는 신경관 결손을 확인할 수 없습니다.",
            "낮은 태아 분획(빠른 임신 주수, 산모의 높은 BMI), 모체의 밝혀지지 않은 염색체 이상, 태반에 국한된 모자이시즘, 태아의 염색체 이상 모자이시즘, 다태아, 태아 분획 측정 오차, 산모의 상태(종양질환, 수혈, 이식수술, 항암치료, 세포치료, 자가면역질환), 약물 사용(헤파린 등) 등의 임상상태에 따라 검사결과의 정확성에 영향을 줄 수 있으며, 판정 불가 결과가 보고될 수 있습니다.",
            "G-NIPT의 태아 성별은 Y 염색체 유무로 결과를 보고하기 때문에 성염색체의 복제수변이(ex. Y 염색체 결실 소견), vanishing twin, 성염색체 모자이시즘 이상, 성분화 이상의 경우에는 실제 성별 또는 생식기 외형과 불일치할 수 있습니다.",
            "쌍태아 초과(삼태아 이상, Vanishing triplet)시 검사가 불가능합니다.",
            "산모의 BMI지수가 27을 초과하는 경우, 태아분획이 낮아 결과보고가 어려울 수 있으므로 임신 16주 이후 의뢰하시는 것을 권장드립니다",
            "NIPT 검사는 임신 10주~22주 사이에 검사하는 것을 권장드리며, 쌍둥이 소실(vanishing twin)의 경우 소실 시점으로부터 최소 6주 이후 검사 가능하지만, 9주 이후에 검사하는 것을 권장드립니다. 권장 시점(소실 후 9주 이후)보다 이른 시기에 진행한 검사 결과는 정확하지 않을 수도 있습니다.",
            "NIPT 검사 결과 항목은 오직 담당의사의 참고자료로 사용될 수 있으며 특히 기타 소견(기타 염색체 수적 이상, 기타 복제수변이, 기타 이상 소견)의 경우 발생 빈도가 매우 낮아 검사의 성능을 보장할 수 없는 질환들이 포함되어 있으므로 신중한 결과 해석이 필요합니다."
        )
        private val LIMITATION_ENUS = listOf(
            "NIPT is a highly accurate prenatal screening test developed to detect fetal chromosomal aneuploidies. However, it is a screening test, not a diagnostic test, and false-positive or false-negative results may occur. If the test result indicates a high risk, confirmatory diagnostic testing via invasive procedures, such as amniocentesis or chorionic villus sampling (CVS), is recommended. ",
            "Even with a low-risk result, the possibility of chromosomal abnormalities cannot be entirely excluded, and additional testing may be recommended based on the physician's assessment.",
            "This test does not screen for neural tube defects.",
           "The accuracy of this test may be affected by clinical factors, including low fetal fraction (e.g., early gestational age, high maternal BMI), unrecognized maternal chromosomal abnormalities, confined placental mosaicism, fetal chromosomal mosaicism, multiple gestations, fetal fraction measurement error, specific maternal conditions (e.g., tumors, blood transfusions, transplants, chemotherapy, cellular therapy, autoimmune diseases), and certain medications (e.g., heparin).",
            "G-NIPT reports fetal sex based on the presence of the Y chromosome. Therefore, in cases involving sex chromosome copy number variations (e.g., Y chromosome deletion findings), vanishing twin, sex chromosome mosaicism, or disorders of sexual development, the actual sex or external genital appearance may not match the reported results.",
            "Testing is not possible for pregnancies with more than twins (e.g., triplets or vanishing triplets).",
            "For mothers with a BMI exceeding 27, the fetal fraction may be low, making it difficult to report results. Therefore, it is recommended to request testing after 16 weeks of pregnancy.",
            "NIPT is recommended to be performed between 10 and 22 weeks of pregnancy. In cases of vanishing twin, testing can be conducted at least 6 weeks after the event; however, it is recommended to test after 9 weeks. Results from tests conducted earlier than the recommended timeframe (9 weeks after the event) may not be accurate.",
            "NIPT test results are intended for the attending physician's guidance only. Particularly for findings categorized as \"RARE VARIANTS(other chromosome abnormality, other copy number variation, additional result)\", these may include conditions with extremely low occurrence rates, and the performance of the test cannot be guaranteed for such conditions. Therefore, careful interpretation of the results is necessary."
        )
        private val REFERENCES = listOf(
            "Placenta. 2014 Feb;35 Suppl(Suppl):S64-8. Review: cell-free fetal DNA in the maternal circulation as an indication of placental health and disease",
            "PLoS One. 2016 Jan 15;11(1):e0146794. False Negative NIPT Results: Risk Figures for Chromosomes 13, 18 and 21 Based on Chorionic Villi Results in 5967 Cases and Literature Review",
            "JAMA. 2015 Jul 14;314(2):162-9. Noninvasive Prenatal Testing and Incidental Detection of Occult Maternal Malignancies",
            "N Engl J Med. 2015 Apr 23;372(17):1639-45. Copy-number variation and false positive prenatal aneuploidy screening results",
            "Clin Genet. 2016 May;89(5):523-30. Clinical implementation of NIPT - technical and biological challenges",
            "Fetal Diagn Ther. 1995 Nov-Dec;10(6):356-67"
        )
        // 기존 G-NIPT
        // 상염색체 44종(Trisomy, Monosomy), 성염색체 4종, CNV 140종 + 10MB 이상
        private val N006 = NIPT(code="N006", name="비침습적 산전 염색체 이상 선별검사 Prime", limitation = LIMITATION_KOKR,
            method = NiptAnalysisMethod(
                performance = performanceKo,
                target = Target(
                    trisomies = allWithoutT13T18T21,
                    monosomies = all,
                    cnvs = cnvTargetPrime
                )
            )
        )
        // 기존 G-NIPT Plus
        // 상염색체 44종(Trisomy, Monosomy), 성염색체 4종, CNV 140종 + 7MB 이상
        // 대학병원 전용, 결과지 간소화
        private val N019 = NIPT(code="N019", name="비침습적 산전 염색체 이상 선별검사 Plus", limitation = LIMITATION_KOKR,
            method = NiptAnalysisMethod(
                performance = performanceKo,
                target = Target(
                    trisomies = allWithoutT13T18T21,
                    monosomies = all,
                    cnvs = cnvTargetPrime
                )
            ),
        )
        // 기존 G-NIPT 난임전용
        // 상염색체 22종(Trisomy), 성염색체 4종, CNV 8종 + 20MB 이상
        private val N128 = NIPT(code="N128", name="비침습적 산전 염색체 이상 선별검사 Essential", limitation = LIMITATION_KOKR,
            method = NiptAnalysisMethod(
                performance = performanceKo,
                target = Target(
                    trisomies = allWithoutT13T18T21,
                    monosomies = listOf(),
                    cnvs = cnvTargetEssential
                )
            )
        )
        // 상염색체 3종(Trisomy), 성염색체 4종
        private val O001 = NIPT(code="O001", name="Non-Invasive Prenatal Test Lite", limitation = LIMITATION_ENUS, method = NiptAnalysisMethod(
            performance = performanceOversea,
            target = Target(
                trisomies = listOf(),
                monosomies = listOf(),
                cnvs = listOf()
            ),
        ), i18n = Reportable.I18N.EnUs)
        // 상염색체 22종(Trisomy), 성염색체 4종
        private val O002 = NIPT(code="O002", name="Non-Invasive Prenatal Test Basic", limitation = LIMITATION_ENUS, method = NiptAnalysisMethod(
            performance = performanceOversea,
            target = Target(
                trisomies = allWithoutT13T18T21,
                monosomies = listOf(),
                cnvs = listOf()
            ),
        ), i18n = Reportable.I18N.EnUs)
        // 상염색체 44종(Trisomy, Monosomy), 성염색체 4종, CNV 140종 + 7MB 이상
        private val O003 = NIPT(code="O003", name="Non-Invasive Prenatal Test Premium", limitation = LIMITATION_ENUS, method = NiptAnalysisMethod(
            performance = performanceOversea,
            target = Target(
                trisomies =  allWithoutT13T18T21,
                monosomies = all,
                cnvs = cnvTargetPrime
            ),
        ), i18n = Reportable.I18N.EnUs)
        fun values() = listOf(
            N006, N019, N128, O001, O002, O003
        )
    }
}