package com.gcgenome.lims.test;

public interface HasCategory {
    Category category();
    enum Category {
        DES, WES, DGS, RareDisease, Single, SinglePlus, GenomeScreen, Cancer, BloodCancer, SolidTumor, NonTSO, MRD, HRD, SANGER, ClonalHematopoiesis,
        SingleWithMLPA, WesWithSingle,
        ALLOSEQ, NIPT, MLPA,
        ClinicalTest,
        ETC
    }
}
