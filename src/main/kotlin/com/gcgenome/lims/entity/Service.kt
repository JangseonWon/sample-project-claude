package com.gcgenome.lims.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("public.service")
data class Service(
    @Id @Column("id") val id: String,
    @Column("name") val name: String,
)