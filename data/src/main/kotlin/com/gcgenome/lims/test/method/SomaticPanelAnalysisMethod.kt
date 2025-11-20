package com.gcgenome.lims.test.method

import com.gcgenome.lims.test.IsPanelAnalysis

data class SomaticPanelAnalysisMethod (
    val panel: String,
    val region: String,
    val probe: String,
    val sequencing: String,
    val reference: String,
    val pipeline: String,
    val abbreviation: String,
    val subpanel: String,
    val method: String = ""
): IsPanelAnalysis {
    override fun panel() = panel
    override fun region() = region
    override fun probe() = probe
    override fun sequencing() = sequencing
    override fun reference() = reference
    override fun pipeline() = pipeline
    override fun abbreviation() = abbreviation
    override fun subpanel() = subpanel
    companion object {
        fun BloodCancerAnalysisMethod(
            panel: String,
            region: String,
            probe: String = "Hybridization with oligonucleotide probes (HEMA v.2504.1)",
            sequencing: String = "Sequencing by synthesis (Illumina)",
            reference: String = "GRCh37/hg19",
            pipeline: String = "BI_HEM.v.1.2 (Alignment: BWA, Variant calling: VarScan2_GATK)",
            abbreviation: String,
            subpanel: String
        ) = SomaticPanelAnalysisMethod(panel, region, probe, sequencing, reference, pipeline, abbreviation, subpanel)
        fun STOPanelAnalysisMethod(
            panel: String,
            region: String,
            probe: String = "Hybridization with oligonucleotide probes",
            sequencing: String = "Sequencing by synthesis (Illumina Novaseq6000Dx)",
            reference: String = "GRCh37/hg19",
            pipeline: String,
            abbreviation: String,
            subpanel: String
        ) = SomaticPanelAnalysisMethod(panel, region, probe, sequencing, reference, pipeline, abbreviation, subpanel)
        fun NonTsoPanelAnalysisMethod(
            panel: String,
            region: String,
            probe: String = "Hybridization with oligonucleotide probes",
            sequencing: String = "Sequencing by synthesis (Illumina Novaseq6000Dx)",
            reference: String = "GRCh37/hg19",
            pipeline: String,
            abbreviation: String = "",
            subpanel: String = "",
            method: String
        ) = SomaticPanelAnalysisMethod(panel, region, probe, sequencing, reference, pipeline, abbreviation, subpanel, method)
    }
}