package com.example.auth.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello() = "Hello from auth-service"

    @GetMapping("/auth/check")
    fun check() = "Auth service is running"
}
