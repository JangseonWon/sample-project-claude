package com.gcgenome.lims.dto;

public record Sample(
        Long id,
        Patient patient,
        String type
) { }
