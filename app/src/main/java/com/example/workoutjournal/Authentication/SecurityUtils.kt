// SecurityUtils.kt
package com.example.workoutjournal.Authentication

import java.math.BigInteger
import java.security.MessageDigest

object SecurityUtils {
    fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(password.toByteArray())
        val hash = BigInteger(1, digest).toString(16)
        return hash.padStart(32, '0')
    }
}