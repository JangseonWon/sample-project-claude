package com.gcgenome.lims.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("public.request")
data class Request(
    @Column("sample")           val sample: Long,
    @Column("type")             val sampleType: String? = null,
    @Column("service")          val service: String,
    @Column("date_request")     val dateRequest: LocalDateTime?=null,
    @Column("date_start")       val dateStart: LocalDateTime?=null,
    @Column("date_due")         val dateDue: LocalDateTime?=null,
    @Column("date_sampling")    val dateSampling: LocalDateTime?=null,
    @Column("date_reception")   val dateReception: LocalDateTime?=null,
    @Column("date_due_publish") val dateDuePublish: LocalDateTime?=null,
    @Column("info")             val info: String? = null,
    @Column("register")         val register: Boolean = true,
    @Column("cancel")           val cancel: Boolean = false,
    @Column("delete")           val delete: Boolean = false,
) {
    @Id @Transient val _id: RequestPK = RequestPK(sample, service)
    companion object {
        data class RequestPK(
            val sample: Long,
            val service: String
        )
    }
}