package com.gcgenome.lims.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class Router(val handler: Handler) {
    @Bean("com.gcgenome.lims.Router")
    fun router() = router {
        GET("/services") { ServerResponse.ok().build() }
        GET("/samples/{sample:[0-9-]+}",            ::sample)
        GET("/samples/{sample:[0-9-]+}/services",   contentType(MediaType("application", "vnd.lims.v1", Charsets.UTF_8)), ::service)
        GET("/samples/{sample:[0-9-]+}/services/{service}/requests",   contentType(MediaType("application", "vnd.lims.v1", Charsets.UTF_8)), ::request)
        GET("/samples/{sample:[0-9-]+}/siblings",   ::siblings)
        GET("/samples/{sample:[0-9-]+}/services/{service}/subjects", ::subjects)
    }
    private fun sample(request: ServerRequest): Mono<ServerResponse> {
        val sample = request.pathVariable("sample").toLong()
        return handler.findSampleById(sample).flatMap(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)::bodyValue)
            .switchIfEmpty(ServerResponse.noContent().build())
            .onErrorResume { e: Throwable ->
                e.printStackTrace()
                ServerResponse.badRequest().bodyValue(e.message?:"")
            }
    }
    private fun service(request: ServerRequest): Mono<ServerResponse> {
        val sample = request.pathVariable("sample").toLong()
        return handler.findServices(sample).collectList()
            .flatMap(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)::bodyValue)
            .switchIfEmpty(ServerResponse.noContent().build())
            .onErrorResume { e: Throwable ->
                e.printStackTrace()
                ServerResponse.badRequest().bodyValue(e.message?:"")
            }
    }
    private fun siblings(request: ServerRequest): Mono<ServerResponse> {
        val sample = request.pathVariable("sample").toLong()
        return handler.findSiblings(sample).collectList()
            .flatMap(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)::bodyValue)
            .switchIfEmpty(ServerResponse.noContent().build())
            .onErrorResume { e: Throwable ->
                e.printStackTrace()
                ServerResponse.badRequest().bodyValue(e.message?:"")
            }
    }
    private fun request(request: ServerRequest): Mono<ServerResponse> {
        val sample = request.pathVariable("sample").toLong()
        val service = request.pathVariable("service")
        return handler.findRequestById(sample, service)
            .flatMap(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)::bodyValue)
            .switchIfEmpty(ServerResponse.noContent().build())
            .onErrorResume { e: Throwable ->
                e.printStackTrace()
                ServerResponse.badRequest().bodyValue(e.message?:"")
            }
    }
    private fun subjects(request: ServerRequest): Mono<ServerResponse> {
        val sample = request.pathVariable("sample").toLong()
        val service = request.pathVariable("service")
        return handler.subjects(sample, service).collectList()
            .flatMap(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)::bodyValue)
            .switchIfEmpty(ServerResponse.noContent().build())
            .onErrorResume { e: Throwable ->
                e.printStackTrace()
                ServerResponse.badRequest().bodyValue(e.message?:"")
            }
    }
}