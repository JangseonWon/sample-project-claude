package com.gcgenome.lims.test.method

import com.gcgenome.lims.test.ContainsGenes
import com.gcgenome.lims.test.IsPanelAnalysis

data class MrdAnalysisMethod (
    val genes: List<String>,
    val cell: String,
    val probe: String,
    val sequencing: String,
    val reference: String,
    val panel: String,
    val pipeline: String,
    val abbreviation: String = "",
    val subpanel: String = "",
): IsPanelAnalysis, ContainsGenes {
    override fun genes() = genes
    override fun panel() = panel
    override fun region() = null
    override fun probe() = probe
    override fun sequencing() = sequencing
    override fun reference() = reference
    override fun pipeline() = pipeline
    override fun abbreviation() = abbreviation
    override fun subpanel() = subpanel
    companion object {
        private fun lymphotrackMethod(
            panel: String,
            genes: List<String>,
            cell: String,
            primer: String = "Amplicon with oligonucleotide primers",
            pipeline: String = "LymphoTrack®",
            sequencing: String = "MiSeq Dx",
            reference: String = "hg19",
        ) = MrdAnalysisMethod(genes=genes, cell=cell, probe=primer, sequencing=sequencing, reference=reference, panel=panel, pipeline=pipeline)
        private fun lymphotrackBcellMethod(
            panel: String,
            genes: List<String>
        ) = lymphotrackMethod(panel, genes, "B-cell")
        val lymphotrackIghMethod = lymphotrackBcellMethod("LymphoTrack IGH FR1 Assay Panel", listOf("IGH"))
        val lymphotrackIghIgkMethod = lymphotrackBcellMethod("LymphoTrack IGH FR1 Assay Panel & IGK Assay Panel", listOf("IGH", "IGK"))
        private fun lymphotrackTcellMethod(
            panel: String,
            genes: List<String>
        ) = lymphotrackMethod(panel, genes, "T-cell")
        val lymphotrackTrbTrgMethod = lymphotrackTcellMethod("LymphoTrack TRB Assay Panel & TRG Assay Panel", listOf("TRB", "TRG"))
    }
}