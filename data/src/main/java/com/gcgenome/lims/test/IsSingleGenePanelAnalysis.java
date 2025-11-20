package com.gcgenome.lims.test;

import java.util.List;

public interface IsSingleGenePanelAnalysis {
    String specimen();
    String target();
    String sequencing();
    String penetrance();
    List<String> limitations();
}