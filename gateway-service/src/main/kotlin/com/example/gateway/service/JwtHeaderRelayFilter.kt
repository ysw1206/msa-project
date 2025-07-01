package com.example.gateway.service

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtHeaderRelayFilter : GlobalFilter, Ordered {
    
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        return ReactiveSecurityContextHolder.getContext()
            .cast(org.springframework.security.core.context.SecurityContext::class.java)
            .map { it.authentication }
            .cast(JwtAuthenticationToken::class.java)
            .map { jwtAuth ->
                val jwt = jwtAuth.token
                val userId = jwt.subject
                val username = jwt.getClaimAsString("preferred_username") ?: jwt.subject
                
                val mutatedRequest = exchange.request.mutate()
                    .header("X-User-Id", userId)
                    .header("X-Username", username)
                    .build()
                
                exchange.mutate().request(mutatedRequest).build()
            }
            .defaultIfEmpty(exchange)
            .flatMap { chain.filter(it) }
    }
    
    override fun getOrder(): Int = -1
} 