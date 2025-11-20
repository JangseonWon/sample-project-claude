package com.gcgenome.lims.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ContainsGenesWithGroup extends ContainsGenes {
    @Override default List<String> genes() {
        return IntStream.of(groups()).mapToObj(this::genes).flatMap(List::stream).collect(Collectors.toList());
    }
    int[] groups();
    List<String> genes(int group);
}