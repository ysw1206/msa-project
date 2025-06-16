package com.example.user.service

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class TestController(private val restTemplate: RestTemplate) {

    @GetMapping("/test-auth")
    fun testAuth(): ResponseEntity<String> {
        val response = restTemplate.getForEntity("http://auth-service/auth/check", String::class.java)
        return response
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate =
        builder.build()
}
