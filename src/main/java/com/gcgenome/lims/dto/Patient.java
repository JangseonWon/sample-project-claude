package com.gcgenome.lims.dto;

import java.time.LocalDate;

public record Patient(
        Organization organization,
        String mrn,
        String name,
        String code,
        String sex,
        Integer age,
        LocalDate birth
) { }
