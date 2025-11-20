package com.gcgenome.lims.test;

import java.util.List;
import java.util.stream.Collectors;

public interface ContainsExons extends ContainsGenesWithGroup {
    @Override default List<String> genes(int group) {
        return exons(group).stream().map(Exon::gene).distinct().collect(Collectors.toList());
    }

    List<Exon> exons(int tier);
}