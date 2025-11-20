package com.gcgenome.lims.service

import com.gcgenome.lims.entity.Test
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TestRepository : ReactiveCrudRepository<Test, String> {
    fun findByCodeLike(code: String): Flux<Test>
}