package com.gcgenome.lims.test;

public interface IsPanelAnalysis {
    String panel();
    String region();
    String probe();
    String sequencing();
    String reference();
    String pipeline();

    String abbreviation();
    String subpanel();
}
