package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.PanelAnalysisMethod
import com.gcgenome.lims.test.method.PanelAnalysisMethod.Companion.WesAnalysisMethod

interface Wes: HasCode, HasName, HasCategory, HasSerialGroup, MayBeNationalInsurance, Interpretable, Reportable {
    companion object {
        private const val DGS_LIMITATION_ENUS = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified.";
        private val DGS_GUIDELINE_ENUS = listOf(
            "Genomic DNA was extracted from EDTA whole blood and sequenced with paired-end reads on Illumina NovaSeq 6000 system. The DNA sequence reads were aligned to reference sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants. CNV calling is based on parliament2 pipeline.",
            "Evaluation is focused on coding exons along with flanking +/-20 intronic bases, however extended to the complete gene region for candidate genes or in search for a second previously described variant in autosomal recessive inheritance pattern. Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Variants that pass internal QC criteria are not validated by Sanger sequencing."
        )
        private val T016 = WesImpl(code = "T016", name = "WES Interpretation Only", title = "Whole Exome Sequencing (WES) Report")
        private val T023 = WesImpl(
            code = "T023", name = "WES (영문)", title = "Whole Exome Sequencing Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using MGIEasy Exome Capture V5 (MGI). Sequencing was " +
                            "performed on DNBSEQ-G400 (MGI) or DNBSEQ-T7 (MGI) platform generating 2 × 100 bp paired-end reads. The DNA sequence reads were aligned to reference " +
                            "sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify " +
                            "sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely " +
                            "pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. " +
                            "Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass " +
                            "internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val R2300101 = WesImpl(code = "R2300101", name = "WES_ASD 연구 검사", title = "Whole Exome Sequencing Test 결과보고서")
        private val N208 = WesImpl(code = "N208", name = "Whole Exome Sequencing_(국내)", title = "Whole Exome Sequencing Test 결과보고서")
        private val N209 = WesImpl(code = "N209", name = "WES_Trio(Proband 국내)", title = "WES_Trio(Proband) 결과보고서")
        private val N211 = WesImpl(code = "N211", name = "WES_Trio(모)")
        private val N210 = WesImpl(code = "N210", name = "WES_Trio(부)")
        private val ON201 = WesImpl(
            code = "ON201", name = "Whole Exome Sequencing_(WES)", title = "Whole Exome Sequencing Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using xGen™ Exome Hybridization Panel. Sequencing was performed on  Novaseq 6000DX platform generating 2 x 150 paired end reads. The DNA sequence reads were aligned to reference sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."

            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON209 = WesImpl(
            code = "ON209", name = "WES_Trio(Proband)", title = "WES_Trio(Proband) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using xGen™ Exome Hybridization Panel. Sequencing was performed on  Novaseq 6000DX platform generating 2 x 150 paired end reads. The DNA sequence reads were aligned to reference sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON210 = WesImpl(
            code = "ON210", name = "WES_Trio(Father)", title = "WES_Trio(Father) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using xGen™ Exome Hybridization Panel. Sequencing was performed on  Novaseq 6000DX platform generating 2 x 150 paired end reads. The DNA sequence reads were aligned to reference sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val ON211 = WesImpl(
            code = "ON211", name = "WES_Trio(Mother)", title = "WES_Trio(Mother) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using xGen™ Exome Hybridization Panel. Sequencing was performed on  Novaseq 6000DX platform generating 2 x 150 paired end reads. The DNA sequence reads were aligned to reference sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs
        )
        private val T001 = WesImpl(
            code = "T001", name = "WES", title = "Whole Exome Sequencing Test 결과보고서",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 MGIEasy Exome Capture V5 (MGI)을 이용하여 전체 exon을 capture하여 DNBSEQ- G400 (MGI) 또는 DNBSEQ-T7 (MGI) 장비의 2 x 100 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
                    "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
                )
            ),
            disposed = true
        )
        private val N134 = WesImpl(
            code = "N134", name = "WES Trio(Proband)",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 MGIEasy Exome Capture V5 (MGI)을 이용하여 전체 exon을 capture하여 DNBSEQ- G400 (MGI) 또는 DNBSEQ-T7 (MGI) 장비의 2 x 100 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
                    "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
                )
            ),
            disposed = true
        )
        private val N135 = WesImpl(
            code = "N135",  name = "WES Trio(부)",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 MGIEasy Exome Capture V5 (MGI)을 이용하여 전체 exon을 capture하여 DNBSEQ- G400 (MGI) 또는 DNBSEQ-T7 (MGI) 장비의 2 x 100 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
                    "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
                )
            ),
            disposed = true
        )
        private val N136 = WesImpl(
            code = "N136", name = "WES Trio(모)",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "검사 대상자의 말초혈액 백혈구 DNA를 추출한 후 MGIEasy Exome Capture V5 (MGI)을 이용하여 전체 exon을 capture하여 DNBSEQ- G400 (MGI) 또는 DNBSEQ-T7 (MGI) 장비의 2 x 100 paired end reads 방법으로 염기서열분석을 시행하였습니다. GRCh37/hg19 표준염기서열과 비교하여 질환 관련 변이를 확인하였으며, 자체 설정한 생물정보학적 분석 기준에 따라 Sanger sequencing을 생략할 수 있습니다.",
                    "검사에서 발견된 변이는 2015 ACMG/AMP guidelines (Richards et al., 2015)에 따라 \"pathogenic\", \"likely pathogenic\", \"uncertain significance\", \"likely benign\", \"benign\" 의 다섯가지 카테고리로 분류되며, 환자의 임상 증상과 관련된 Pathogenic variant 및 Likely pathogenic variant에 대해서 주로 보고하고 있으며, 의미가 불분명한 변이(VUS)에 대해서는 질환 관련성이 낮다고 판단될 경우 판독자 재량에 따라 보고되지 않을 수도 있습니다. 추후 관련 문헌과 데이터베이스의 추가적인 연구 결과에 따라 해당 변이의 분류가 변경될 수 있습니다."
                )
            ),
            disposed = true
        )
        private val ON134 = WesImpl(
            code = "ON134", name = "WES Trio(Proband)", title = "WES Trio(Proband) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using MGIEasy Exome Capture V5 (MGI). Sequencing was " +
                            "performed on DNBSEQ-G400 (MGI) or DNBSEQ-T7 (MGI) platform generating 2 × 100 bp paired-end reads. The DNA sequence reads were aligned to reference " +
                            "sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify " +
                            "sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely " +
                            "pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. " +
                            "Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass " +
                            "internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs, disposed = true
        )
        private val ON135 = WesImpl(
            code = "ON135", name = "WES Trio(Father)", title = "WES Trio(Father) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using MGIEasy Exome Capture V5 (MGI). Sequencing was " +
                            "performed on DNBSEQ-G400 (MGI) or DNBSEQ-T7 (MGI) platform generating 2 × 100 bp paired-end reads. The DNA sequence reads were aligned to reference " +
                            "sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify " +
                            "sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely " +
                            "pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. " +
                            "Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass " +
                            "internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs, disposed = true
        )
        private val ON136 = WesImpl(
            code = "ON136", name = "WES Trio(Mother)", title = "WES Trio(Mother) Test Report",
            method = WesAnalysisMethod(
                guideline = listOf(
                    "Genomic DNA was extracted from EDTA whole blood and we captured all the exons of human genes using MGIEasy Exome Capture V5 (MGI). Sequencing was " +
                            "performed on DNBSEQ-G400 (MGI) or DNBSEQ-T7 (MGI) platform generating 2 × 100 bp paired-end reads. The DNA sequence reads were aligned to reference " +
                            "sequence based on public human genome build GRCh37/UCSC hg19. Using a in-house bioinformatics pipeline, data were filtered and analysed to identify " +
                            "sequence variants.",
                    "Sequence variants were classified based on the ACMG/AMP guidelines (Richards et al., 2015). Reported results are focused on pathogenic and likely " +
                            "pathogenic variants in genes related to the phenotype of proband, while variants of uncertain significance are only rarely reported at our discretion. " +
                            "Depending on the results of additional studies in the literature and databases, the classification of the variant may change. Variants that pass " +
                            "internal QC criteria are not validated by Sanger sequencing."
                ),
                limitation = "The absence of definitive pathogenic findings does not rule out the diagnosis of a genetic disorder as some genetic abnormalities may be undetectable " +
                        "with this test. It is possible that the genomic region where a disease-causing variant exists in the proband was not captured or sufficiently " +
                        "sequenced with low quality. Additionally, multifactorial disorders and some types of genetic disorders due to nucleotide repeat expansion/contraction, " +
                        "abnormal DNA methylation, and other mechanisms may not be detectable with this test. This test also cannot reliably detect mosaicism, chromosomal " +
                        "aberrations, and deletions/insertions of 20 bp or more. Some genes have inherent sequence properties (for example: repeats, homology, high GC content, " +
                        "rare polymorphisms) that may result in suboptimal data, and variants in those regions may not be reliably identified."
            ),
            i18n = Reportable.I18N.EnUs, disposed = true
        )
        private val N127 = WesImpl(
            code = "N127", name = "DGS", serialGroup = "DGS", title = "Diagnostic Genome Sequencing Test 결과보고서",
            method = PanelAnalysisMethod.DgsAnalysisMethod(), nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val N137 = WesImpl(
            code = "N137", name = "DGS Trio(Proband)", serialGroup = "DGS", title = "DGS Trio(Proband) 결과보고서",
            method = PanelAnalysisMethod.DgsAnalysisMethod(), nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val N138 = WesImpl(
            code = "N138", name = "DGS Trio(부)", serialGroup = "DGS", title = "DGS Trio(부) 결과보고서",
            method = PanelAnalysisMethod.DgsAnalysisMethod(), nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val N139 = WesImpl(
            code = "N139", name = "DGS Trio(모)", serialGroup = "DGS", title = "DGS Trio(모) 결과보고서",
            method = PanelAnalysisMethod.DgsAnalysisMethod(), nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val ON127 = WesImpl(
            code = "ON127", name = "Diagnostic Genome Sequencing (DGS)", serialGroup = "DGS", title = "Diagnostic Genome Sequencing Test Report",
            method = PanelAnalysisMethod.DgsAnalysisMethod(limitation = DGS_LIMITATION_ENUS, guideline = DGS_GUIDELINE_ENUS,),
            i18n = Reportable.I18N.EnUs, nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val ON137 = WesImpl(
            code = "ON137", name = "DGS Trio(Proband)", serialGroup = "DGS", title = "DGS Trio(Proband) Test Report",
            method = PanelAnalysisMethod.DgsAnalysisMethod(limitation = DGS_LIMITATION_ENUS, guideline = DGS_GUIDELINE_ENUS,),
            i18n = Reportable.I18N.EnUs, nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val ON138 = WesImpl(
            code = "ON138", name = "DGS Trio(Father)", serialGroup = "DGS", title = "DGS Trio(Father) Test Report",
            method = PanelAnalysisMethod.DgsAnalysisMethod(
                limitation = DGS_LIMITATION_ENUS, guideline = DGS_GUIDELINE_ENUS,
            ),
            i18n = Reportable.I18N.EnUs, nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        private val ON139 = WesImpl(
            code = "ON139", name = "DGS Trio(Mother)", serialGroup = "DGS", title = "DGS Trio(Mother) Test Report",
            method = PanelAnalysisMethod.DgsAnalysisMethod(limitation = DGS_LIMITATION_ENUS, guideline = DGS_GUIDELINE_ENUS,),
            i18n = Reportable.I18N.EnUs, nationalInsurance = false,
            category = HasCategory.Category.DGS, interpretationCategory = Interpretable.Category.DGS, reportCategory = Reportable.Category.DGS
        )
        val N058 = WesImpl(
            code = "N058", name = "IDS gene mutation / 전용", title = "Whole Exome Sequencing Test 결과보고서",
        )
        fun values() = listOf(
            T001,
            T016, T023,
            N134, N135, N136,
            N208, N209, N210, N211,
            ON134, ON135, ON136,
            ON201, ON209, ON210, ON211,
            R2300101,
            N127,
            N137, N138, N139,
            ON127,
            ON137, ON138, ON139,
        )
        open class WesImpl (
            val code: String,
            val name: String,
            val serialGroup: String = "WES",
            val title: String = "$name 결과보고서",
            val method: PanelAnalysisMethod = WesAnalysisMethod(),
            val nationalInsurance: Boolean = false,
            val i18n: Reportable.I18N = Reportable.I18N.KoKr,
            val disposed: Boolean = false,
            val category: HasCategory.Category = HasCategory.Category.WES,
            val interpretationCategory: Interpretable.Category = Interpretable.Category.WES,
            val reportCategory: Reportable.Category = Reportable.Category.WES
        ) :Wes {
            override fun code() = code
            override fun name() = name
            override fun serialGroup(): String = serialGroup
            override fun title() = title
            override fun category() = category
            override fun interpretationCategory() = interpretationCategory
            override fun reportCategory() = reportCategory
            override fun isNationalInsuranceTest() = nationalInsurance
            override fun i18n(): Reportable.I18N = i18n
        }
    }
}
