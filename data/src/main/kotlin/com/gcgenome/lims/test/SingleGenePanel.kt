package com.gcgenome.lims.test



interface SingleGenePanel: HasCode, HasCategory, HasName, HasSerialGroup, ContainsGenes, ContainsAddendumGenes, IsSingleGenePanelAnalysis, MayBeNationalInsurance, Interpretable, Reportable {
    companion object {
        val N027 = SingleGenePanelImpl(
            code = "N027", gene = "SLC26A4", serialGroup = "WES",
            addendum = "ABHD12,ABCC1,ACOX1,ACTG1,ADGRV1,AIFM1,ALMS1,AP1S1,ATP11A,ATP2B2,ATP6V1B1,ATP6V1B2,BCS1L,BSND,CABP2,CCDC50,CDC14A,CDH23,CEACAM16,CEP250,CEP78,CHD7,CIB2,CISD2,CLDN14,CLDN9,CLIC5,CLPP,CLRN1,CLRN2,COCH,COG4,COL11A1,COL11A2,COL2A1,COL4A5,COL4A6,COL9A1,COL9A2,COL9A3,CRLS1,CRYM,DIABLO,DIAPH1,DIAPH3,DMXL2,DNAJC3,DNMT1,DSPP,EDN3,EDNRB,ELMOD3,EPS8,EPS8L2,ESPN,ESRP1,ESRRB,EYA1,EYA4,FDXR,FGF3,FOXF2,FOXI1,GATA3,GGPS1,GIPC3,GJB2,GJB3,GJB6,GPR156,GPSM2,GREB1L,GRHL2,GRXCR1,GSDME,HAAO,HARS2,HGF,HOMER2,HOXA2,HSD17B4,ILDR1,KARS1,KCNE1,KCNJ10,KCNJ16,KCNQ1,KCNQ4,KDM3B,KIT,KITLG,LARS2,LETM1,LHFPL5,LMX1A,LOXHD1,LRTOMT,MARVELD2,MASP1,MET,MITF,MN1,MORC2,MPZL2,MSRB3,MYH14,MYH9,MYO15A,MYO3A,MYO6,MYO7A,NARS2,OGDHL,OPA1,OSBPL2,OTOA,OTOF,OTOG,OTOGL,OXR1,P2RX2,PAX2,PAX3,PBX1,PCDH15,PDSS1,PDZD7,PJVK,PLS1,PMP22,PNPT1,POU3F4,POU4F3,PPIP5K2,PRPS1,PSMC3,PTPRQ,RDX,RIPOR2,RNF220,ROR1,S1PR2,SALL1,SALL4,SERAC1,SERPINB6,SGPL1,SIX1,SLC12A2,SLC17A8,SLC26A5,SLC4A11,SLC52A2,SLC52A3,SLITRK6,SMPX,SNAI2,SOX10,SOX2,SPATA5,SPATA5L1,SPATC1L,SPNS2,SPTBN4,STRC,STX4,STXBP3,SYNE4,TBC1D24,TECTA,THOC1,TIMM8A,TMC1,TMIE,TMPRSS3,TMTC2,TNC,TOP2B,TPRN,TRIOBP,USH1C,USH1G,USH2A,USP48,WBP2,WFS1,WHRN,YARS1".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "SLC26A4 on Chromosome 7q22.3")
        )
        val N057 = SingleGenePanelImpl(
            code = "N057", gene = "GALNS", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "GALNS on Chromosome 16q24.3"),
            name = "GALNS gene mutation_WES / 전용", title = "GALNS gene mutation_WES / 전용 결과보고서"
        )
        val N058 = SingleGenePanelImpl(
            code = "N058", gene = "IDS", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "IDS on Chromosome Xq28")
        )
        val N059 = SingleGenePanelImpl(
            code = "N059", gene = "IDUA", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "IDUA on Chromosome 4p16.3")
        )
        val N257 = SingleGenePanelHasReferralDefault(
            code = "N257",
            gene = "NF1", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "NF1 on Chromosome 17q11.2", sequencing = "Sequencing of all coding exons, MLPA (Multiplex Ligation-dependent Probe Amplification)"),
            name = "NF1 [Sequencing&MLPA]", title = "NF1 [Sequencing&MLPA] Analysis 결과보고서",
            referralDefault = "Neurofibromatosis"
        )
        val S030 = SingleGenePanelHasReferralDefault(
            code = "S030", gene = "F8", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F11,F12,F13A1,F13B,F2,F2R,F5,F7,F9,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,VWF,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "F8 on Chromosome Xq28"), referralDefault = "Hemophilia A"
        )
        val S032 = SingleGenePanelHasReferralDefault(
            code = "S032", gene = "F9", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F11,F12,F13A1,F13B,F2,F2R,F5,F7,F8,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,VWF,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "F9 on Chromosome Xq27.1"), referralDefault = "Hemophilia B"
        )
        val S034 = SingleGenePanelHasReferralDefault(
            code = "S034",
            gene = "VWF", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F11,F12,F13A1,F13B,F2,F2R,F5,F7,F8,F9,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "VWF on Chromosome 12p13.31"),
            referralDefault = "VWD (F8: ??%, VWF (Ag): ??%, VWF (Activity): ??% )"
        )
        val S053 = SingleGenePanelHasReferralDefault(
            code = "S053", gene = "F7", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F11,F12,F13A1,F13B,F2,F2R,F5,F8,F9,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,VWF,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "F7 on Chromosome 13q34"), referralDefault = "F7 deficiency"
        )
        val S054 = SingleGenePanelHasReferralDefault(
            code = "S054", gene = "F11", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F12,F13A1,F13B,F2,F2R,F5,F7,F8,F9,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,VWF,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "F11 on Chromosome 4q35.2"), referralDefault = "F11 deficiency"
        )
        val S055 = SingleGenePanelHasReferralDefault(
            code = "S055", gene = "F12", serialGroup = "WES",
            addendum = "ABCG5,ABCG8,ACTB,ACTN1,ACVRL1,ADAMTS13,ANKRD26,ANO6,AP3B1,AP3D1,ARPC1B,BLOC1S3,BLOC1S5,BLOC1S6,BMPR2,CDC42,CHST14,COL1A1,COL3A1,COL5A1,COL5A2,CYCS,DIAPH1,DTNBP1,ENG,EPHB2,ETV6,F10,F11,F13A1,F13B,F2,F2R,F5,F7,F8,F9,FERMT3,FGA,FGB,FGG,FLI1,FLNA,FYB1,GALE,GATA1,GBA,GFI1B,GGCX,GNE,GP1BA,GP1BB,GP6,GP9,HOXA11,HPS1,HPS3,HPS4,HPS5,HPS6,HRG,IKZF5,ITGA2B,ITGB3,KDSR,KLKB1,KNG1,LAT,LMAN1,LYST,MCFD2,MECOM,MPIG6B,MPL,MRTFA,MYH9,NBEA,NBEAL2,ORAI1,P2RX1,P2RY12,PLA2G4A,PLAT,PLAU,PLG,PROC,PROS1,PTGS1,PTPN11,PTPRJ,RASGRP2,RBM8A,RGS2,RUNX1,SERPINC1,SERPIND1,SERPINE1,SERPINF2,SLC35A1,SLC45A2,SLFN14,SMAD4,SRC,STIM1,STXBP2,TBXA2R,TBXAS1,THBD,THPO,TNXB,TPM4,TRPM7,TUBB1,UNC13D,VIPAS39,VKORC1,VPS33B,VWF,WAS".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "F12 on Chromosome 5q35.3"), referralDefault = "F12 deficiency"
        )
        val S061 = SingleGenePanelImpl(
            code = "S061", gene = "MEN1", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "MEN1 on Chromosome 11q13.1"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val S062 = SingleGenePanelImpl(
            code = "S062", gene = "NF2", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "NF2 on Chromosome 22q12.2"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val S063 = SingleGenePanelImpl(
            code = "S063", gene = "PTEN", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "PTEN on Chromosome 10q23.31"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val S064 = SingleGenePanelImpl(
            code = "S064", gene = "PTPN11", serialGroup = "WES",
            addendum = "BRAF,CBL,HRAS,KRAS,LZTR1,MAP2K1,MAP2K2,MRAS,NF1,NRAS,PPP1CB,RAF1,RIT1,RRAS2,SHOC2,SOS1,SOS2,SPRED1,RASA2,RRAS,SPRED2".split(",").sorted().toList(),
            method= SingleGeneAnalysisMethod(target="PTPN11 on Chromosome 12q24.13")
        )
        val S065 = SingleGenePanelHasReferralDefault(
            code = "S065", gene = "ATP7B", serialGroup = "WES",
            addendum = "ATP13A2,ATP1A3,C19orf12,CSF1R,DCTN1,DNAJC6,FBXO7,FTL,GBA,GCH1,GRN,LRRK2,LYST,MAPT,OPA3,PANK2,PARK7,PINK1,PLA2G6,PRKN,PRKRA,PTRHD1,RAB39B,SLC30A10,SLC39A14,SLC6A3,SNCA,SPG11,SPR,SYNJ1,TH,TUBB4A,VPS13A,VPS35,WDR45,ANG,CHCHD2,CLN3,COASY,CP,DNAJC12,DNAJC5,EPHB4,PDE8B,PDGFB,PDGFRB,SLC20A2,TAF1,TWNK,VPS13C,XPR1".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "ATP7B on Chromosome 13q14.3"), referralDefault = "Wilson disease"
        )
        val S067 = SingleGenePanelImpl(
            code = "S067", gene = "HPRT1", serialGroup = "WES",
            addendum = "AARS2,AASS,ABAT,ABCA1,ABCB11,ABCB4,ABCB7,ABCD1,ABCD4,ABCG5,ABCG8,ABHD12,ABHD5,ACAD8,ACAD9,ACADM,ACADS,ACADSB,ACADVL,ACAT1,ACO2,ACOX1,ACSF3,ACY1,ADA,ADAR,ADSL,AFG3L2,AGA,AGK,AGL,AGPS,AGXT,AHCY,AIFM1,AKR1D1,ALAD,ALAS2,ALDH18A1,ALDH3A2,ALDH4A1,ALDH5A1,ALDH6A1,ALDH7A1,ALDOA,ALDOB,ALG1,ALG11,ALG12,ALG14,ALG3,ALG6,ALG8,ALG9,ALPL,AMACR,AMN,AMT,ANO10,APOA1,APOA5,APOB,APOC2,APOE,APRT,APTX,ARG1,ARSA,ARSB,ARSL,ASAH1,ASL,ASPA,ASS1,ATAD3A,ATIC,ATP13A2,ATP6AP1,ATP6V0A2,ATP7A,ATP7B,ATP8B1,ATPAF2,AUH,B3GALNT2,B3GALT6,B3GAT3,B3GLCT,B4GALT1,B4GALT7,BAAT,BCKDHA,BCKDHB,BCKDK,BCS1L,BOLA3,BTD,C19orf12,CA5A,CAT,CBLIF,CBS,CCDC115,CHCHD10,CHKB,CHST14,CHST3,CHST6,CHSY1,CISD2,CLDN16,CLDN19,CLN3,CLN5,CLN6,CLN8,CLPB,CLPP,CNNM2,COA8,COG1,COG4,COG5,COG6,COG7,COG8,COQ2,COQ4,COQ6,COQ7,COQ8A,COQ8B,COQ9,COX10,COX14,COX15,COX20,COX6A1,COX6B1,COX7B,CP,CPOX,CPS1,CPT1A,CPT2,CRPPA,CTH,CTNS,CTSA,CTSC,CTSD,CTSK,CUBN,CYC1,CYP27A1,CYP7B1,D2HGDH,DARS1,DARS2,DBH,DBT,DCXR,DDC,DGUOK,DHCR24,DHCR7,DHFR,DHODH,DHTKD1,DLAT,DLD,DNA2,DNAJC12,DNAJC19,DNAJC5,DNM1L,DNM2,DOLK,DPAGT1,DPM1,DPM2,DPM3,DPYD,DPYS,DYM,EARS2,EBP,ECHS1,ELAC2,ENO3,EPG5,EPM2A,ETFA,ETFB,ETFDH,ETHE1,EXT1,EXT2,FA2H,FAH,FAR1,FARS2,FASTKD2,FBP1,FBXL4,FDX2,FECH,FGFR2,FH,FKRP,FKTN,FMO3,FOLR1,FOXRED1,FTCD,FUCA1,FUT8,FXN,G6PC1,G6PC3,GAA,GABRG2,GALC,GALE,GALK1,GALNS,GALNT3,GALT,GAMT,GARS1,GATM,GBA,GBE1,GCDH,GCH1,GCLC,GDAP1,GFER,GFM1,GFPT1,GK,GLA,GLB1,GLDC,GLRA1,GLRX5,GLUD1,GLUL,GLYCTK,GM2A,GMPPB,GNE,GNMT,GNPAT,GNPTAB,GNPTG,GNS,GPD1,GPHN,GRHPR,GSS,GTPBP3,GUSB,GYG1,GYS1,GYS2,HAAO,HADH,HADHA,HADHB,HAMP,HARS2,HCCS,HCFC1,HEXA,HEXB,HFE,HGD,HGSNAT,HIBCH,HJV,HLCS,HMBS,HMGCL,HMGCS2,HOGA1,HPD,HPS1,HS2ST1,HSD17B10,HSD17B4,HSD3B7,HSPD1,HTRA2,HYAL1,IARS2,IBA57,IDH2,IDS,IDUA,IER3IP1,ISCA2,ISCU,ITPA,IVD,KARS1,KYNU,L2HGDH,LAMP2,LARGE1,LARS1,LARS2,LBR,LCAT,LCT,LDHA,LDLR,LDLRAP1,LIAS,LIPA,LIPT1,LMBRD1,LONP1,LPIN1,LPL,LRPPRC,MAGT1,MAN1B1,MAN2B1,MANBA,MAOA,MARS2,MAT1A,MCCC1,MCCC2,MCEE,MCOLN1,MFF,MFN2,MFSD8,MGAT2,MGME1,MLYCD,MMAA,MMAB,MMACHC,MMADHC,MMUT,MOCS1,MOCS2,MOGS,MPDU1,MPI,MPV17,MRPL3,MRPS22,MSMO1,MTFMT,MTHFR,MTO1,MTPAP,MTR,MTRFR,MTRR,MTTP,MVK,NAGA,NAGLU,NAGS,NARS2,NDUFA1,NDUFA10,NDUFA11,NDUFA2,NDUFAF1,NDUFAF2,NDUFAF3,NDUFAF4,NDUFAF5,NDUFAF6,NDUFB11,NDUFB3,NDUFS1,NDUFS2,NDUFS3,NDUFS4,NDUFS6,NDUFS7,NDUFS8,NDUFV1,NDUFV2,NEU1,NFU1,NGLY1,NHLRC1,NNT,NPC1,NPC2,NSDHL,NT5C3A,NUBPL,OAT,OCRL,OPA1,OPA3,OTC,OXCT1,PAH,PANK2,PC,PCBD1,PCCA,PCCB,PCK1,PCSK9,PDHA1,PDHB,PDHX,PDP1,PDSS1,PDSS2,PEPD,PET100,PEX1,PEX10,PEX11B,PEX12,PEX13,PEX14,PEX16,PEX19,PEX2,PEX26,PEX3,PEX5,PEX6,PEX7,PFKM,PGAM2,PGAP2,PGAP3,PGK1,PGM1,PGM3,PHGDH,PHKA1,PHKA2,PHKB,PHKG2,PHYH,PIGA,PIGL,PIGN,PIGO,PIGT,PIGV,PINK1,PLA2G6,PMM2,PMPCA,PNP,PNPO,PNPT1,POLG,POLG2,POMGNT1,POMGNT2,POMT1,POMT2,POR,PPA2,PPOX,PPT1,PRKAG2,PRODH,PRPS1,PSAP,PSAT1,PTS,PUS1,PYCR1,PYGL,PYGM,QDPR,RARS2,RBCK1,RBP4,RFT1,RMND1,RNASEH1,RPIA,RPL10,RRM2B,RXYLT1,SACS,SAMHD1,SAR1B,SARS2,SC5D,SCO1,SCO2,SCP2,SDHA,SDHAF1,SDHB,SDHD,SEC23B,SERAC1,SETX,SGSH,SI,SKIV2L,SLC12A3,SLC16A1,SLC17A5,SLC18A2,SLC19A2,SLC19A3,SLC22A5,SLC25A1,SLC25A12,SLC25A13,SLC25A15,SLC25A19,SLC25A20,SLC25A22,SLC25A26,SLC25A3,SLC25A38,SLC25A4,SLC25A46,SLC2A1,SLC2A2,SLC30A10,SLC35A1,SLC35A2,SLC35C1,SLC35D1,SLC37A4,SLC39A14,SLC39A4,SLC39A8,SLC3A1,SLC40A1,SLC46A1,SLC52A2,SLC52A3,SLC5A1,SLC6A19,SLC6A20,SLC6A3,SLC6A8,SLC7A7,SLC7A9,SMPD1,SPG7,SPR,SPTLC1,SPTLC2,SRD5A3,SSR4,ST3GAL3,ST3GAL5,STS,SUCLA2,SUCLG1,SUMF1,SUOX,SURF1,TACO1,TAFAZZIN,TALDO1,TANGO2,TAT,TCN2,TFR2,TIMM8A,TK2,TMEM165,TMEM70,TPK1,TPP1,TRAP1,TREX1,TRIM37,TRMU,TRNT1,TRPM6,TSFM,TTC19,TTC37,TTPA,TUFM,TUSC3,TWNK,TYMP,UGT1A1,UMOD,UMPS,UQCRB,UROD,UROS,VARS2,VIPAS39,VKORC1,VPS33B,WDR45,WFS1,XDH,XYLT1,XYLT2,YARS2,ALG13,ATP5F1A,ATP5F1E,COX4I2,CSTB,DHDDS,GLS,HSPA9,LIPC,MRPS16,NDUFA12,NDUFB9,NDUFC2,OPLAH,PDK3,PIGM,PSPH,RANBP2,RNASET2,RYR1,SDHAF2,SDHC,STAT2,TH,UQCRQ,UROC1".split(",").sorted().toList(),
            method = SingleGeneAnalysisMethod(target = "HPRT1 on Chromosome Xq26.2-q26.3")
        )
        val S104 = SingleGenePanelImpl(
            code = "S104", gene = "GBA", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "GBA on Chromosome 1q22")
        )
        val S123 = SingleGenePanelImpl(
            code = "S123", gene = "PHEX", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "PHEX on Chromosome Xp22.2-p22.1")
        )
        val S125 = SingleGenePanelHasReferralDefault(
            code = "S125", gene = "OPA1", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "OPA1 on Chromosome 3q29"), referralDefault = "Optic atrophy 1"
        )
        val S126 = SingleGenePanelHasReferralDefault(
            code = "S126", gene = "SCN4A", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "SCN4A on Chromosome 17q23.3"), referralDefault = "Skeletal Muscle Channelopathy"
        )
        val S127 = SingleGenePanelHasReferralDefault(
            code = "S127", gene = "SLC12A3", serialGroup = "WES",
            method = SingleGeneAnalysisMethod(target = "SLC12A3 on Chromosome 16q13"), referralDefault = "Gitelman syndrome"
        )
        val S128 = SingleGenePanelHasReferralDefault(
            code = "S128", gene = "TSC1", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "TSC1 on Chromosome 9q34.13"), referralDefault = "Tuberous sclerosis complex",
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.RareDisease
        )
        val S129 = SingleGenePanelHasReferralDefault(
            code = "S129", gene = "TSC2", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "TSC2 on Chromosome 16p13.3"),
            referralDefault = "Tuberous sclerosis complex",
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.RareDisease
        )
        val X009 = SingleGenePanelImpl(
            code = "X009", gene = "TP53", serialGroup = "BRCA",
            method = SingleGeneAnalysisMethod(target = "TP53 on Chromosome 17p13.1"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val Z137 = SingleGenePanelImpl(
            code = "Z137", gene = "BRCA1", serialGroup = "BRCA",
            method = SingleGeneAnalysisMethod(target = "BRCA1 on Chromosome 17q21.31", penetrance = "Breast cancer(50-80%), Secondary Breast cancer(27% within 5 yrs), Ovarian cancer(24-40%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val Z138 = SingleGenePanelImpl(
            code = "Z138", gene = "BRCA2", serialGroup = "BRCA",
            method = SingleGeneAnalysisMethod(target = "BRCA2 on Chromosome 13q13.1", penetrance = "Breast cancer(40-70%), Secondary breast cancer(12% within 5 yrs, 40-50% at 20 yrs), Ovarian cancer(11-18%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val Z141 = SingleGenePanelImpl(
            code = "Z141", gene = "MLH1", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "MLH1 on Chromosome 3p22.2", penetrance = "Colorectal cancer(52%-82%), Endometrial cancer(25%-60%), Gastric cancer(6%-13%), Ovarian cancer(4-12%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val Z962 = SingleGenePanelImpl(
            code = "Z962", gene = "MSH2", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "MSH2 on Chromosome 2p21-p16", penetrance = "Colorectal cancer(52%-82%), Endometrial cancer(25%-60%), Gastric cancer(6%-13%), Ovarian cancer(4-12%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val Z964 = SingleGenePanelImpl(
            code = "Z964", gene = "APC", serialGroup = "CAN",
            method = SingleGeneAnalysisMethod(target = "APC on Chromosome 5q22.2"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val G068 = SingleGenePanelImpl(
            code = "G068", gene = "BRCA1", serialGroup = "BRCA",
            method = SingleGeneAnalysisMethod(target = "BRCA1 on Chromosome 17q21.31", penetrance = "Breast cancer (50-80%), Secondary Breast cancer (27% within 5 yrs), Ovarian cancer (24-40%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        val G069 = SingleGenePanelImpl(
            code = "G069", gene = "BRCA2", serialGroup = "BRCA",
            method = SingleGeneAnalysisMethod(target = "BRCA2 on Chromosome 13q13.1", penetrance = "Breast cancer (40-70%), Secondary breast cancer (12% within 5 yrs, 40-50% at 20 yrs), Ovarian cancer (11-18%)"),
            category = HasCategory.Category.Cancer, interpretationCategory = Interpretable.Category.Cancer, reportCategory = Reportable.Category.Cancer
        )
        fun values() = listOf(
            N027,
            N057, N059,
            S030, S032, S034,
            S053, S054, S055,
            S061, S062, S063, S064, S065, S067,
            S104,
            S123,
            S125, S126, S127, S128, S129,
            X009,
            Z137, Z138,
            Z141,
            Z962, Z964,
            G068, G069,
        )
        open class SingleGenePanelImpl(
            val code: String,
            val gene: String,
            val method: SingleGeneAnalysisMethod,
            val name: String = when (code) {
                "G068", "G069", "N059", "S104" -> "$gene gene mutation / 전용"
                else -> "$gene gene mutation"
            },
            val serialGroup: String,
            val title: String = "$name 결과보고서",
            val addendum: List<String> = listOf(),
            val nationalInsurance: Boolean = true,
            val category: HasCategory.Category = HasCategory.Category.Single,
            val interpretationCategory: Interpretable.Category = Interpretable.Category.Single,
            val reportCategory: Reportable.Category = Reportable.Category.Single,
            val i18n: Reportable.I18N = Reportable.I18N.KoKr,
        ): SingleGenePanel, IsSingleGenePanelAnalysis by method {
            val genes = listOf(gene) + addendum
            override fun code() = code
            override fun category() = category
            override fun interpretationCategory() = interpretationCategory
            override fun reportCategory() = reportCategory
            override fun name() = name
            override fun serialGroup(): String = serialGroup
            override fun genes() = genes
            override fun addendum() = addendum
            override fun isNationalInsuranceTest() = nationalInsurance
            override fun i18n() = i18n
            override fun title() = title
        }
        data class SingleGeneAnalysisMethod (
            private val specimen: String = "Genomic DNA isolated from peripheral blood leukocytes",
            private val target: String,
            private val sequencing: String = "Sequencing of all coding exons",
            private val penetrance: String? = null,
            private val limitations: List<String> = listOf(
                "본 검사는 염기서열분석법으로 시행되었으며, Large deletion/duplication 검출은 제한적입니다.",
                "본 검사에서 발견된 변이는 2015 ACMG/AMP Guidelines (Genet Med 2015;17:405-24)에 따라 'Pathogenic variant (PV)', 'Likely pathogenic variant (LPV)', 'Variant of uncertain significance (VUS)', 'Likely benign variant (LBV)', 'Benign variant (BV)'의 다섯가지 카테고리로 분류되며, LBV와 BV는 보고하지 않습니다. 또한, 추가 연구 결과에 따라 해당 변이의 분류가 변경 될 수 있습니다."
            )
        ) : IsSingleGenePanelAnalysis {
            override fun specimen() = specimen
            override fun target() = target
            override fun sequencing() = sequencing
            override fun penetrance() = penetrance
            override fun limitations() = limitations
        }
        class SingleGenePanelHasReferralDefault(
            code: String, serialGroup: String,
            method: SingleGeneAnalysisMethod,
            gene: String,
            name: String = "$gene gene mutation",
            title: String = "$name 결과보고서",
            addendum: List<String> = listOf(),
            val referralDefault: String,
            category: HasCategory.Category = HasCategory.Category.Single,
            interpretationCategory: Interpretable.Category = Interpretable.Category.Single,
            reportCategory: Reportable.Category = Reportable.Category.Single,
        ): SingleGenePanelImpl(code=code, gene=gene, serialGroup = serialGroup, title=title, method=method, addendum = addendum, category = category, interpretationCategory = interpretationCategory, reportCategory = reportCategory), HasReferralDefault {
            override fun referralDefault(): String = referralDefault
            override fun i18n(): Reportable.I18N {
                TODO("Not yet implemented")
            }
        }
    }
}