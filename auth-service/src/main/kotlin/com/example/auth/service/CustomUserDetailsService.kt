package com.example.auth.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * Simple [UserDetailsService] implementation used for demonstration purposes.
 * In a real application this would query a user repository or external service.
 */
@Service
class CustomUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // Returns a dummy user. Password uses {noop} encoder for simplicity.
        return User.withUsername(username)
            .password("password")
            .roles("USER")
            .build()
    }
}

