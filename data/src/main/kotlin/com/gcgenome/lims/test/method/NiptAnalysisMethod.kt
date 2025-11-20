package com.gcgenome.lims.test.method

import com.gcgenome.lims.test.IsNiptAnalysis
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.AutosomalChromosomeTrisomyTarget.*
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.BirthPlurality.Multiple
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.BirthPlurality.Single
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.Metrics.*
import com.gcgenome.lims.test.method.NiptAnalysisMethod.Companion.SexChromosomeTrisomyTarget.*
import java.text.DecimalFormat
import java.time.LocalDate

data class NiptAnalysisMethod (
    val method: String = "Ver. G-NIPT 2.0",
    val pipeline: String = "Ver. G-NIPT BI 1.2",
    val performance: Map<BirthPlurality, Performance>,
    val target: Target,
): IsNiptAnalysis {
    override fun method(): String = method
    override fun pipeline(): String = pipeline

    companion object {
        enum class BirthPlurality {
            Single, Multiple
        }
        enum class AutosomalChromosomeTrisomyTarget(private val label: String) {
            Trisomy1  ("Trisomy 1"), Trisomy2  ("Trisomy 2"), Trisomy3  ("Trisomy 3"), Trisomy4  ("Trisomy 4"),
            Trisomy5  ("Trisomy 5"), Trisomy6  ("Trisomy 6"), Trisomy7  ("Trisomy 7"), Trisomy8  ("Trisomy 8"),
            Trisomy9  ("Trisomy 9"), Trisomy10("Trisomy 10"), Trisomy11("Trisomy 11"), Trisomy12("Trisomy 12"),
            Trisomy13("Trisomy 13"), Trisomy14("Trisomy 14"), Trisomy15("Trisomy 15"), Trisomy16("Trisomy 16"),
            Trisomy17("Trisomy 17"), Trisomy18("Trisomy 18"), Trisomy19("Trisomy 19"), Trisomy20("Trisomy 20"),
            Trisomy21("Trisomy 21"), Trisomy22("Trisomy 22");
            override fun toString() = label
            companion object {
                val allWithoutT13T18T21 = entries - Trisomy13 - Trisomy18 - Trisomy21
            }
        }
        enum class AutosomalChromosomeMonosomyTarget(private val label: String) {
            Monosomy1  ("Monosomy 1"), Monosomy2  ("Monosomy 2"), Monosomy3  ("Monosomy 3"), Monosomy4  ("Monosomy 4"),
            Monosomy5  ("Monosomy 5"), Monosomy6  ("Monosomy 6"), Monosomy7  ("Monosomy 7"), Monosomy8  ("Monosomy 8"),
            Monosomy9  ("Monosomy 9"), Monosomy10("Monosomy 10"), Monosomy11("Monosomy 11"), Monosomy12("Monosomy 12"),
            Monosomy13("Monosomy 13"), Monosomy14("Monosomy 14"), Monosomy15("Monosomy 15"), Monosomy16("Monosomy 16"),
            Monosomy17("Monosomy 17"), Monosomy18("Monosomy 18"), Monosomy19("Monosomy 19"), Monosomy20("Monosomy 20"),
            Monosomy21("Monosomy 21"), Monosomy22("Monosomy 22");
            override fun toString() = label
            companion object {
                val all = entries
            }
        }
        val cnvTargetPrime = listOf(
            "1p deletion", "1p duplication", "1q duplication", "1p36 deletion", "1p32.2p31.3 deletion", "1q41q42 deletion",
            "1q43q44 deletion", "1q terminal duplication", "2p deletion", "2p duplication", "2q duplication", "2p25.3 deletion", "2p16.1p15 deletion", "2q11.2 duplication",
            "2q13 deletion", "2q13 duplication", "2q31.1 duplication", "2q31.1 deletion", "2q33.1 deletion", "2q37.3 deletion", "3p deletion", "3p duplication",
            "3q duplication", "3p26.3p25.3 deletion", "3q13.31 deletion", "3q24 deletion", "3q29 deletion", "4p deletion", "4p duplication", "4q duplication", "Wolf-Hirschhorn Syndrome", "4p16.3 duplication",
            "4q21 deletion", "4q terminal deletion", "5p duplication", "5q duplication", "Cri-du-chat Syndrome", "5q11.2q12.1 deletion", "5q14.3q15 deletion", "Sotos Syndrome", "5q35 duplication",
            "6p deletion", "6p duplication", "6q duplication", "6p25.3p25.2 deletion", "6p25.1p24.3 deletion", "6q12q14.1 deletion", "6q13q14.2 deletion", "6q13q16.1 deletion",
            "6q14.1q14.3 deletion", "6q14.3q16.3 deletion", "6q24q25 deletion", "6q25 deletion", "7p duplication", "7q duplication", "Williams Syndrome",
            "7q11.23 duplication", "7q31 deletion", "7q34q36.1 deletion", "8p deletion", "8p duplication", "8q duplication", "8p terminal deletion", "8p23.1 deletion",
            "8p23.1 duplication", "8q21.11 deletion", "8q22.1 deletion", "Trichorhinophalangeal Syndrome type 2", "9p deletion", "9p duplication", "9q duplication",
            "9q31.1q31.3 deletion", "9q terminal deletion", "10p deletion", "10p duplication", "10q duplication", "10p15.3 deletion", "10p14p12 deletion", "10q11.22q11.23 deletion",
            "10q22.3q23.2 deletion", "10q26 deletion", "Beckwith-Wiedemann Syndrome", "WAGR Syndrome", "Potocki-Shaffer Syndrome", "11q13.2q13.4 deletion", "Jacobsen Syndrome",
            "12p duplication", "12p deletion", "12q deletion", "12p13.33 deletion", "12q13.3q14. 1 deletion", "12q14 deletion", "13q12.11q12.13 deletion", "13q14 deletion",
            "13q21.33q31.1 deletion", "13q terminal deletion", "14q12 deletion", "14q13.2q21.1 deletion", "14q22.1q22.2 deletion", "14q31.3q32.12 deletion", "15q duplication",
            "Prader-Willi Syndrome /Angelman Syndrome", "15q11q13 duplication", "15q14 deletion", "15q24 deletion", "15q26.3 duplication", "15q26.3 deletion", "16p duplication",
            "16q deletion", "16q24.1 deletion", "17p duplication", "Miller-Dieker lissencephaly Syndrome", "17p13.3p duplication", "Smith-Magenis Syndrome",
            "Potocki-Lupski Syndrome", "Yuan-Harel-Lupski Syndrome", "17q11.2 deletion", "17q11.2 duplication", "17q12 deletion", "17q12 duplication",
            "17q23.1q23.2 deletion", "18p duplication", "18p deletion", "18q11.2q12.1 deletion", "18q terminal deletion", "19p13.3 duplication", "19q13.11 deletion",
            "20p deletion", "20p duplication", "20p terminal duplication", "20p12.3 deletion", "21q deletion", "21q22 deletion", "Cat eye Syndrome",
            "DiGeorge Syndrome", "22q11.2 deletion", "Phelan-McDermid Syndrome", "Xp21 deletion", "Xp11.22p11.23 duplication", "Xq21 deletion"
        )
        val cnvTargetEssential = listOf(
            "1p36 deletion", "Wolf-Hirschhorn Syndrome", "Cri-du-chat Syndrome", "Williams Syndrome",
            "Jacobsen Syndrome", "Prader-Willi Syndrome /Angelman Syndrome", "Dandy-Walker malformation", "DiGeorge Syndrome"
        )
        enum class SexChromosomeTrisomyTarget(private val label: String) {
            XO("XO"), XXX("XXX"), XXY("XXY"), XYY("XYY");
            override fun toString() = label
        }
        enum class Metrics {
            Sensitivity, Specificity, PPV, NPV
        }
        data class MetricsValue (
            private val value: Double?,
            private val prefix: String = "",
            private val format: DecimalFormat = DecimalFormat("00.00")
        ) {
            override fun toString() = if(value!=null) "$prefix${format.format(value)}%" else "-"
        }
        data class Performance (
            val autosomals: Map<AutosomalChromosomeTrisomyTarget, Map<Metrics, MetricsValue>>,
            val sexes: Map<SexChromosomeTrisomyTarget, Map<Metrics, MetricsValue>>,
            val durationFrom: LocalDate = LocalDate.of(2015, 12, 1),
            val durationTo: LocalDate = LocalDate.of(2023, 12, 31),
        )
        data class Target (
            val trisomies: List<AutosomalChromosomeTrisomyTarget>,
            val monosomies: List<AutosomalChromosomeMonosomyTarget>,
            val cnvs: List<String>
        )
        private val performanceTrisomy21Ko = Trisomy21 to mapOf(
            Sensitivity to MetricsValue(99.85),
            Specificity to MetricsValue(99.98),
            PPV to MetricsValue(98.69),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceTrisomy18Ko = Trisomy18 to mapOf(
            Sensitivity to MetricsValue(99.52),
            Specificity to MetricsValue(99.97),
            PPV to MetricsValue(93.24),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceTrisomy13Ko = Trisomy13 to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.98),
            PPV to MetricsValue(82.81),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXOKo = XO to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.93),
            PPV to MetricsValue(24.49),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXXXKo = XXX to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.96),
            PPV to MetricsValue(50.00),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXXYKo = XXY to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.99),
            PPV to MetricsValue(82.98),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXYYKo = XYY to mapOf(
            Sensitivity to MetricsValue(null),
            Specificity to MetricsValue(99.99, ">"),
            PPV to MetricsValue(null),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceAutosomalKo = mapOf(performanceTrisomy21Ko, performanceTrisomy18Ko, performanceTrisomy13Ko)
        private val performanceSexKo = mapOf(performanceXOKo, performanceXXXKo, performanceXXYKo, performanceXYYKo)
        val performanceKo = mapOf(
            Single to Performance(autosomals = performanceAutosomalKo, sexes = performanceSexKo),
            Multiple to Performance (autosomals = performanceAutosomalKo, sexes = mapOf())
        )
        private val performanceTrisomy21Oversea = Trisomy21 to mapOf(
            Sensitivity to MetricsValue(99.85),
            Specificity to MetricsValue(99.98),
            PPV to MetricsValue(98.69),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceTrisomy18Oversea = Trisomy18 to mapOf(
            Sensitivity to MetricsValue(99.52),
            Specificity to MetricsValue(99.97),
            PPV to MetricsValue(93.24),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceTrisomy13Oversea = Trisomy13 to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.98),
            PPV to MetricsValue(82.81),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXOOversea = XO to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.93),
            PPV to MetricsValue(24.49),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXXXOversea = XXX to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.96),
            PPV to MetricsValue(50.00),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXXYOversea = XXY to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.99),
            PPV to MetricsValue(82.98),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceXYYOversea = XYY to mapOf(
            Sensitivity to MetricsValue(99.99, ">"),
            Specificity to MetricsValue(99.99, ">"),
            PPV to MetricsValue(99.99, ">"),
            NPV to MetricsValue(99.99, ">")
        )
        private val performanceAutosomalOversea = mapOf(performanceTrisomy21Oversea, performanceTrisomy18Oversea, performanceTrisomy13Oversea)
        private val performanceSexOversea = mapOf(performanceXOOversea, performanceXXXOversea, performanceXXYOversea, performanceXYYOversea)
        val performanceOversea = mapOf(
            Single to Performance(autosomals = performanceAutosomalOversea, sexes = performanceSexOversea),
            Multiple to Performance (autosomals = performanceAutosomalOversea, sexes = mapOf())
        )
    }
}