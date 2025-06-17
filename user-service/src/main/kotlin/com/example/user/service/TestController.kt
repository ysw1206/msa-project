package com.example.user.service

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

@RestController
class TestController(private val restTemplate: RestTemplate) {

    @GetMapping("/test-auth")
    fun testAuth(@RequestHeader("Authorization") authorization: String): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.set("Authorization", authorization)
        val entity = HttpEntity<String>(headers)
        val response = restTemplate.exchange(
            "http://auth-service/hello",
            HttpMethod.GET,
            entity,
            String::class.java
        )
        return response
    }
}
