package com.gcgenome.lims.dto;

import java.time.LocalDate;

public record Request(
        Sample sample,
        String service,
        LocalDate dateRequest,
        LocalDate dateReception,
        LocalDate dateDue,
        LocalDate dateDuePublish,
        String info,
        boolean register,
        boolean cancel,
        boolean delete
) { }