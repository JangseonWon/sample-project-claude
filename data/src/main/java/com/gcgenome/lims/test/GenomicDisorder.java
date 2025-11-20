package com.gcgenome.lims.test;

import java.util.List;

public class GenomicDisorder {
    public final String name;
    public final List<String> genes;

    public GenomicDisorder(String name, List<String> genes) {
        this.name = name;
        this.genes = genes;
    }

    public String name() {
        return this.name;
    }

    public List<String> genes() {
        return this.genes;
    }

}