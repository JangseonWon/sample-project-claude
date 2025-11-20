package com.gcgenome.lims.service.user

import com.gcgenome.lims.entity.User
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserDao(private val template: R2dbcEntityTemplate) {
    fun findById(id: String): Mono<User> = template.selectOne (Query.query (Criteria.where("id").`is`(id)), User::class.java)
}