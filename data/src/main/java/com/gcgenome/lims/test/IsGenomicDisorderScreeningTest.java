package com.gcgenome.lims.test;

import java.util.List;
import java.util.stream.Collectors;

public interface IsGenomicDisorderScreeningTest extends ContainsGenes {
    @Override
    default List<String> genes() {
        return diseases().stream().flatMap(genomicDisorder -> genomicDisorder.genes.stream()).distinct().collect(Collectors.toList());
    }
    List<GenomicDisorder> diseases();
}
