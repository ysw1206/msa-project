package com.example.user.service

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    
    private val logger = LoggerFactory.getLogger(HelloController::class.java)
    
    @GetMapping("/hello")
    fun hello(
        @RequestHeader(value = "X-User-Id", required = false) userId: String?,
        @RequestHeader(value = "X-Username", required = false) username: String?
    ): String {
        logger.info("=== Gateway Header Information ===")
        logger.info("X-User-Id: {}", userId ?: "NOT_PROVIDED")
        logger.info("X-Username: {}", username ?: "NOT_PROVIDED")
        logger.info("===================================")
        
        return "Hello from user-service! User ID: $userId, Username: $username"
    }
}
