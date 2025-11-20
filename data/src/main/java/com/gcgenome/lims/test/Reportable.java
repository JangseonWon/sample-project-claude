package com.gcgenome.lims.test;

public interface Reportable {
    I18N i18n();
    String title();
    Category reportCategory();
    enum I18N {
        KoKr, EnUs
    }
    enum Category {
        DES, WES, DGS, RareDisease, Single, SinglePlus, GenomeScreen, Cancer, BloodCancer, SolidTumor, NonTSO, MRD, HRD, SANGER, ClonalHematopoiesis,
        SingleWithMLPA, WesWithSingle,
        NIPT,
        ETC
    }
}