package com.gcgenome.lims.test;

public interface Interpretable {
    Category interpretationCategory();
    enum Category {
        DES, WES, DGS, RareDisease, Single, SinglePlus, GenomeScreen, Cancer, BloodCancer, SolidTumor, NonTSO, MRD, HRD, SANGER, ClonalHematopoiesis,
        SingleWithMLPA, WesWithSingle,
        ETC
    }
}
