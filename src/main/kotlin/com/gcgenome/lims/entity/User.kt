package com.gcgenome.lims.entity;

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("public.user")
data class User(
    @Id @Column("id") val id: String,
    @Column("name") val name: String,
    @Column("role") val role: String = ""
) {
    private fun isAdmin(): Boolean = role == "A"
    fun isMaster(): Boolean = (role == "M") || isAdmin()
}