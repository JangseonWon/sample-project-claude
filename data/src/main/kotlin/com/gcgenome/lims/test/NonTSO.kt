package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.SomaticPanelAnalysisMethod
import com.gcgenome.lims.test.method.SomaticPanelAnalysisMethod.Companion.NonTsoPanelAnalysisMethod

data class NonTSO(
    val code: String,
    val name: String,
    val serialGroup: String,
    val referralDefault: String = "",
    val method: SomaticPanelAnalysisMethod,
    val genesets: List<GeneSet>,
    val nationalInsurance: Boolean = false,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr,
    val limitations: List<String>,
) : SomaticCancerPanel, ContainsGenes, IsPanelAnalysis by method, Interpretable, Reportable {
    val category: HasCategory.Category = HasCategory.Category.NonTSO
    val interpretationCategory: Interpretable.Category = Interpretable.Category.NonTSO
    val reportCategory: Reportable.Category = Reportable.Category.NonTSO
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun title() = name
    override fun category() = category
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun referralDefault() = referralDefault
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun genes() = genesets.flatMap { it.genes }

    companion object {
        val N103 = NonTSO(
            code = "N103",
            name = "비유전성 고형암 유전자 패널II검사(161)",
            serialGroup = "ST2(161)",
            referralDefault = "",
            method = NonTsoPanelAnalysisMethod(
                region = "161 genes(필수: 14 genes, 선택: 147 genes)",
                panel = "Solid Tumor Panel II(161)",
                pipeline = "Oncomine Comprehensive v3 - w3.1.1 - DNA and Fusion - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive v3)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "Hotspot genes (n=87)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK",
                        "AR", "ARAF", "AXL", "BRAF",
                        "BTK", "CBL", "CCND1", "CDK4",
                        "CDK6", "CHEK2", "CSF1R", "CTNNB1",
                        "DDR2", "EGFR", "ERBB2(HER2)", "ERBB3",
                        "ERBB4", "ERCC2", "ESR1", "EZH2",
                        "FGFR1", "FGFR2", "FGFR3", "FGFR4",
                        "FLT3", "FOXL2", "GATA2", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "HIST1H3B",
                        "HNF1A", "HRAS", "IDH1", "IDH2",
                        "JAK1", "JAK2", "JAK3", "KDR",
                        "KIT", "KNSTRN", "KRAS", "MAGOH",
                        "MAP2K1", "MAP2K2", "MAP2K4", "MAPK1",
                        "MAX", "MDM4", "MED12", "MET",
                        "MTOR", "MYC", "MYCN", "MYD88",
                        "NFE2L2", "NRAS", "NTRK1", "NTRK2",
                        "NTRK3", "PDGFRA", "PDGFRB", "PIK3CA",
                        "PIK3CB", "PPP2R1A", "PTPN11", "RAC1",
                        "RAF1", "RET", "RHEB", "RHOA",
                        "ROS1", "SF3B1", "SMAD4", "SMO",
                        "SPOP", "SRC", "STAT3", "TERT",
                        "TOP1", "U2AF1", "XPO1")
                ),
                GeneSet(
                    name = "Full-length genes (n=48)",
                    genes = listOf(
                        "ARID1A", "ATM", "ATR", "ATRX",
                        "BAP1", "BRCA1", "BRCA2", "CDK12",
                        "CDKN1B", "CDKN2A", "CDKN2B", "CHEK1",
                        "CREBBP", "FANCA", "FANCD2", "FANCI",
                        "FBXW7", "MLH1", "MRE11A", "MSH2",
                        "MSH6", "NBN", "NF1", "NF2",
                        "NOTCH1", "NOTCH2", "NOTCH3", "PALB2",
                        "PIK3R1", "PMS2", "POLE", "PTCH1",
                        "PTEN", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RB1", "RNF43",
                        "SETD2", "SLX4", "SMARCA4", "SMARCB1",
                        "STK11", "TSC1", "TSC2", "TP53")
                ),
                GeneSet(
                    name = "Copy number gain genes (n=43)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK",
                        "AR", "AXL", "BRAF", "CCND1",
                        "CCND2", "CCND3", "CCNE1", "CDK2",
                        "EGFR", "ERBB2(HER2)", "KIT", "KRAS",
                        "MYC", "MYCN", "PDGFRA", "CDK4",
                        "CDK6", "ESR1", "FGF19", "FGF3",
                        "FGFR1", "FGFR2", "FGFR3", "FGFR4",
                        "FLT3", "IGF1R", "MDM2", "MDM4",
                        "MET", "MYCL", "NTRK1", "NTRK2",
                        "NTRK3", "PDGFRB", "PIK3CA", "PIK3CB",
                        "PPARG", "RICTOR", "TERT")
                ),
                GeneSet(
                    name = "Fusion genes (n=49)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK",
                        "AR", "BRAF", "BRCA1", "CDKN2A",
                        "EGFR", "ERBB2", "ERBB4", "ERG",
                        "ESR1", "ETV1", "ETV4", "ETV5",
                        "FGFR1", "FGFR2", "FGFR3", "MAP3K8",
                        "MET", "MTAP", "MYB", "MYBL1",
                        "NOTCH1", "NOTCH2", "NOTCH3", "NRG1",
                        "NTRK1", "NTRK2", "NTRK3", "NUTM1",
                        "PIK3CA", "PIK3CB", "PPARG", "PRKACA",
                        "PRKACB", "RAF1", "RARA", "RELA",
                        "RET", "ROS1", "RSPO2", "RSPO3",
                        "STAT6", "TERT", "TFE3", "TFEB",
                        "YAP1")
                )
            ),
            limitations = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, 검사에 포함된 영역의 SNP, small indel 을 검출할 수 있고 검사에 포함되지 않은 영역에 존재하는 변이는 검출할 수 없습니다.",
                "SNV 및 small indel 변이의 검출 한계는 약 5%입니다.",
                "Homopolymer 및 repeat 부위 등 일부 영역에서는 염기서열의 특성상 변이의 검출률이 떨어질 수 있으며, 일부 target region 은 coverage 가 떨어질 가능성이 있습니다.",
                "해당 검사에서 발견된 변이는 Sanger sequencing, ddPCR 등 다른 검사법을 이용하여 재확인을 시행하지 않습니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사에서 발견된 변이는 2017 JMD guideline (J Mol Diagn 2017;19:313-327)에 따라 4단계로 분류하며(tier 1~4), tier 4 변이는 보고하지 않습니다.",
                "CNV calling의 QC 지표가 되는 MAPD (Median of the Absolute values of all Pairwise Differences) score 가 기준값 0.5를 초과하는 경우 CNV 분석은 진행되지 않으며, 0.3을 초과하는 경우 신뢰도는 낮아집니다.",
                "CNV 분석은 copy number gain 만 보고하며 copy number loss 는 보고하지 않습니다. Copy number gain 은 약 4 copy부터 검출 가능합니다.",
                "cDNA qPCR 결과가 기준보다 높은 Ct 값을 보이는 경우 낮은 RNA 농도로 인한 pool imbalance, depth fail 등으로 RNA fusion 결과 보고가 불가능 할 수 있습니다."
            ), nationalInsurance = true
        )
        val N117 = NonTSO(
            code = "N117",
            name = "비유전성 고형암 유전자 패널II검사(액체생검)",
            serialGroup = "PAN",
            referralDefault = "",
            method = NonTsoPanelAnalysisMethod(
                region = "55 genes(필수: 14 genes, 선택: 41 genes)",
                panel = "Oncomine pan-cancer panel + Customized BRCA1, BRCA2 & MYCN",
                pipeline = "Oncomine TagSeq Pan-Cancer Liquid Biopsy w2.0, BRCA1,2_MYCN_AmpliSeq HD for Liquid Biopsy w2.1 + LBx_BRCA_v1",
                method = "Amplicon-based assay"
            ),
            genesets = listOf(
                GeneSet(
                    name = "Hotspot genes (n=40)",
                    genes = listOf("AKT1", "ALK", "AR", "ARAF", "BRAF",
                        "CHEK2", "CTNNB1", "DDR2", "EGFR", "ERBB2", "ERBB3",
                        "ESR1", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "GNA11", "GNAQ", "GNAS", "HRAS", "IDH1", "IDH2",
                        "KIT", "KRAS", "MAP2K1", "MAP2K2", "MET", "MTOR",
                        "NRAS", "NTRK1", "NTRK3", "PDGFRA", "PIK3CA",
                        "RAF1", "RET", "ROS1", "SF3B1", "SMAD4", "SMO")
                ),
                GeneSet(
                    name = "Full-length genes (n=48)",
                    genes = listOf(
                    )
                ),
                GeneSet(
                    name = "Tumor suppressor genes (n=4)",
                    genes = listOf("APC", "FBXW7", "PTEN", "TP53")
                ),
                GeneSet(
                    name = "Copy number gain genes (n=13)",
                    genes = listOf("CCND1", "CCND2", "CCND3", "CDK4",
                        "CDK6", "EGFR", "ERBB2", "FGFR1", "FGFR2",
                        "FGFR3", "MET", "MYC", "MYCN")
                ),
                GeneSet(
                    name = "Fusion genes (n=12)",
                    genes = listOf("ALK", "BRAF", "ERG", "ETV1",
                        "FGFR1", "FGFR2", "FGFR3", "MET", "NTRK1",
                        "NTRK3", "RET", "ROS1")
                ),
                GeneSet(
                    name = "All coding gene (n=2)",
                    genes = listOf("BRCA1", "BRCA2")
                )
            ),
            limitations = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, 검사에 포함된 영역의 SNP, small indel, copy number variation (CNV), gene rearrangement을 검출할 수 있고 검사에 포함되지 않은 영역에 존재하는 변이는 검출할 수 없습니다.",
                "SNV 및 small indel 변이의 검출한계는 추출된 검체의 DNA 양에 따라 다르며, input cfDNA 농도에 따라 약 0.1~2.0%의 limit of detection (LOD) 값을 보이며, 종양분율이 낮을 경우 변이가 검출되지 않을 수 있으므로 clinical correlation이 권고됩니다.",
                "본 검사에 포함된 선택 유전자 중 tumor suppressor genes에 해당하는 4개 유전자 (APC, FBXW7, PTEN, TP53)는 유전자 전체 영역을 포함하지는 않으나, 주요한 병원성 변이의 대부분을 포함합니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사에서 발견된 변이는 2017 JMD guideline (J Mol Diagn 2017;19:313-327)에 따라 4단계로 분류하며(tier 1~4), tier 4 변이는 보고하지 않습니다."
            ), nationalInsurance = true
        )
        val N124 = NonTSO(
            code = "N124",
            name = "비유전성 고형암 유전자 패널II검사(TMB/MSI)(RNA 포함)",
            serialGroup = "TMB_R",
            referralDefault = "",
            method = NonTsoPanelAnalysisMethod(
                region = "427 genes(필수: 14 genes, 선택: 413 genes)",
                panel = "Solid Tumor Panel II(TMB/MSI)(RNA included)",
                pipeline = "Oncomine Comprehensive Plus - w.2.5 - DNA and Fusions - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive Plus)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "CDS (n=227)",
                    genes = listOf("ABRAXAS1", "ACVR1B", "ACVR2A", "ADAMTS12", "ADAMTS2",
                        "AMER1", "APC", "ARHGAP35", "ARID1A", "ARID1B",
                        "ARID2", "ARID5B", "ASXL1", "ASXL2", "ATM",
                        "ATR", "ATRX", "AXIN1", "AXIN2", "B2M",
                        "BAP1", "BARD1", "BCOR", "BLM", "BMPR2",
                        "BRCA1", "BRCA2", "BRIP1", "CALR", "CASP8",
                        "CBFB", "CD274", "CD276", "CDC73", "CDH1",
                        "CDH10", "CDK12", "CDKN1A", "CDKN1B", "CDKN2A",
                        "CDKN2B", "CDKN2C", "CHEK1", "CHEK2", "CIC",
                        "CIITA", "CREBBP", "CSMD3", "CTCF", "CTLA4",
                        "CUL3", "CUL4A", "CUL4B", "CYLD", "CYP2C9",
                        "CYP2D6", "DAXX", "DDX3X", "DICER1", "DNMT3A",
                        "DOCK3", "DPYD", "DSC1", "DSC3", "ELF3",
                        "ENO1", "EP300", "EPCAM", "EPHA2", "ERAP1",
                        "ERAP2", "ERCC2", "ERCC4", "ERCC5", "ERRFI1",
                        "ETV6", "FANCA", "FANCC", "FANCD2", "FANCE",
                        "FANCF", "FANCG", "FANCI", "FANCL", "FANCM",
                        "FAS", "FAT1", "FBXW7", "FUBP1", "GATA3",
                        "GNA13", "GPS2", "HDAC2", "HDAC9", "HLA-A",
                        "HLA-B", "HNF1A", "ID3", "INPP4B", "JAK1",
                        "JAK2", "JAK3", "KDM5C", "KDM6A", "KEAP1",
                        "KLHL13", "KMT2A", "KMT2B", "KMT2C", "KMT2D",
                        "LARP4B", "LATS1", "LATS2", "MAP2K4", "MAP2K7",
                        "MAP3K1", "MAP3K4",
                        "MAPK8", "MEN1", "MGA", "MLH1", "MLH3",
                        "MRE11", "MSH2", "MSH3", "MSH6", "MTAP",
                        "MTUS2", "MUTYH", "NBN", "NCOR1", "NF1",
                        "NF2", "NOTCH1", "NOTCH2", "NOTCH3", "NOTCH4",
                        "PALB2", "PARP1", "PARP2", "PARP3", "PARP4",
                        "PBRM1", "PDCD1", "PDCD1LG2", "PDIA3", "PGD",
                        "PHF6", "PIK3R1", "PMS1", "PMS2", "POLD1", "POLE",
                        "POT1", "PPM1D", "PPP2R2A", "PRDM1", "PRDM9",
                        "PRKAR1A", "PSMB10", "PSMB8", "PSMB9", "PTCH1",
                        "PTEN", "PTPRT", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RAD52", "RAD54L", "RASA1",
                        "RASA2", "RB1", "RBM10", "RECQL4", "RNASEH2A",
                        "RNASEH2B", "RNASEH2C", "RNF43", "RPA1", "RPL22",
                        "RPL5", "RUNX1", "RUNX1T1", "SDHA", "SDHB",
                        "SDHC", "SDHD", "SETD2", "SLX4", "SMAD2",
                        "SMAD4", "SMARCA4", "SMARCB1", "SOCS1", "SOX9",
                        "SPEN", "STAG2", "STAT1", "STK11", "SUFU",
                        "TAP1", "TAP2", "TBX3", "TCF7L2", "TET2",
                        "TGFBR2", "TMEM132D", "TNFAIP3", "TNFRSF14", "TP53",
                        "TP63", "TPP2", "TSC1", "TSC2", "UGT1A1",
                        "USP9X", "VHL", "WT1", "XRCC2", "XRCC3",
                        "ZBTB20", "ZFHX3", "ZMYM3", "ZRSR2")
                ),
                GeneSet(
                    name = "Hotspot mutations (n=165)",
                    genes = listOf(
                        "ABL1", "ABL2", "ACVR1", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "ATP1A1", "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BCR",
                        "BMP5", "BRAF", "BTK", "CACNA1D", "CARD11", "CBL", "CCND1", "CCND2",
                        "CCND3", "CCNE1", "CD79B", "CDK4", "CDK6", "CHD4", "CSF1R", "CTNNB1",
                        "CUL1", "CYSLTR2", "DDR2", "DGCR8", "DROSHA", "E2F1", "EGFR",
                        "EIF1AX", "EPAS1", "ERBB2", "ERBB3", "ERBB4", "ESR1", "EZH2",
                        "FAM135B", "FGF7", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "FLT4", "FOXA1", "FOXL2", "FOXO1", "GATA2", "GLI1", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "H3F3B", "HIF1A", "HIST1H2BD", "HIST1H3B",
                        "HRAS", "IDH1", "IDH2", "IKBKB", "IL6ST", "IL7R", "IRF4", "IRS4",
                        "KDR", "KIT", "KLF4", "KLF5", "KNSTRN", "KRAS", "MAGOH", "MAP2K1",
                        "MAP2K2", "MAPK1", "MAX", "MDM4", "MECOM", "MED12", "MEF2B",
                        "MET", "MITF", "MPL", "MTOR", "MYC", "MYCN", "MYD88", "MYOD1",
                        "NFE2L2", "NRAS", "NSD2", "NT5C2", "NTRK1", "NTRK2", "NTRK3",
                        "NUP93", "PAX5", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B", "PIK3CA",
                        "PIK3CB", "PIK3CD", "PIK3CG", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A",
                        "PPP6C", "PRKACA", "PTPN11", "PTPRD", "PXDNL", "RAC1", "RAF1",
                        "RARA", "RET", "RGS7", "RHEB", "RHOA", "RICTOR", "RIT1", "ROS1",
                        "RPL10", "SETBP1", "SF3B1", "SIX1", "SIX2", "SLCO1B3", "SMC1A",
                        "SMO", "SNCAIP", "SOS1", "SOX2", "SPOP", "SRC", "SRSF2", "STAT3",
                        "STAT5B", "STAT6", "TAF1", "TERT", "TGFBR1", "TOP1", "TPMT",
                        "TRRAP", "TSHR", "U2AF1", "USP8", "WAS", "XPO1", "ZNF217", "ZNF429"
                    )
                ),
                GeneSet(
                    name = "CNV Gain (n=127)",
                    genes = listOf(
                        "ABCB1", "ABL1", "ABL2", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BRAF", "CARD11",
                        "CBL", "CCND1", "CCND2", "CCND3", "CCNE1", "CDK4", "CDK6", "CHD4",
                        "CTNND2", "DDR1", "DDR2", "EGFR", "EIF1AX", "EMSY", "ERBB2", "ERBB3",
                        "ERBB4", "ESR1", "EZH2", "FAM135B", "FGF19", "FGF23", "FGF3", "FGF4",
                        "FGF9", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3", "FLT4", "FOXA1",
                        "FYN", "GATA2", "GLI3", "GNAS", "H3F3A", "H3F3B", "IDH2", "IGF1R",
                        "IKBKB", "IL7R", "KDR", "KIT", "KLF5", "KRAS", "MAGOH", "MAP2K1",
                        "MAPK1", "MAX", "MCL1", "MDM2", "MDM4", "MECOM", "MEF2B", "MET",
                        "MITF", "MPL", "MTOR", "MYC", "MYCL", "MYCN", "MYD88", "NFE2L2",
                        "NRAS", "NTRK1", "NTRK3", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B",
                        "PIK3CA", "PIK3CB", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A", "PPP6C",
                        "PRKACA", "PTPN11", "PXDNL", "RAC1", "RAF1", "RARA", "RET", "RHEB",
                        "RICTOR", "RIT1", "ROS1", "RPS6KB1", "RPTOR", "SETBP1", "SF3B1",
                        "SLCO1B3", "SMC1A", "SMO", "SPOP", "SRC", "STAT3", "STAT6", "TERT",
                        "TOP1", "TPMT", "U2AF1", "USP8", "XPO1", "YAP1", "YES1", "ZNF217",
                        "ZNF429")
                ),
                GeneSet(
                    name = "RNA fusion (n=49)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK", "AR", "BRAF", "BRCA1", "CDKN2A",
                        "EGFR", "ERBB2", "ERBB4", "ERG", "ESR1", "ETV1", "ETV4", "ETV5",
                        "FGFR1", "FGFR2", "FGFR3", "MAP3K8", "MET", "MTAP", "MYB", "MYBL1",
                        "NOTCH1", "NOTCH2", "NOTCH3", "NRG1", "NTRK1", "NTRK2", "NTRK3",
                        "NUTM1", "PIK3CA", "PIK3CB", "PPARG", "PRKACA", "PRKACB", "RAF1",
                        "RARA", "RELA", "RET", "ROS1", "RSPO2", "RSPO3", "STAT6", "TERT",
                        "TFE3", "TFEB", "YAP1")
                )
            ),
            limitations = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, 검사에 포함된 영역의 SNP, small indel 을 검출할 수 있고 검사에 포함되지 않은 영역에 존재하는 변이는 검출할 수 없습니다.",
                "SNV 및 small indel 변이의 검출 한계는 약 5%입니다.",
                "Homopolymer 및 repeat 부위 등 일부 영역에서는 염기서열의 특성상 변이의 검출률이 떨어질 수 있으며, 일부 target region 은 coverage 가 떨어질 가능성이 있습니다.",
                "해당 검사에서 발견된 변이는 Sanger sequencing, ddPCR 등 다른 검사법을 이용하여 재확인을 시행하지 않습니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사에서 발견된 변이는 2017 JMD guideline (J Mol Diagn 2017;19:313-327)에 따라 4단계로 분류하며(tier 1~4), tier 4 변이는 보고하지 않습니다.",
                "CNV calling의 QC 지표가 되는 MAPD (Median of the Absolute values of all Pairwise Differences) score 가 기준값 0.5를 초과하는 경우 CNV 분석은 진행되지 않으며, 0.3을 초과하는 경우 신뢰도는 낮아집니다.",
                "CNV 분석은 copy number gain 만 보고하며 copy number loss 는 보고하지 않습니다. Copy number gain 은 약 4 copy부터 검출 가능합니다.",
                "cDNA qPCR 결과가 기준보다 높은 Ct 값을 보이는 경우 낮은 RNA 농도로 인한 pool imbalance, depth fail 등으로 RNA fusion 결과 보고가 불가능 할 수 있습니다.",
                "MSI 결과는 MSI-High 및 그 외 MSS 로 보고됩니다. 76개의 MSI biomarker 가 충분히 cover 되지 않은 경우 MSI 결과는 no call 또는 QC fail 로 보고됩니다. 제조사에서 시행한 192개 샘플을 이용한 MSI 검증 결과 96%의 민감도, 99%의 특이도를 보이는 것으로 확인되었습니다.",
                "TMB score는 1,000,000 base pair 당 검출된 mutation의 개수입니다. TMB-low, TMB-high의 기준은 아직 정의할 수 없습니다."
            ), nationalInsurance = true
        )
        val G2200102 = NonTSO(
            code = "G2200102",
            name = "비유전성 고형암 유전자 패널II검사(TMB/MSI)(RNA 포함)",
            serialGroup = "TMB_R",
            method = NonTsoPanelAnalysisMethod(
                region = "427 genes(필수: 14 genes, 선택: 413 genes)",
                panel = "Solid Tumor Panel II(TMB/MSI)(RNA included)",
                pipeline = "Oncomine Comprehensive Plus - w.2.3 - DNA and Fusions - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive Plus)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "CDS (n=227)",
                    genes = listOf("ABRAXAS1", "ACVR1B", "ACVR2A", "ADAMTS12", "ADAMTS2",
                        "AMER1", "APC", "ARHGAP35", "ARID1A", "ARID1B",
                        "ARID2", "ARID5B", "ASXL1", "ASXL2", "ATM",
                        "ATR", "ATRX", "AXIN1", "AXIN2", "B2M",
                        "BAP1", "BARD1", "BCOR", "BLM", "BMPR2",
                        "BRCA1", "BRCA2", "BRIP1", "CALR", "CASP8",
                        "CBFB", "CD274", "CD276", "CDC73", "CDH1",
                        "CDH10", "CDK12", "CDKN1A", "CDKN1B", "CDKN2A",
                        "CDKN2B", "CDKN2C", "CHEK1", "CHEK2", "CIC",
                        "CIITA", "CREBBP", "CSMD3", "CTCF", "CTLA4",
                        "CUL3", "CUL4A", "CUL4B", "CYLD", "CYP2C9",
                        "CYP2D6", "DAXX", "DDX3X", "DICER1", "DNMT3A",
                        "DOCK3", "DPYD", "DSC1", "DSC3", "ELF3",
                        "ENO1", "EP300", "EPCAM", "EPHA2", "ERAP1",
                        "ERAP2", "ERCC2", "ERCC4", "ERCC5", "ERRFI1",
                        "ETV6", "FANCA", "FANCC", "FANCD2", "FANCE",
                        "FANCF", "FANCG", "FANCI", "FANCL", "FANCM",
                        "FAS", "FAT1", "FBXW7", "FUBP1", "GATA3",
                        "GNA13", "GPS2", "HDAC2", "HDAC9", "HLA-A",
                        "HLA-B", "HNF1A", "ID3", "INPP4B", "JAK1",
                        "JAK2", "JAK3", "KDM5C", "KDM6A", "KEAP1",
                        "KLHL13", "KMT2A", "KMT2B", "KMT2C", "KMT2D",
                        "LARP4B", "LATS1", "LATS2", "MAP2K4", "MAP2K7",
                        "MAP3K1", "MAP3K4",
                        "MAPK8", "MEN1", "MGA", "MLH1", "MLH3",
                        "MRE11", "MSH2", "MSH3", "MSH6", "MTAP",
                        "MTUS2", "MUTYH", "NBN", "NCOR1", "NF1",
                        "NF2", "NOTCH1", "NOTCH2", "NOTCH3", "NOTCH4",
                        "PALB2", "PARP1", "PARP2", "PARP3", "PARP4",
                        "PBRM1", "PDCD1", "PDCD1LG2", "PDIA3", "PGD",
                        "PHF6", "PIK3R1", "PMS1", "PMS2", "POLD1", "POLE",
                        "POT1", "PPM1D", "PPP2R2A", "PRDM1", "PRDM9",
                        "PRKAR1A", "PSMB10", "PSMB8", "PSMB9", "PTCH1",
                        "PTEN", "PTPRT", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RAD52", "RAD54L", "RASA1",
                        "RASA2", "RB1", "RBM10", "RECQL4", "RNASEH2A",
                        "RNASEH2B", "RNASEH2C", "RNF43", "RPA1", "RPL22",
                        "RPL5", "RUNX1", "RUNX1T1", "SDHA", "SDHB",
                        "SDHC", "SDHD", "SETD2", "SLX4", "SMAD2",
                        "SMAD4", "SMARCA4", "SMARCB1", "SOCS1", "SOX9",
                        "SPEN", "STAG2", "STAT1", "STK11", "SUFU",
                        "TAP1", "TAP2", "TBX3", "TCF7L2", "TET2",
                        "TGFBR2", "TMEM132D", "TNFAIP3", "TNFRSF14", "TP53",
                        "TP63", "TPP2", "TSC1", "TSC2", "UGT1A1",
                        "USP9X", "VHL", "WT1", "XRCC2", "XRCC3",
                        "ZBTB20", "ZFHX3", "ZMYM3", "ZRSR2")
                ),
                GeneSet(
                    name = "Hotspot mutations (n=165)",
                    genes = listOf(
                        "ABL1", "ABL2", "ACVR1", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "ATP1A1", "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BCR",
                        "BMP5", "BRAF", "BTK", "CACNA1D", "CARD11", "CBL", "CCND1", "CCND2",
                        "CCND3", "CCNE1", "CD79B", "CDK4", "CDK6", "CHD4", "CSF1R", "CTNNB1",
                        "CUL1", "CYSLTR2", "DDR2", "DGCR8", "DROSHA", "E2F1", "EGFR",
                        "EIF1AX", "EPAS1", "ERBB2", "ERBB3", "ERBB4", "ESR1", "EZH2",
                        "FAM135B", "FGF7", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "FLT4", "FOXA1", "FOXL2", "FOXO1", "GATA2", "GLI1", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "H3F3B", "HIF1A", "HIST1H2BD", "HIST1H3B",
                        "HRAS", "IDH1", "IDH2", "IKBKB", "IL6ST", "IL7R", "IRF4", "IRS4",
                        "KDR", "KIT", "KLF4", "KLF5", "KNSTRN", "KRAS", "MAGOH", "MAP2K1",
                        "MAP2K2", "MAPK1", "MAX", "MDM4", "MECOM", "MED12", "MEF2B",
                        "MET", "MITF", "MPL", "MTOR", "MYC", "MYCN", "MYD88", "MYOD1",
                        "NFE2L2", "NRAS", "NSD2", "NT5C2", "NTRK1", "NTRK2", "NTRK3",
                        "NUP93", "PAX5", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B", "PIK3CA",
                        "PIK3CB", "PIK3CD", "PIK3CG", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A",
                        "PPP6C", "PRKACA", "PTPN11", "PTPRD", "PXDNL", "RAC1", "RAF1",
                        "RARA", "RET", "RGS7", "RHEB", "RHOA", "RICTOR", "RIT1", "ROS1",
                        "RPL10", "SETBP1", "SF3B1", "SIX1", "SIX2", "SLCO1B3", "SMC1A",
                        "SMO", "SNCAIP", "SOS1", "SOX2", "SPOP", "SRC", "SRSF2", "STAT3",
                        "STAT5B", "STAT6", "TAF1", "TERT", "TGFBR1", "TOP1", "TPMT",
                        "TRRAP", "TSHR", "U2AF1", "USP8", "WAS", "XPO1", "ZNF217", "ZNF429"
                    )
                ),
                GeneSet(
                    name = "CNV Gain (n=127)",
                    genes = listOf(
                        "ABCB1", "ABL1", "ABL2", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BRAF", "CARD11",
                        "CBL", "CCND1", "CCND2", "CCND3", "CCNE1", "CDK4", "CDK6", "CHD4",
                        "CTNND2", "DDR1", "DDR2", "EGFR", "EIF1AX", "EMSY", "ERBB2", "ERBB3",
                        "ERBB4", "ESR1", "EZH2", "FAM135B", "FGF19", "FGF23", "FGF3", "FGF4",
                        "FGF9", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3", "FLT4", "FOXA1",
                        "FYN", "GATA2", "GLI3", "GNAS", "H3F3A", "H3F3B", "IDH2", "IGF1R",
                        "IKBKB", "IL7R", "KDR", "KIT", "KLF5", "KRAS", "MAGOH", "MAP2K1",
                        "MAPK1", "MAX", "MCL1", "MDM2", "MDM4", "MECOM", "MEF2B", "MET",
                        "MITF", "MPL", "MTOR", "MYC", "MYCL", "MYCN", "MYD88", "NFE2L2",
                        "NRAS", "NTRK1", "NTRK3", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B",
                        "PIK3CA", "PIK3CB", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A", "PPP6C",
                        "PRKACA", "PTPN11", "PXDNL", "RAC1", "RAF1", "RARA", "RET", "RHEB",
                        "RICTOR", "RIT1", "ROS1", "RPS6KB1", "RPTOR", "SETBP1", "SF3B1",
                        "SLCO1B3", "SMC1A", "SMO", "SPOP", "SRC", "STAT3", "STAT6", "TERT",
                        "TOP1", "TPMT", "U2AF1", "USP8", "XPO1", "YAP1", "YES1", "ZNF217",
                        "ZNF429")
                ),
                GeneSet(
                    name = "RNA fusion (n=49)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK", "AR", "BRAF", "BRCA1", "CDKN2A",
                        "EGFR", "ERBB2", "ERBB4", "ERG", "ESR1", "ETV1", "ETV4", "ETV5",
                        "FGFR1", "FGFR2", "FGFR3", "MAP3K8", "MET", "MTAP", "MYB", "MYBL1",
                        "NOTCH1", "NOTCH2", "NOTCH3", "NRG1", "NTRK1", "NTRK2", "NTRK3",
                        "NUTM1", "PIK3CA", "PIK3CB", "PPARG", "PRKACA", "PRKACB", "RAF1",
                        "RARA", "RELA", "RET", "ROS1", "RSPO2", "RSPO3", "STAT6", "TERT",
                        "TFE3", "TFEB", "YAP1")
                )
            ),
            limitations = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, 검사에 포함된 영역의 SNP, small indel 을 검출할 수 있고 검사에 포함되지 않은 영역에 존재하는 변이는 검출할 수 없습니다.",
                "SNV 및 small indel 변이의 검출 한계는 약 5%입니다.",
                "Homopolymer 및 repeat 부위 등 일부 영역에서는 염기서열의 특성상 변이의 검출률이 떨어질 수 있으며, 일부 target region 은 coverage 가 떨어질 가능성이 있습니다.",
                "해당 검사에서 발견된 변이는 Sanger sequencing, ddPCR 등 다른 검사법을 이용하여 재확인을 시행하지 않습니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사에서 발견된 변이는 2017 JMD guideline (J Mol Diagn 2017;19:313-327)에 따라 4단계로 분류하며(tier 1~4), tier 4 변이는 보고하지 않습니다.",
                "CNV calling의 QC 지표가 되는 MAPD (Median of the Absolute values of all Pairwise Differences) score 가 기준값 0.5를 초과하는 경우 CNV 분석은 진행되지 않으며, 0.3을 초과하는 경우 신뢰도는 낮아집니다.",
                "CNV 분석은 copy number gain 만 보고하며 copy number loss 는 보고하지 않습니다. Copy number gain 은 약 4 copy부터 검출 가능합니다.",
                "cDNA qPCR 결과가 기준보다 높은 Ct 값을 보이는 경우 낮은 RNA 농도로 인한 pool imbalance, depth fail 등으로 RNA fusion 결과 보고가 불가능 할 수 있습니다.",
                "MSI 결과는 MSI-High 및 그 외 MSS 로 보고됩니다. 76개의 MSI biomarker 가 충분히 cover 되지 않은 경우 MSI 결과는 no call 또는 QC fail 로 보고됩니다. 제조사에서 시행한 192개 샘플을 이용한 MSI 검증 결과 96%의 민감도, 99%의 특이도를 보이는 것으로 확인되었습니다.",
                "TMB score는 1,000,000 base pair 당 검출된 mutation의 개수입니다. TMB-low, TMB-high의 기준은 아직 정의할 수 없습니다."
            )
        )
        val N125 = NonTSO(
            code = "N125",
            name = "비유전성 고형암 유전자 패널II검사(TMB/MSI)(RNA 미포함)",
            serialGroup = "TMB_D",
            method = NonTsoPanelAnalysisMethod(
                region = "411 genes(필수: 14 genes, 선택: 397 genes)",
                panel = "Solid Tumor Panel II(TMB/MSI)(RNA not included)",
                pipeline = "Oncomine Comprehensive Plus - w.2.5 - DNA - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive Plus)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "CDS (n=227)",
                    genes = listOf("ABRAXAS1", "ACVR1B", "ACVR2A", "ADAMTS12", "ADAMTS2",
                        "AMER1", "APC", "ARHGAP35", "ARID1A", "ARID1B",
                        "ARID2", "ARID5B", "ASXL1", "ASXL2", "ATM",
                        "ATR", "ATRX", "AXIN1", "AXIN2", "B2M",
                        "BAP1", "BARD1", "BCOR", "BLM", "BMPR2",
                        "BRCA1", "BRCA2", "BRIP1", "CALR", "CASP8",
                        "CBFB", "CD274", "CD276", "CDC73", "CDH1",
                        "CDH10", "CDK12", "CDKN1A", "CDKN1B", "CDKN2A",
                        "CDKN2B", "CDKN2C", "CHEK1", "CHEK2", "CIC",
                        "CIITA", "CREBBP", "CSMD3", "CTCF", "CTLA4",
                        "CUL3", "CUL4A", "CUL4B", "CYLD", "CYP2C9",
                        "CYP2D6", "DAXX", "DDX3X", "DICER1", "DNMT3A",
                        "DOCK3", "DPYD", "DSC1", "DSC3", "ELF3",
                        "ENO1", "EP300", "EPCAM", "EPHA2", "ERAP1",
                        "ERAP2", "ERCC2", "ERCC4", "ERCC5", "ERRFI1",
                        "ETV6", "FANCA", "FANCC", "FANCD2", "FANCE",
                        "FANCF", "FANCG", "FANCI", "FANCL", "FANCM",
                        "FAS", "FAT1", "FBXW7", "FUBP1", "GATA3",
                        "GNA13", "GPS2", "HDAC2", "HDAC9", "HLA-A",
                        "HLA-B", "HNF1A", "ID3", "INPP4B", "JAK1",
                        "JAK2", "JAK3", "KDM5C", "KDM6A", "KEAP1",
                        "KLHL13", "KMT2A", "KMT2B", "KMT2C", "KMT2D",
                        "LARP4B", "LATS1", "LATS2", "MAP2K4", "MAP2K7",
                        "MAP3K1", "MAP3K4",
                        "MAPK8", "MEN1", "MGA", "MLH1", "MLH3",
                        "MRE11", "MSH2", "MSH3", "MSH6", "MTAP",
                        "MTUS2", "MUTYH", "NBN", "NCOR1", "NF1",
                        "NF2", "NOTCH1", "NOTCH2", "NOTCH3", "NOTCH4",
                        "PALB2", "PARP1", "PARP2", "PARP3", "PARP4",
                        "PBRM1", "PDCD1", "PDCD1LG2", "PDIA3", "PGD",
                        "PHF6", "PIK3R1", "PMS1", "PMS2", "POLD1", "POLE",
                        "POT1", "PPM1D", "PPP2R2A", "PRDM1", "PRDM9",
                        "PRKAR1A", "PSMB10", "PSMB8", "PSMB9", "PTCH1",
                        "PTEN", "PTPRT", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RAD52", "RAD54L", "RASA1",
                        "RASA2", "RB1", "RBM10", "RECQL4", "RNASEH2A",
                        "RNASEH2B", "RNASEH2C", "RNF43", "RPA1", "RPL22",
                        "RPL5", "RUNX1", "RUNX1T1", "SDHA", "SDHB",
                        "SDHC", "SDHD", "SETD2", "SLX4", "SMAD2",
                        "SMAD4", "SMARCA4", "SMARCB1", "SOCS1", "SOX9",
                        "SPEN", "STAG2", "STAT1", "STK11", "SUFU",
                        "TAP1", "TAP2", "TBX3", "TCF7L2", "TET2",
                        "TGFBR2", "TMEM132D", "TNFAIP3", "TNFRSF14", "TP53",
                        "TP63", "TPP2", "TSC1", "TSC2", "UGT1A1",
                        "USP9X", "VHL", "WT1", "XRCC2", "XRCC3",
                        "ZBTB20", "ZFHX3", "ZMYM3", "ZRSR2")
                ),
                GeneSet(
                    name = "Hotspot mutations (n=165)",
                    genes = listOf(
                        "ABL1", "ABL2", "ACVR1", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "ATP1A1", "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BCR",
                        "BMP5", "BRAF", "BTK", "CACNA1D", "CARD11", "CBL", "CCND1", "CCND2",
                        "CCND3", "CCNE1", "CD79B", "CDK4", "CDK6", "CHD4", "CSF1R", "CTNNB1",
                        "CUL1", "CYSLTR2", "DDR2", "DGCR8", "DROSHA", "E2F1", "EGFR",
                        "EIF1AX", "EPAS1", "ERBB2", "ERBB3", "ERBB4", "ESR1", "EZH2",
                        "FAM135B", "FGF7", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "FLT4", "FOXA1", "FOXL2", "FOXO1", "GATA2", "GLI1", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "H3F3B", "HIF1A", "HIST1H2BD", "HIST1H3B",
                        "HRAS", "IDH1", "IDH2", "IKBKB", "IL6ST", "IL7R", "IRF4", "IRS4",
                        "KDR", "KIT", "KLF4", "KLF5", "KNSTRN", "KRAS", "MAGOH", "MAP2K1",
                        "MAP2K2", "MAPK1", "MAX", "MDM4", "MECOM", "MED12", "MEF2B",
                        "MET", "MITF", "MPL", "MTOR", "MYC", "MYCN", "MYD88", "MYOD1",
                        "NFE2L2", "NRAS", "NSD2", "NT5C2", "NTRK1", "NTRK2", "NTRK3",
                        "NUP93", "PAX5", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B", "PIK3CA",
                        "PIK3CB", "PIK3CD", "PIK3CG", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A",
                        "PPP6C", "PRKACA", "PTPN11", "PTPRD", "PXDNL", "RAC1", "RAF1",
                        "RARA", "RET", "RGS7", "RHEB", "RHOA", "RICTOR", "RIT1", "ROS1",
                        "RPL10", "SETBP1", "SF3B1", "SIX1", "SIX2", "SLCO1B3", "SMC1A",
                        "SMO", "SNCAIP", "SOS1", "SOX2", "SPOP", "SRC", "SRSF2", "STAT3",
                        "STAT5B", "STAT6", "TAF1", "TERT", "TGFBR1", "TOP1", "TPMT",
                        "TRRAP", "TSHR", "U2AF1", "USP8", "WAS", "XPO1", "ZNF217", "ZNF429"
                    )
                ),
                GeneSet(
                    name = "CNV Gain (n=127)",
                    genes = listOf(
                        "ABCB1", "ABL1", "ABL2", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BRAF", "CARD11",
                        "CBL", "CCND1", "CCND2", "CCND3", "CCNE1", "CDK4", "CDK6", "CHD4",
                        "CTNND2", "DDR1", "DDR2", "EGFR", "EIF1AX", "EMSY", "ERBB2", "ERBB3",
                        "ERBB4", "ESR1", "EZH2", "FAM135B", "FGF19", "FGF23", "FGF3", "FGF4",
                        "FGF9", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3", "FLT4", "FOXA1",
                        "FYN", "GATA2", "GLI3", "GNAS", "H3F3A", "H3F3B", "IDH2", "IGF1R",
                        "IKBKB", "IL7R", "KDR", "KIT", "KLF5", "KRAS", "MAGOH", "MAP2K1",
                        "MAPK1", "MAX", "MCL1", "MDM2", "MDM4", "MECOM", "MEF2B", "MET",
                        "MITF", "MPL", "MTOR", "MYC", "MYCL", "MYCN", "MYD88", "NFE2L2",
                        "NRAS", "NTRK1", "NTRK3", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B",
                        "PIK3CA", "PIK3CB", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A", "PPP6C",
                        "PRKACA", "PTPN11", "PXDNL", "RAC1", "RAF1", "RARA", "RET", "RHEB",
                        "RICTOR", "RIT1", "ROS1", "RPS6KB1", "RPTOR", "SETBP1", "SF3B1",
                        "SLCO1B3", "SMC1A", "SMO", "SPOP", "SRC", "STAT3", "STAT6", "TERT",
                        "TOP1", "TPMT", "U2AF1", "USP8", "XPO1", "YAP1", "YES1", "ZNF217",
                        "ZNF429")
                )
            ),
            limitations = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, 검사에 포함된 영역의 SNP, small indel 을 검출할 수 있고 검사에 포함되지 않은 영역에 존재하는 변이는 검출할 수 없습니다.",
                "SNV 및 small indel 변이의 검출 한계는 약 5%입니다.",
                "Homopolymer 및 repeat 부위 등 일부 영역에서는 염기서열의 특성상 변이의 검출률이 떨어질 수 있으며, 일부 target region 은 coverage 가 떨어질 가능성이 있습니다.",
                "해당 검사에서 발견된 변이는 Sanger sequencing, ddPCR 등 다른 검사법을 이용하여 재확인을 시행하지 않습니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사로 germline 변이와 somatic 변이를 감별할 수 없으며 variant allele frequency 가 50% 혹은 100%에 가까운 경우 germline variant 의 가능성을 배제할 수 없습니다.",
                "본 검사에서 발견된 변이는 2017 JMD guideline (J Mol Diagn 2017;19:313-327)에 따라 4단계로 분류하며(tier 1~4), tier 4 변이는 보고하지 않습니다.",
                "CNV calling의 QC 지표가 되는 MAPD (Median of the Absolute values of all Pairwise Differences) score 가 기준값 0.5를 초과하는 경우 CNV 분석은 진행되지 않으며, 0.3을 초과하는 경우 신뢰도는 낮아집니다.",
                "CNV 분석은 copy number gain 만 보고하며 copy number loss 는 보고하지 않습니다. Copy number gain 은 약 4 copy부터 검출 가능합니다.",
                "MSI 결과는 MSI-High 및 그 외 MSS 로 보고됩니다. 76개의 MSI biomarker 가 충분히 cover 되지 않은 경우 MSI 결과는 no call 또는 QC fail 로 보고됩니다. 제조사에서 시행한 192개 샘플을 이용한 MSI 검증 결과 96%의 민감도, 99%의 특이도를 보이는 것으로 확인되었습니다.",
                "TMB score는 1,000,000 base pair 당 검출된 mutation의 개수입니다. TMB-low, TMB-high의 기준은 아직 정의할 수 없습니다."
            ), nationalInsurance = true
        )
        val O004 = NonTSO(
            code = "O004",
            name = "GCG Oncomine Pan-Cancer Cell-Free Assay",
            serialGroup = "PAN",
            referralDefault = "",
            method = NonTsoPanelAnalysisMethod(
                region = "52 genes",
                panel = "Oncomine pan-cancer panel(RUO)",
                pipeline = "Oncomine TagSeq Pan-Cancer Liquid Biopsy w2.0",
                method = "Amplicon-based assay"
            ),
            genesets = listOf(
                GeneSet(
                    name = "Hotspot genes (n=40)",
                    genes = listOf("AKT1", "ALK", "AR", "ARAF", "BRAF",
                        "CHEK2", "CTNNB1", "DDR2", "EGFR", "ERBB2", "ERBB3",
                        "ESR1", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "GNA11", "GNAQ", "GNAS", "HRAS", "IDH1", "IDH2",
                        "KIT", "KRAS", "MAP2K1", "MAP2K2", "MET", "MTOR",
                        "NRAS", "NTRK1", "NTRK3", "PDGFRA", "PIK3CA",
                        "RAF1", "RET", "ROS1", "SF3B1", "SMAD4", "SMO")
                ),
                GeneSet(
                    name = "Tumor suppressor genes (n=4)",
                    genes = listOf("APC", "FBXW7", "PTEN", "TP53")
                ),
                GeneSet(
                    name = "Copy number gain genes (n=12)",
                    genes = listOf("CCND1", "CCND2", "CCND3", "CDK4",
                        "CDK6", "EGFR", "ERBB2", "FGFR1", "FGFR2",
                        "FGFR3", "MET", "MYC")
                ),
                GeneSet(
                    name = "Fusion genes (n=12)",
                    genes = listOf("ALK", "BRAF", "ERG", "ETV1",
                        "FGFR1", "FGFR2", "FGFR3", "MET", "NTRK1",
                        "NTRK3", "RET", "ROS1")
                )
            ),
            limitations = listOf(
                "This test was performed using DNA sequencing analysis, and it is possible to detect single nucleotide variant (SNV), small indel, copy number variation (CNV), gene rearrangement in the region included in the test. However it’s not possible to detect any variants in the region not covered by the test.",
                "The detection limit of SNV and small-indel depends on the amount of DNA in the extracted sample and limit of detection (LOD) is about 0.1~2.0%  depending on the concentration of cfDNA. And when the tumor proportion is low, variants may not be detected, so clinical correlaton is recommended.",
                "The four genes (APC, FBXW7, PTEN, TP53) corresponding to tumor suppressor genes among the selected genes included in this test do not cover the entire gene region, but include  most of the major pathogenic variants.",
                "This test does not distinguish germline and somatic variations. If the variant allele frequency of the mutation is close to 50% or 100% in the gene associated with hereditary cancer syndrome, there is a possibility of germline mutation.",
                "The variants detected in this test are classified into four stages (tier 1 to 4)  according to the 2017 JMD guideline (J Mol Diagn 2017; 19:313-327), and tier 4 variations are not reported."
            ), i18n = Reportable.I18N.EnUs
        )
        val ON124 = NonTSO(
            code = "ON124",
            name = "GCG-Oncomine Comprehensive Assay Plus\n(TMB/MSI, incl. RNA)",
            serialGroup = "TMB_R",
            referralDefault = "",
            method = NonTsoPanelAnalysisMethod(
                region = "427 genes",
                panel = "Solid Tumor Panel II(TMB/MSI)(RNA included)",
                pipeline = "Oncomine Comprehensive Plus - w.2.5 - DNA and Fusions - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive Plus)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "CDS (n=227)",
                    genes = listOf("ABRAXAS1", "ACVR1B", "ACVR2A", "ADAMTS12", "ADAMTS2",
                        "AMER1", "APC", "ARHGAP35", "ARID1A", "ARID1B",
                        "ARID2", "ARID5B", "ASXL1", "ASXL2", "ATM",
                        "ATR", "ATRX", "AXIN1", "AXIN2", "B2M",
                        "BAP1", "BARD1", "BCOR", "BLM", "BMPR2",
                        "BRCA1", "BRCA2", "BRIP1", "CALR", "CASP8",
                        "CBFB", "CD274", "CD276", "CDC73", "CDH1",
                        "CDH10", "CDK12", "CDKN1A", "CDKN1B", "CDKN2A",
                        "CDKN2B", "CDKN2C", "CHEK1", "CHEK2", "CIC",
                        "CIITA", "CREBBP", "CSMD3", "CTCF", "CTLA4",
                        "CUL3", "CUL4A", "CUL4B", "CYLD", "CYP2C9",
                        "CYP2D6", "DAXX", "DDX3X", "DICER1", "DNMT3A",
                        "DOCK3", "DPYD", "DSC1", "DSC3", "ELF3",
                        "ENO1", "EP300", "EPCAM", "EPHA2", "ERAP1",
                        "ERAP2", "ERCC2", "ERCC4", "ERCC5", "ERRFI1",
                        "ETV6", "FANCA", "FANCC", "FANCD2", "FANCE",
                        "FANCF", "FANCG", "FANCI", "FANCL", "FANCM",
                        "FAS", "FAT1", "FBXW7", "FUBP1", "GATA3",
                        "GNA13", "GPS2", "HDAC2", "HDAC9", "HLA-A",
                        "HLA-B", "HNF1A", "ID3", "INPP4B", "JAK1",
                        "JAK2", "JAK3", "KDM5C", "KDM6A", "KEAP1",
                        "KLHL13", "KMT2A", "KMT2B", "KMT2C", "KMT2D",
                        "LARP4B", "LATS1", "LATS2", "MAP2K4", "MAP2K7",
                        "MAP3K1", "MAP3K4",
                        "MAPK8", "MEN1", "MGA", "MLH1", "MLH3",
                        "MRE11", "MSH2", "MSH3", "MSH6", "MTAP",
                        "MTUS2", "MUTYH", "NBN", "NCOR1", "NF1",
                        "NF2", "NOTCH1", "NOTCH2", "NOTCH3", "NOTCH4",
                        "PALB2", "PARP1", "PARP2", "PARP3", "PARP4",
                        "PBRM1", "PDCD1", "PDCD1LG2", "PDIA3", "PGD",
                        "PHF6", "PIK3R1", "PMS1", "PMS2", "POLD1", "POLE",
                        "POT1", "PPM1D", "PPP2R2A", "PRDM1", "PRDM9",
                        "PRKAR1A", "PSMB10", "PSMB8", "PSMB9", "PTCH1",
                        "PTEN", "PTPRT", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RAD52", "RAD54L", "RASA1",
                        "RASA2", "RB1", "RBM10", "RECQL4", "RNASEH2A",
                        "RNASEH2B", "RNASEH2C", "RNF43", "RPA1", "RPL22",
                        "RPL5", "RUNX1", "RUNX1T1", "SDHA", "SDHB",
                        "SDHC", "SDHD", "SETD2", "SLX4", "SMAD2",
                        "SMAD4", "SMARCA4", "SMARCB1", "SOCS1", "SOX9",
                        "SPEN", "STAG2", "STAT1", "STK11", "SUFU",
                        "TAP1", "TAP2", "TBX3", "TCF7L2", "TET2",
                        "TGFBR2", "TMEM132D", "TNFAIP3", "TNFRSF14", "TP53",
                        "TP63", "TPP2", "TSC1", "TSC2", "UGT1A1",
                        "USP9X", "VHL", "WT1", "XRCC2", "XRCC3",
                        "ZBTB20", "ZFHX3", "ZMYM3", "ZRSR2")
                ),
                GeneSet(
                    name = "Hotspot mutations (n=165)",
                    genes = listOf(
                        "ABL1", "ABL2", "ACVR1", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "ATP1A1", "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BCR",
                        "BMP5", "BRAF", "BTK", "CACNA1D", "CARD11", "CBL", "CCND1", "CCND2",
                        "CCND3", "CCNE1", "CD79B", "CDK4", "CDK6", "CHD4", "CSF1R", "CTNNB1",
                        "CUL1", "CYSLTR2", "DDR2", "DGCR8", "DROSHA", "E2F1", "EGFR",
                        "EIF1AX", "EPAS1", "ERBB2", "ERBB3", "ERBB4", "ESR1", "EZH2",
                        "FAM135B", "FGF7", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "FLT4", "FOXA1", "FOXL2", "FOXO1", "GATA2", "GLI1", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "H3F3B", "HIF1A", "HIST1H2BD", "HIST1H3B",
                        "HRAS", "IDH1", "IDH2", "IKBKB", "IL6ST", "IL7R", "IRF4", "IRS4",
                        "KDR", "KIT", "KLF4", "KLF5", "KNSTRN", "KRAS", "MAGOH", "MAP2K1",
                        "MAP2K2", "MAPK1", "MAX", "MDM4", "MECOM", "MED12", "MEF2B",
                        "MET", "MITF", "MPL", "MTOR", "MYC", "MYCN", "MYD88", "MYOD1",
                        "NFE2L2", "NRAS", "NSD2", "NT5C2", "NTRK1", "NTRK2", "NTRK3",
                        "NUP93", "PAX5", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B", "PIK3CA",
                        "PIK3CB", "PIK3CD", "PIK3CG", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A",
                        "PPP6C", "PRKACA", "PTPN11", "PTPRD", "PXDNL", "RAC1", "RAF1",
                        "RARA", "RET", "RGS7", "RHEB", "RHOA", "RICTOR", "RIT1", "ROS1",
                        "RPL10", "SETBP1", "SF3B1", "SIX1", "SIX2", "SLCO1B3", "SMC1A",
                        "SMO", "SNCAIP", "SOS1", "SOX2", "SPOP", "SRC", "SRSF2", "STAT3",
                        "STAT5B", "STAT6", "TAF1", "TERT", "TGFBR1", "TOP1", "TPMT",
                        "TRRAP", "TSHR", "U2AF1", "USP8", "WAS", "XPO1", "ZNF217", "ZNF429"
                    )
                ),
                GeneSet(
                    name = "CNV Gain (n=127)",
                    genes = listOf(
                        "ABCB1", "ABL1", "ABL2", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BRAF", "CARD11",
                        "CBL", "CCND1", "CCND2", "CCND3", "CCNE1", "CDK4", "CDK6", "CHD4",
                        "CTNND2", "DDR1", "DDR2", "EGFR", "EIF1AX", "EMSY", "ERBB2", "ERBB3",
                        "ERBB4", "ESR1", "EZH2", "FAM135B", "FGF19", "FGF23", "FGF3", "FGF4",
                        "FGF9", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3", "FLT4", "FOXA1",
                        "FYN", "GATA2", "GLI3", "GNAS", "H3F3A", "H3F3B", "IDH2", "IGF1R",
                        "IKBKB", "IL7R", "KDR", "KIT", "KLF5", "KRAS", "MAGOH", "MAP2K1",
                        "MAPK1", "MAX", "MCL1", "MDM2", "MDM4", "MECOM", "MEF2B", "MET",
                        "MITF", "MPL", "MTOR", "MYC", "MYCL", "MYCN", "MYD88", "NFE2L2",
                        "NRAS", "NTRK1", "NTRK3", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B",
                        "PIK3CA", "PIK3CB", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A", "PPP6C",
                        "PRKACA", "PTPN11", "PXDNL", "RAC1", "RAF1", "RARA", "RET", "RHEB",
                        "RICTOR", "RIT1", "ROS1", "RPS6KB1", "RPTOR", "SETBP1", "SF3B1",
                        "SLCO1B3", "SMC1A", "SMO", "SPOP", "SRC", "STAT3", "STAT6", "TERT",
                        "TOP1", "TPMT", "U2AF1", "USP8", "XPO1", "YAP1", "YES1", "ZNF217",
                        "ZNF429")
                ),
                GeneSet(
                    name = "RNA fusion (n=49)",
                    genes = listOf(
                        "AKT1", "AKT2", "AKT3", "ALK", "AR", "BRAF", "BRCA1", "CDKN2A",
                        "EGFR", "ERBB2", "ERBB4", "ERG", "ESR1", "ETV1", "ETV4", "ETV5",
                        "FGFR1", "FGFR2", "FGFR3", "MAP3K8", "MET", "MTAP", "MYB", "MYBL1",
                        "NOTCH1", "NOTCH2", "NOTCH3", "NRG1", "NTRK1", "NTRK2", "NTRK3",
                        "NUTM1", "PIK3CA", "PIK3CB", "PPARG", "PRKACA", "PRKACB", "RAF1",
                        "RARA", "RELA", "RET", "ROS1", "RSPO2", "RSPO3", "STAT6", "TERT",
                        "TFE3", "TFEB", "YAP1")
                )
            ),
            limitations = listOf(
                "This test was performed using sequencing analysis, and can detect SNP and small-indel variants within the analyzed region. The test cannot detect any variants in the region not covered by the test.",
                "The limit of detection for SNV and small-indel variants is approximately 5%.",
                "The detection rate of certain regions such as homopolymer and repeat regions may be lower due to limitation of DNA sequencing method,and certain target region may have lower coverage.",
                "The detected variants in this test are not confirmed by Sanger sequencing, ddPCR or other confirmation methods.",
                "This test does not distinguish between germline and somatic variants. If the variant allele frequency of the mutation is close to 50% or 100%, the possibility of germline variant cannot be excluded.",
                "The variants detected in this test are classified into four stages (tier 1~4) according to the 2017 JMD guideline, and tier 4 variants are not reported.",
                "If the MAPD (Median of the Absolute values of all Pairwise Differences) score, a QC index for CNV calling, exceed 0.5, CNV analysis will not be performed. If the MAPD score exceed 0.3, the reliability is considered low.",
                "For CNV analysis, only copy number gain is reported. The detection limit of copy number gain is about 4 copy gain.",
                "MSI status is reported as MSI-High or MSS. If the 76 MSI biomarkers are not covered enough, MSI status may be reported as no call or QC fail. Validation of MSI status from the manufacturer,using 192 samples, showed sensitivity of 96% and specificity of 99%.",
                "TMB score is the number of mutations detected per 106 base pair. The classification for TMB-low and TMB-high is not yet defined."
            ), i18n = Reportable.I18N.EnUs
        )
        val ON125 = NonTSO(
            code = "ON125",
            name = "GCG-Oncomine Comprehensive Assay Plus\n(TMB/MSI, excl. RNA)",
            serialGroup = "TMB_D",
            method = NonTsoPanelAnalysisMethod(
                region = "411 genes",
                panel = "Solid Tumor Panel II(TMB/MSI)(RNA not included)",
                pipeline = "Oncomine Comprehensive Plus - w.2.5 - DNA - Single Sample",
                method = "Amplicon-based assay(Oncomine Comprehensive Plus)"
            ),
            genesets = listOf(
                GeneSet(
                    name = "CDS (n=227)",
                    genes = listOf("ABRAXAS1", "ACVR1B", "ACVR2A", "ADAMTS12", "ADAMTS2",
                        "AMER1", "APC", "ARHGAP35", "ARID1A", "ARID1B",
                        "ARID2", "ARID5B", "ASXL1", "ASXL2", "ATM",
                        "ATR", "ATRX", "AXIN1", "AXIN2", "B2M",
                        "BAP1", "BARD1", "BCOR", "BLM", "BMPR2",
                        "BRCA1", "BRCA2", "BRIP1", "CALR", "CASP8",
                        "CBFB", "CD274", "CD276", "CDC73", "CDH1",
                        "CDH10", "CDK12", "CDKN1A", "CDKN1B", "CDKN2A",
                        "CDKN2B", "CDKN2C", "CHEK1", "CHEK2", "CIC",
                        "CIITA", "CREBBP", "CSMD3", "CTCF", "CTLA4",
                        "CUL3", "CUL4A", "CUL4B", "CYLD", "CYP2C9",
                        "CYP2D6", "DAXX", "DDX3X", "DICER1", "DNMT3A",
                        "DOCK3", "DPYD", "DSC1", "DSC3", "ELF3",
                        "ENO1", "EP300", "EPCAM", "EPHA2", "ERAP1",
                        "ERAP2", "ERCC2", "ERCC4", "ERCC5", "ERRFI1",
                        "ETV6", "FANCA", "FANCC", "FANCD2", "FANCE",
                        "FANCF", "FANCG", "FANCI", "FANCL", "FANCM",
                        "FAS", "FAT1", "FBXW7", "FUBP1", "GATA3",
                        "GNA13", "GPS2", "HDAC2", "HDAC9", "HLA-A",
                        "HLA-B", "HNF1A", "ID3", "INPP4B", "JAK1",
                        "JAK2", "JAK3", "KDM5C", "KDM6A", "KEAP1",
                        "KLHL13", "KMT2A", "KMT2B", "KMT2C", "KMT2D",
                        "LARP4B", "LATS1", "LATS2", "MAP2K4", "MAP2K7",
                        "MAP3K1", "MAP3K4",
                        "MAPK8", "MEN1", "MGA", "MLH1", "MLH3",
                        "MRE11", "MSH2", "MSH3", "MSH6", "MTAP",
                        "MTUS2", "MUTYH", "NBN", "NCOR1", "NF1",
                        "NF2", "NOTCH1", "NOTCH2", "NOTCH3", "NOTCH4",
                        "PALB2", "PARP1", "PARP2", "PARP3", "PARP4",
                        "PBRM1", "PDCD1", "PDCD1LG2", "PDIA3", "PGD",
                        "PHF6", "PIK3R1", "PMS1", "PMS2", "POLD1", "POLE",
                        "POT1", "PPM1D", "PPP2R2A", "PRDM1", "PRDM9",
                        "PRKAR1A", "PSMB10", "PSMB8", "PSMB9", "PTCH1",
                        "PTEN", "PTPRT", "RAD50", "RAD51", "RAD51B",
                        "RAD51C", "RAD51D", "RAD52", "RAD54L", "RASA1",
                        "RASA2", "RB1", "RBM10", "RECQL4", "RNASEH2A",
                        "RNASEH2B", "RNASEH2C", "RNF43", "RPA1", "RPL22",
                        "RPL5", "RUNX1", "RUNX1T1", "SDHA", "SDHB",
                        "SDHC", "SDHD", "SETD2", "SLX4", "SMAD2",
                        "SMAD4", "SMARCA4", "SMARCB1", "SOCS1", "SOX9",
                        "SPEN", "STAG2", "STAT1", "STK11", "SUFU",
                        "TAP1", "TAP2", "TBX3", "TCF7L2", "TET2",
                        "TGFBR2", "TMEM132D", "TNFAIP3", "TNFRSF14", "TP53",
                        "TP63", "TPP2", "TSC1", "TSC2", "UGT1A1",
                        "USP9X", "VHL", "WT1", "XRCC2", "XRCC3",
                        "ZBTB20", "ZFHX3", "ZMYM3", "ZRSR2")
                ),
                GeneSet(
                    name = "Hotspot mutations (n=165)",
                    genes = listOf(
                        "ABL1", "ABL2", "ACVR1", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "ATP1A1", "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BCR",
                        "BMP5", "BRAF", "BTK", "CACNA1D", "CARD11", "CBL", "CCND1", "CCND2",
                        "CCND3", "CCNE1", "CD79B", "CDK4", "CDK6", "CHD4", "CSF1R", "CTNNB1",
                        "CUL1", "CYSLTR2", "DDR2", "DGCR8", "DROSHA", "E2F1", "EGFR",
                        "EIF1AX", "EPAS1", "ERBB2", "ERBB3", "ERBB4", "ESR1", "EZH2",
                        "FAM135B", "FGF7", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3",
                        "FLT4", "FOXA1", "FOXL2", "FOXO1", "GATA2", "GLI1", "GNA11",
                        "GNAQ", "GNAS", "H3F3A", "H3F3B", "HIF1A", "HIST1H2BD", "HIST1H3B",
                        "HRAS", "IDH1", "IDH2", "IKBKB", "IL6ST", "IL7R", "IRF4", "IRS4",
                        "KDR", "KIT", "KLF4", "KLF5", "KNSTRN", "KRAS", "MAGOH", "MAP2K1",
                        "MAP2K2", "MAPK1", "MAX", "MDM4", "MECOM", "MED12", "MEF2B",
                        "MET", "MITF", "MPL", "MTOR", "MYC", "MYCN", "MYD88", "MYOD1",
                        "NFE2L2", "NRAS", "NSD2", "NT5C2", "NTRK1", "NTRK2", "NTRK3",
                        "NUP93", "PAX5", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B", "PIK3CA",
                        "PIK3CB", "PIK3CD", "PIK3CG", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A",
                        "PPP6C", "PRKACA", "PTPN11", "PTPRD", "PXDNL", "RAC1", "RAF1",
                        "RARA", "RET", "RGS7", "RHEB", "RHOA", "RICTOR", "RIT1", "ROS1",
                        "RPL10", "SETBP1", "SF3B1", "SIX1", "SIX2", "SLCO1B3", "SMC1A",
                        "SMO", "SNCAIP", "SOS1", "SOX2", "SPOP", "SRC", "SRSF2", "STAT3",
                        "STAT5B", "STAT6", "TAF1", "TERT", "TGFBR1", "TOP1", "TPMT",
                        "TRRAP", "TSHR", "U2AF1", "USP8", "WAS", "XPO1", "ZNF217", "ZNF429"
                    )
                ),
                GeneSet(
                    name = "CNV Gain (n=127)",
                    genes = listOf(
                        "ABCB1", "ABL1", "ABL2", "AKT1", "AKT2", "AKT3", "ALK", "AR", "ARAF",
                        "AURKA", "AURKC", "AXL", "BCL2", "BCL2L12", "BCL6", "BRAF", "CARD11",
                        "CBL", "CCND1", "CCND2", "CCND3", "CCNE1", "CDK4", "CDK6", "CHD4",
                        "CTNND2", "DDR1", "DDR2", "EGFR", "EIF1AX", "EMSY", "ERBB2", "ERBB3",
                        "ERBB4", "ESR1", "EZH2", "FAM135B", "FGF19", "FGF23", "FGF3", "FGF4",
                        "FGF9", "FGFR1", "FGFR2", "FGFR3", "FGFR4", "FLT3", "FLT4", "FOXA1",
                        "FYN", "GATA2", "GLI3", "GNAS", "H3F3A", "H3F3B", "IDH2", "IGF1R",
                        "IKBKB", "IL7R", "KDR", "KIT", "KLF5", "KRAS", "MAGOH", "MAP2K1",
                        "MAPK1", "MAX", "MCL1", "MDM2", "MDM4", "MECOM", "MEF2B", "MET",
                        "MITF", "MPL", "MTOR", "MYC", "MYCL", "MYCN", "MYD88", "NFE2L2",
                        "NRAS", "NTRK1", "NTRK3", "PCBP1", "PDGFRA", "PDGFRB", "PIK3C2B",
                        "PIK3CA", "PIK3CB", "PIK3R2", "PIM1", "PLCG1", "PPP2R1A", "PPP6C",
                        "PRKACA", "PTPN11", "PXDNL", "RAC1", "RAF1", "RARA", "RET", "RHEB",
                        "RICTOR", "RIT1", "ROS1", "RPS6KB1", "RPTOR", "SETBP1", "SF3B1",
                        "SLCO1B3", "SMC1A", "SMO", "SPOP", "SRC", "STAT3", "STAT6", "TERT",
                        "TOP1", "TPMT", "U2AF1", "USP8", "XPO1", "YAP1", "YES1", "ZNF217",
                        "ZNF429")
                )
            ),
            limitations = listOf(
                "This test was performed using sequencing analysis, and can detect SNP and small-indel variants within the analyzed region. The test cannot detect any variants in the region not covered by the test.",
                "The limit of detection for SNV and small-indel variants is approximately 5%.",
                "The detection rate of certain regions such as homopolymer and repeat regions may be lower due to limitation of DNA sequencing method,and certain target region may have lower coverage.",
                "The detected variants in this test are not confirmed by Sanger sequencing, ddPCR or other confirmation methods.",
                "This test does not distinguish between germline and somatic variants. If the variant allele frequency of the mutation is close to 50% or 100%, the possibility of germline variant cannot be excluded.",
                "The variants detected in this test are classified into four stages (tier 1~4) according to the 2017 JMD guideline, and tier 4 variants are not reported.",
                "If the MAPD (Median of the Absolute values of all Pairwise Differences) score, a QC index for CNV calling, exceed 0.5, CNV analysis will not be performed. If the MAPD score exceed 0.3, the reliability is considered low.",
                "For CNV analysis, only copy number gain is reported. The detection limit of copy number gain is about 4 copy gain.",
                "MSI status is reported as MSI-High or MSS. If the 76 MSI biomarkers are not covered enough, MSI status may be reported as no call or QC fail. Validation of MSI status from the manufacturer,using 192 samples, showed sensitivity of 96% and specificity of 99%.",
                "TMB score is the number of mutations detected per 106 base pair. The classification for TMB-low and TMB-high is not yet defined."
            ), i18n = Reportable.I18N.EnUs
        )
        fun values() = listOf(
            N103, N117, N124, G2200102, N125,
            O004, ON124, ON125
        )
    }
    data class GeneSet(val name: String, val genes: List<String>)

}