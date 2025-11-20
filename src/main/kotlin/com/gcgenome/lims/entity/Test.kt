package com.gcgenome.lims.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("handbook_test")
data class Test (
    @Id val id: String,
    @Column("effective_date") val effectiveDate: LocalDateTime,
    @Column("expiry_date") val expiryDate: LocalDateTime?,
    val code: String,
    val name: String,
    val labs: String?
)