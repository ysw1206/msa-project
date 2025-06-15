package com.example.auth.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import jakarta.servlet.http.HttpServletResponse

@RestController
class AuthController(
    private val authenticationManager: AuthenticationManager
) {
    @Value("\${jwt.secret:mysecret}")
    private lateinit var jwtSecret: String

    data class LoginRequest(val username: String, val password: String)
    data class TokenResponse(val token: String)

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest, response: HttpServletResponse): Any {
        return try {
            val auth = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(req.username, req.password)
            )
            val user = auth.principal as UserDetails
            val token = JwtUtil.createToken(user.username, jwtSecret)
            TokenResponse(token)
        } catch (e: AuthenticationException) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            mapOf("error" to "Invalid credentials")
        }
    }
} 