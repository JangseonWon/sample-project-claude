package com.gcgenome.lims.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("panel.sample000")
data class Sample(
    @Id @Column("sample") val id: Long,
    val organization: String,
    @Column("organization_name") val organizationName: String,
    @Column("sample_type") val sampleType: String?,
    val age: Int? = null,
    @Column("patient_name") val patientName: String,
    @Column("patient_code") val patientCode: String? = null,
    val mrn: String? = null,
    val sex: String?,
    val birth: LocalDate? = null,
    @Column("remark")
    val info: String? = null
)