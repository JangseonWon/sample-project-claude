package com.gcgenome.lims

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono

@Configuration
@Order(2)
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig (
    private val securityContextRepository: SecurityContextRepository
) {
    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        val corsConfig = CorsConfiguration()
        corsConfig.addAllowedOriginPattern("*")
        corsConfig.addAllowedMethod("*")
        corsConfig.addAllowedHeader("*")
        corsConfig.allowCredentials = true
        corsConfig.addExposedHeader("X-Total-Count")
        corsConfig.addExposedHeader("X-Total-Page")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return source
    }

    @Bean
    fun resourceFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.securityContextRepository(securityContextRepository)
        return http {
            cors { configurationSource = corsConfiguration() }
            httpBasic { disable() }
            formLogin { disable() }
            csrf { disable() }
            headers {
                frameOptions {
                    mode = XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN
                }
            }
            exceptionHandling {
                authenticationEntryPoint = ServerAuthenticationEntryPoint { exchange, _ -> Mono.fromRunnable { exchange.response.statusCode = HttpStatus.UNAUTHORIZED } }
                accessDeniedHandler = ServerAccessDeniedHandler { exchange, _ -> Mono.fromRunnable { exchange.response.statusCode = HttpStatus.FORBIDDEN } }
            }
            authorizeExchange {
                authorize (
                    ServerWebExchangeMatchers.pathMatchers("/js/**", "/img/**", "/css/**", "/font/**", "/*.html"), permitAll)
                authorize (ServerWebExchangeMatchers.pathMatchers(HttpMethod.OPTIONS, "/**"), permitAll)
                authorize (ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/actuator/health/**"), permitAll)
                authorize (anyExchange, authenticated)
            }
        }
    }
}