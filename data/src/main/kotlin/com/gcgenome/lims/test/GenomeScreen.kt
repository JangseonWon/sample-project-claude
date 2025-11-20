package com.gcgenome.lims.test

data class GenomeScreen(
    val code: String,
    val name: String,
    val displayName: String? = null,
    val serialGroup: String,
    val genomicDisorders: List<GenomicDisorder>,
    val nationalInsurance: Boolean = false,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr,
    val genotypes: List<Genotype> = listOf(),
): HasCode, HasName, HasDisplayName, HasCategory, HasSerialGroup,
    IsGenomicDisorderScreeningTest, MayBeNationalInsurance, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.GenomeScreen
    val interpretationCategory: Interpretable.Category = Interpretable.Category.GenomeScreen
    val reportCategory: Reportable.Category = Reportable.Category.GenomeScreen
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun displayName() = displayName
    override fun title() = name
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory

    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun diseases() = genomicDisorders
    data class Genotype(
        val gene: String,
        val pos: String? = null,
        val types: List<String>,
    )
    companion object {
        private fun genomescreen(code: String, name: String, serialGroup: String, displayName: String? = null, genomicDisorders: List<GenomicDisorder>, nationalInsurance: Boolean = false, i18n: Reportable.I18N = Reportable.I18N.KoKr):GenomeScreen = GenomeScreen(code, name, displayName, serialGroup, genomicDisorders, nationalInsurance, i18n)
        private fun genomescreenWithRiskScreen(code: String, name: String, serialGroup: String, displayName: String? = name, genomicDisorders: List<GenomicDisorder>, nationalInsurance: Boolean = false, i18n: Reportable.I18N = Reportable.I18N.KoKr, genotypes: List<Genotype>):GenomeScreen = GenomeScreen(code, name, displayName, serialGroup, genomicDisorders, nationalInsurance, i18n, genotypes)

        val N074 = genomescreen(
            code = "N074", name = "암유전자 패널검사 (강북삼성)", serialGroup="SMC-CAN",
            genomicDisorders = listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53")),
                GenomicDisorder("포이츠 제거스 증후군", listOf("STK11")),
                GenomicDisorder(
                    "린치 증후군",
                    listOf("MLH1", "MSH2", "MSH6", "PMS2")
                ),
                GenomicDisorder("가족성 선종성 용종증", listOf("APC")),
                GenomicDisorder("MUTYH 연관 용종증", listOf("MUTYH")),
                GenomicDisorder("연소성 용종증 증후군", listOf("BMPR1A", "SMAD4")),
                GenomicDisorder("폰히펠 린다우 증후군", listOf("VHL")),
                GenomicDisorder("제1형 다발성 내분비선종증", listOf("MEN1")),
                GenomicDisorder("제2형 다발성 내분비선종증", listOf("RET")),
                GenomicDisorder("PTEN 과오종 증후군", listOf("PTEN")),
                GenomicDisorder("망막모세포종", listOf("RB1")),
                GenomicDisorder(
                    "유전성 부신경절종 갈색세포종",
                    listOf("SDHD", "SDHAF2", "SDHC", "SDHB")
                ),
                GenomicDisorder("결절성 경화증", listOf("TSC1", "TSC2")),
                GenomicDisorder("WT1 연관 윌름스 종양", listOf("WT1")),
                GenomicDisorder("제2형 신경섬유종증", listOf("NF2"))
            ))
        val N075 = genomescreen(
            code = "N075", name = "심장유전자 패널검사 (강북삼성)", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("엘러스-단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder(
                    "로이-디에츠 증후군",
                    listOf("TGFBR1", "TGFBR2", "SMAD3", "TGFB2", "TGFB3")
                ),
                GenomicDisorder(
                    "가족성 흉부대동맥류와 박리증",
                    listOf("FBN1", "SMAD3", "TGFBR1", "TGFBR2", "ACTA2", "MYH11", "MYLK")
                ),
                GenomicDisorder(
                    "비후성 심근병증",
                    listOf("MYBPC3", "MYH7", "TNNT2", "TNNI3", "TPM1", "MYL3", "MYL2", "CSRP3", "PRKAG2")
                ),
                GenomicDisorder(
                    "확장성 심근병증",
                    listOf("ACTC1", "MYH7", "LMNA", "BAG3", "DES")
                ),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder(
                    "부정맥 유발성 우심실 심근병증",
                    listOf("PKP2", "DSP", "DSC2", "TMEM43", "DSG2")
                ),
                GenomicDisorder("애머리 드라이푸스 증후군", listOf("EMD", "FHL1")),
                GenomicDisorder("카테콜아민성 다형성 심실성 빈맥", listOf("RYR2")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNH2", "SCN5A")
                ),
                GenomicDisorder("브루가다 증후군", listOf("SCN5A")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9")
                ),
                GenomicDisorder("고호모시스테인혈전증", listOf("CBS")),
                GenomicDisorder("동맥비틀림증후군", listOf("SLC2A10"))
            ))
        val N089 = genomescreen(
            code = "N089", name = "심장 돌연사 지놈 스크린 / 검진", displayName = "GSH_심장 돌연사 지놈 스크린 / 검진", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("엘러스-단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder(
                    "로이-디에츠 증후군",
                    listOf("TGFBR1", "TGFBR2", "SMAD3", "TGFB2", "TGFB3")
                ),
                GenomicDisorder(
                    "가족성 흉부대동맥류와 박리증",
                    listOf("FBN1", "SMAD3", "TGFBR1", "TGFBR2", "ACTA2", "MYH11", "MYLK")
                ),
                GenomicDisorder(
                    "비후성 심근병증",
                    listOf("MYBPC3", "MYH7", "TNNT2", "TNNI3", "TPM1", "MYL3", "MYL2", "CSRP3", "PRKAG2")
                ),
                GenomicDisorder(
                    "확장성 심근병증",
                    listOf("ACTC1", "MYH7", "LMNA", "BAG3", "DES")
                ),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder(
                    "부정맥 유발성 우심실 심근병증",
                    listOf("PKP2", "DSP", "DSC2", "TMEM43", "DSG2")
                ),
                GenomicDisorder("애머리 드라이푸스 증후군", listOf("EMD", "FHL1")),
                GenomicDisorder("카테콜아민성 다형성 심실성 빈맥", listOf("RYR2")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNH2", "SCN5A")
                ),
                GenomicDisorder("브루가다 증후군", listOf("SCN5A")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9")
                ),
                GenomicDisorder("고호모시스테인혈전증", listOf("CBS")),
                GenomicDisorder("동맥비틀림증후군", listOf("SLC2A10"))
            ))
        val N090 = genomescreen(
            code = "N090", name = "암 지놈 스크린 / 검진", displayName = "GSC_암 지놈 스크린 / 검진", serialGroup = "CAN",
            genomicDisorders = listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder(
                    "유방암 감수성",
                    listOf("ATM", "CDH1", "CHEK2", "NBN", "NF1", "PALB2")
                ),
                GenomicDisorder(
                    "난소암 감수성",
                    listOf("BRIP1", "RAD51C", "RAD51D")
                ),
                GenomicDisorder(
                    "전립선암 감수성",
                    listOf("ATM", "CHEK2", "MLH1", "MSH2", "MSH6", "PALB2", "PMS2")
                ),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53")),
                GenomicDisorder("위암", listOf("CDH1")),
                GenomicDisorder("포이츠 제거스 증후군", listOf("STK11")),
                GenomicDisorder(
                    "린치 증후군",
                    listOf("EPCAM", "MLH1", "MSH2", "MSH6", "PMS2")
                ),
                GenomicDisorder("가족성 선종성 용종증", listOf("APC")),
                GenomicDisorder("MUTYH 연관 용종증", listOf("MUTYH")),
                GenomicDisorder("연소성 용종증 증후군", listOf("BMPR1A", "SMAD4")),
                GenomicDisorder("폰히펠 린다우 증후군", listOf("VHL")),
                GenomicDisorder("제1형 다발성 내분비선종증", listOf("MEN1")),
                GenomicDisorder("제2형 다발성 내분비선종증", listOf("RET")),
                GenomicDisorder("PTEN 과오종 증후군", listOf("PTEN")),
                GenomicDisorder("망막모세포종", listOf("RB1")),
                GenomicDisorder(
                    "유전성 부신경절종 갈색세포종",
                    listOf("SDHD", "SDHAF2", "SDHC", "SDHB")
                ),
                GenomicDisorder("결절성 경화증", listOf("TSC1", "TSC2")),
                GenomicDisorder("WT1 연관 윌름스 종양", listOf("WT1")),
                GenomicDisorder("제2형 신경섬유종증", listOf("NF2"))
            ))
        val N101 = genomescreen(
            code = "N101", name = "캔서 진 스크린(건협)", serialGroup = "BRCA",
            genomicDisorders = listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53"))
            ))
        val N109 = genomescreen(
            code = "N109", name = "CANCER GENOME SCREEN (영문)", serialGroup = "GSC",
            genomicDisorders = listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder(
                    "유방암 감수성",
                    listOf("ATM", "CDH1", "CHEK2", "NBN", "NF1", "PALB2")
                ),
                GenomicDisorder(
                    "난소암 감수성",
                    listOf("BRIP1", "RAD51C", "RAD51D")
                ),
                GenomicDisorder(
                    "전립선암 감수성",
                    listOf("ATM", "CHEK2", "MLH1", "MSH2", "MSH6", "PALB2", "PMS2")
                ),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53")),
                GenomicDisorder("위암", listOf("CDH1")),
                GenomicDisorder("포이츠 제거스 증후군", listOf("STK11")),
                GenomicDisorder(
                    "린치 증후군",
                    listOf("EPCAM", "MLH1", "MSH2", "MSH6", "PMS2")
                ),
                GenomicDisorder("가족성 선종성 용종증", listOf("APC")),
                GenomicDisorder("MUTYH 연관 용종증", listOf("MUTYH")),
                GenomicDisorder("연소성 용종증 증후군", listOf("BMPR1A", "SMAD4")),
                GenomicDisorder("폰히펠 린다우 증후군", listOf("VHL")),
                GenomicDisorder("제1형 다발성 내분비선종증", listOf("MEN1")),
                GenomicDisorder("제2형 다발성 내분비선종증", listOf("RET")),
                GenomicDisorder("PTEN 과오종 증후군", listOf("PTEN")),
                GenomicDisorder("망막모세포종", listOf("RB1")),
                GenomicDisorder(
                    "유전성 부신경절종 갈색세포종",
                    listOf("SDHD", "SDHAF2", "SDHC", "SDHB")
                ),
                GenomicDisorder("결절성 경화증", listOf("TSC1", "TSC2")),
                GenomicDisorder("WT1 연관 윌름스 종양", listOf("WT1")),
                GenomicDisorder("제2형 신경섬유종증", listOf("NF2"))
            ),
            i18n = Reportable.I18N.EnUs)
        val N111 = genomescreen(
            code = "N111", name = "캔서 진 스크린(건협 임직원)", serialGroup = "BRCA",
            genomicDisorders = listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53"))
            ))
        val N112 = genomescreen(
            code = "N112", name = "심장 돌연사 지놈 스크린(건협 임직원)", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("엘러스-단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder(
                    "로이-디에츠 증후군",
                    listOf("TGFBR1", "TGFBR2", "SMAD3", "TGFB2", "TGFB3")
                ),
                GenomicDisorder(
                    "가족성 흉부대동맥류와 박리증",
                    listOf("FBN1", "SMAD3", "TGFBR1", "TGFBR2", "ACTA2", "MYH11", "MYLK")
                ),
                GenomicDisorder(
                    "비후성 심근병증",
                    listOf("MYBPC3", "MYH7", "TNNT2", "TNNI3", "TPM1", "MYL3", "MYL2", "CSRP3", "PRKAG2")
                ),
                GenomicDisorder(
                    "확장성 심근병증",
                    listOf("ACTC1", "MYH7", "LMNA", "BAG3", "DES")
                ),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder(
                    "부정맥 유발성 우심실 심근병증",
                    listOf("PKP2", "DSP", "DSC2", "TMEM43", "DSG2")
                ),
                GenomicDisorder("애머리 드라이푸스 증후군", listOf("EMD", "FHL1")),
                GenomicDisorder("카테콜아민성 다형성 심실성 빈맥", listOf("RYR2")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNH2", "SCN5A")
                ),
                GenomicDisorder("브루가다 증후군", listOf("SCN5A")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9")
                ),
                GenomicDisorder("고호모시스테인혈전증", listOf("CBS")),
                GenomicDisorder("동맥비틀림증후군", listOf("SLC2A10"))
            ))
        val N185 = genomescreen(
            code = "N185", name = "심장 돌연사 지놈 스크린(건협)", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("엘러스-단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder(
                    "로이-디에츠 증후군",
                    listOf("TGFBR1", "TGFBR2", "SMAD3", "TGFB2", "TGFB3")
                ),
                GenomicDisorder(
                    "가족성 흉부대동맥류와 박리증",
                    listOf("FBN1", "SMAD3", "TGFBR1", "TGFBR2", "ACTA2", "MYH11", "MYLK")
                ),
                GenomicDisorder(
                    "비후성 심근병증",
                    listOf("MYBPC3", "MYH7", "TNNT2", "TNNI3", "TPM1", "MYL3", "MYL2", "CSRP3", "PRKAG2")
                ),
                GenomicDisorder(
                    "확장성 심근병증",
                    listOf("ACTC1", "MYH7", "LMNA", "BAG3", "DES")
                ),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder(
                    "부정맥 유발성 우심실 심근병증",
                    listOf("PKP2", "DSP", "DSC2", "TMEM43", "DSG2")
                ),
                GenomicDisorder("애머리 드라이푸스 증후군", listOf("EMD", "FHL1")),
                GenomicDisorder("카테콜아민성 다형성 심실성 빈맥", listOf("RYR2")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNH2", "SCN5A")
                ),
                GenomicDisorder("브루가다 증후군", listOf("SCN5A")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9")
                ),
                GenomicDisorder("고호모시스테인혈전증", listOf("CBS")),
                GenomicDisorder("동맥비틀림증후군", listOf("SLC2A10"))
            ))
        val ON089 = genomescreen(
            code ="ON089", name = "심장 돌연사 지놈 스크린 / 검진", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("엘러스-단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder(
                    "로이-디에츠 증후군",
                    listOf("TGFBR1", "TGFBR2", "SMAD3", "TGFB2", "TGFB3")
                ),
                GenomicDisorder(
                    "가족성 흉부대동맥류와 박리증",
                    listOf("FBN1", "SMAD3", "TGFBR1", "TGFBR2", "ACTA2", "MYH11", "MYLK")
                ),
                GenomicDisorder(
                    "비후성 심근병증",
                    listOf("MYBPC3", "MYH7", "TNNT2", "TNNI3", "TPM1", "MYL3", "MYL2", "CSRP3", "PRKAG2")
                ),
                GenomicDisorder(
                    "확장성 심근병증",
                    listOf("ACTC1", "MYH7", "LMNA", "BAG3", "DES")
                ),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder(
                    "부정맥 유발성 우심실 심근병증",
                    listOf("PKP2", "DSP", "DSC2", "TMEM43", "DSG2")
                ),
                GenomicDisorder("애머리 드라이푸스 증후군", listOf("EMD", "FHL1")),
                GenomicDisorder("카테콜아민성 다형성 심실성 빈맥", listOf("RYR2")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNH2", "SCN5A")
                ),
                GenomicDisorder("브루가다 증후군", listOf("SCN5A")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9")
                ),
                GenomicDisorder("고호모시스테인혈전증", listOf("CBS")),
                GenomicDisorder("동맥비틀림증후군", listOf("SLC2A10"))
            ),
            i18n = Reportable.I18N.EnUs)
        val ON090 = genomescreen(
            code ="ON090", name ="암 지놈 스크린 / 검진", serialGroup = "CAN",
            genomicDisorders = 	listOf(
                GenomicDisorder("유전성 유방암 난소암 증후군", listOf("BRCA1", "BRCA2")),
                GenomicDisorder(
                    "유방암 감수성",
                    listOf("ATM", "CDH1", "CHEK2", "NBN", "NF1", "PALB2")
                ),
                GenomicDisorder(
                    "난소암 감수성",
                    listOf("BRIP1", "RAD51C", "RAD51D")
                ),
                GenomicDisorder(
                    "전립선암 감수성",
                    listOf("ATM", "CHEK2", "MLH1", "MSH2", "MSH6", "PALB2", "PMS2")
                ),
                GenomicDisorder("리 프라우메니 증후군", listOf("TP53")),
                GenomicDisorder("위암", listOf("CDH1")),
                GenomicDisorder("포이츠 제거스 증후군", listOf("STK11")),
                GenomicDisorder(
                    "린치 증후군",
                    listOf("EPCAM", "MLH1", "MSH2", "MSH6", "PMS2")
                ),
                GenomicDisorder("가족성 선종성 용종증", listOf("APC")),
                GenomicDisorder("MUTYH 연관 용종증", listOf("MUTYH")),
                GenomicDisorder("연소성 용종증 증후군", listOf("BMPR1A", "SMAD4")),
                GenomicDisorder("폰히펠 린다우 증후군", listOf("VHL")),
                GenomicDisorder("제1형 다발성 내분비선종증", listOf("MEN1")),
                GenomicDisorder("제2형 다발성 내분비선종증", listOf("RET")),
                GenomicDisorder("PTEN 과오종 증후군", listOf("PTEN")),
                GenomicDisorder("망막모세포종", listOf("RB1")),
                GenomicDisorder(
                    "유전성 부신경절종 갈색세포종",
                    listOf("SDHD", "SDHAF2", "SDHC", "SDHB")
                ),
                GenomicDisorder("결절성 경화증", listOf("TSC1", "TSC2")),
                GenomicDisorder("WT1 연관 윌름스 종양", listOf("WT1")),
                GenomicDisorder("제2형 신경섬유종증", listOf("NF2"))
            ),
            i18n = Reportable.I18N.EnUs)
        val N087 = genomescreenWithRiskScreen(
            code = "N087", name = "뇌졸중 지놈 스크린 / 검진", serialGroup = "ST",
            displayName = "GSSTK_뇌졸중 지놈 스크린 / 검진",
            genomicDisorders = listOf(
                GenomicDisorder("베타 지중해혈증", listOf("HBB")),
                GenomicDisorder("고호모시스테인혈증", listOf("CBS")),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder("탄력섬유거짓황색종", listOf("ABCC6")),
                GenomicDisorder("카다실", listOf("NOTCH3")),
                GenomicDisorder("카라실", listOf("HTRA1")),
                GenomicDisorder("백색질 형성장애 동반 망막 혈관병증", listOf("TREX1")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNJ2", "SCN5A")
                ),
                GenomicDisorder("혈관성 엘러스 단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder("결절성 다발동맥염", listOf("ADA2")),
                GenomicDisorder("동맥 비틀림 증후군", listOf("SLC2A10")),
                GenomicDisorder(
                    "가족성 편마비 편두통",
                    listOf("CACNA1A", "ATP1A2", "SCN1A")
                ),
                GenomicDisorder(
                    "모야모야병",
                    listOf("RNF213", "ACTA2", "GUCY1A1")
                ),
                GenomicDisorder(
                    "아밀로이드 뇌 혈관병증",
                    listOf("APP", "CST3", "ITM2B")
                ),
                GenomicDisorder("뇌 소혈관질환", listOf("COL4A1")),
                GenomicDisorder("뇌구멍증", listOf("COL4A2")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9", "APOE")
                ),
                GenomicDisorder("FactorVLeiden혈전증", listOf("F5")),
                GenomicDisorder("프로트롬빈 관련 혈전증", listOf("F2")),
                GenomicDisorder("항트롬빈 결핍 혈전증", listOf("SERPINC1")),
                GenomicDisorder("단백질C 결핍 혈전증", listOf("PROC")),
                GenomicDisorder("단백질S 결핍 혈전증", listOf("PROS1"))
            ),
            genotypes = listOf(
                Genotype(gene = "APOE", pos = "", types = listOf("e2e2", "e2e3", "e2e4", "e3e3", "e3e4", "e4e4")),
                Genotype(gene = "MTHFR", pos = "c.677", types = listOf("CC", "CT", "TT")),
                Genotype(gene = "MTHFR", pos = "c.1298", types = listOf("AA", "AC", "CC")),
                Genotype(gene = "RNF213", pos = "c.14429", types = listOf("GG", "GA", "AA")),
                Genotype(gene = "NOTCH3", pos = "c.1630", types = listOf("CC", "CT", "TT"))))
        val N088 = genomescreenWithRiskScreen(
            code = "N088", name = "고지혈증 지놈 스크린 / 검진", serialGroup = "ST",
            displayName = "GSHPL_고지혈증 지놈 스크린 / 검진",
            genomicDisorders = 	listOf(
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("APOB", "APOA2", "LDLR", "LDLRAP1", "PCSK9", "STAP1")
                ),
                GenomicDisorder("뇌건황색종증", listOf("CYP27A1")),
                GenomicDisorder("시토스테롤혈증", listOf("ABCG5", "ABCG8")),
                GenomicDisorder(
                    "고알파지질단백혈증",
                    listOf("APOC3", "CETP", "SCARB1")
                ),
                GenomicDisorder("스타틴 부작용", listOf("SLCO1B1")),
                GenomicDisorder(
                    "저베타지질단백혈증",
                    listOf("MTTP", "APOB", "SAR1B", "ANGPTL3")
                ),
                GenomicDisorder(
                    "저알파지질단백혈증",
                    listOf("ABCA1", "APOA1", "LCAT")
                ),
                GenomicDisorder("이상베타지질단백혈증", listOf("APOE")),
                GenomicDisorder("복합 고지질혈증", listOf("LPL", "LIPA", "LIPC")),
                GenomicDisorder(
                    "가족성 지질단백 지질분해효소 결핍증",
                    listOf("APOC2", "APOA5", "GPIHBP1", "LMF1", "LPL")
                ),
                GenomicDisorder(
                    "고중성지방혈증",
                    listOf("CREB3L3", "CYP7A1", "GPD1", "GPIHBP1")
                ),
                GenomicDisorder("알스트롬 증후군", listOf("ALMS1"))
            ),
            genotypes = listOf(
                Genotype("APOE", "", listOf("e2e2", "e2e3", "e2e4", "e3e3", "e3e4", "e4e4")),
                Genotype("APOA5", "c.553", listOf("GG", "GT", "TT")),
                Genotype("APOA5", "c.56", listOf("CC", "CG", "GG")),
                Genotype("COQ2", "c.779-1022", listOf("GG", "GC", "CC"))))
        val ON087 = genomescreenWithRiskScreen(
            code = "ON087", name ="뇌졸중 지놈 스크린 / 검진", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder("베타 지중해혈증", listOf("HBB")),
                GenomicDisorder("고호모시스테인혈증", listOf("CBS")),
                GenomicDisorder("파브리병", listOf("GLA")),
                GenomicDisorder("탄력섬유거짓황색종", listOf("ABCC6")),
                GenomicDisorder("카다실", listOf("NOTCH3")),
                GenomicDisorder("카라실", listOf("HTRA1")),
                GenomicDisorder("백색질 형성장애 동반 망막 혈관병증", listOf("TREX1")),
                GenomicDisorder(
                    "심장 긴간격 증후군",
                    listOf("KCNQ1", "KCNJ2", "SCN5A")
                ),
                GenomicDisorder("혈관성 엘러스 단로스 증후군", listOf("COL3A1")),
                GenomicDisorder("마르판 증후군", listOf("FBN1")),
                GenomicDisorder("결절성 다발동맥염", listOf("ADA2")),
                GenomicDisorder("동맥 비틀림 증후군", listOf("SLC2A10")),
                GenomicDisorder(
                    "가족성 편마비 편두통",
                    listOf("CACNA1A", "ATP1A2", "SCN1A")
                ),
                GenomicDisorder(
                    "모야모야병",
                    listOf("RNF213", "ACTA2", "GUCY1A1")
                ),
                GenomicDisorder(
                    "아밀로이드 뇌 혈관병증",
                    listOf("APP", "CST3", "ITM2B")
                ),
                GenomicDisorder("뇌 소혈관질환", listOf("COL4A1")),
                GenomicDisorder("뇌구멍증", listOf("COL4A2")),
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("LDLR", "APOB", "PCSK9", "APOE")
                ),
                GenomicDisorder("FactorVLeiden혈전증", listOf("F5")),
                GenomicDisorder("프로트롬빈 관련 혈전증", listOf("F2")),
                GenomicDisorder("항트롬빈 결핍 혈전증", listOf("SERPINC1")),
                GenomicDisorder("단백질C 결핍 혈전증", listOf("PROC")),
                GenomicDisorder("단백질S 결핍 혈전증", listOf("PROS1"))
            ),
            genotypes = listOf(
                Genotype(gene = "APOE", pos = "", types = listOf("e2e2", "e2e3", "e2e4", "e3e3", "e3e4", "e4e4")),
                Genotype(gene = "MTHFR", pos = "c.677", types = listOf("CC", "CT", "TT")),
                Genotype(gene = "MTHFR", pos = "c.1298", types = listOf("AA", "AC", "CC")),
                Genotype(gene = "RNF213", pos = "c.14429", types = listOf("GG", "GA", "AA")),
                Genotype(gene = "NOTCH3", pos = "c.1630", types = listOf("CC", "CT", "TT"))),
            i18n = Reportable.I18N.EnUs)
        val ON088 = genomescreenWithRiskScreen(
            code ="ON088", name ="고지혈증 지놈 스크린 / 검진", serialGroup = "ST",
            genomicDisorders = listOf(
                GenomicDisorder(
                    "가족성 고콜레스테롤혈증",
                    listOf("APOB", "APOA2", "LDLR", "LDLRAP1", "PCSK9", "STAP1")
                ),
                GenomicDisorder("뇌건황색종증", listOf("CYP27A1")),
                GenomicDisorder("시토스테롤혈증", listOf("ABCG5", "ABCG8")),
                GenomicDisorder(
                    "고알파지질단백혈증",
                    listOf("APOC3", "CETP", "SCARB1")
                ),
                GenomicDisorder("스타틴 부작용", listOf("SLCO1B1")),
                GenomicDisorder(
                    "저베타지질단백혈증",
                    listOf("MTTP", "APOB", "SAR1B", "ANGPTL3")
                ),
                GenomicDisorder(
                    "저알파지질단백혈증",
                    listOf("ABCA1", "APOA1", "LCAT")
                ),
                GenomicDisorder("이상베타지질단백혈증", listOf("APOE")),
                GenomicDisorder("복합 고지질혈증", listOf("LPL", "LIPA", "LIPC")),
                GenomicDisorder(
                    "가족성 지질단백 지질분해효소 결핍증",
                    listOf("APOC2", "APOA5", "GPIHBP1", "LMF1", "LPL")
                ),
                GenomicDisorder(
                    "고중성지방혈증",
                    listOf("CREB3L3", "CYP7A1", "GPD1", "GPIHBP1")
                ),
                GenomicDisorder("알스트롬 증후군", listOf("ALMS1"))
            ),
            genotypes = listOf(
                Genotype("APOE", "", listOf("e2e2", "e2e3", "e2e4", "e3e3", "e3e4", "e4e4")),
                Genotype("APOA5", "c.553", listOf("GG", "GT", "TT")),
                Genotype("APOA5", "c.56", listOf("CC", "CG", "GG")),
                Genotype("COQ2", "c.779-1022", listOf("GG", "GC", "CC"))),
            i18n = Reportable.I18N.EnUs)
        private val J018 = N090.copy(code = "J018", name = "암 지놈 스크린 / 검진 (아이메드)")
        private val J019 = N089.copy(code = "J019", name = "심장 돌연사 지놈 스크린 / 검진 (아이메드)")
        private val J020 = N087.copy(code = "J020", name = "뇌졸중 지놈 스크린 / 검진 (아이메드)")
        private val J021 = N088.copy(code = "J021", name = "고지혈증 지놈 스크린 / 검진 (아이메드)")
        fun values() = listOf(
            N074, N075,
            N087, N088, N089,
            N090,
            N101, N109,
            N111, N112,
            N185,
            ON087, ON088, ON089, ON090,
            J018, J019, J020, J021
        )
    }


}