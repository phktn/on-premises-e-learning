package com.example.demo.controller

import org.apache.logging.log4j.util.Strings

data class SignupForm(
    val email: String = Strings.EMPTY,
    val password: String = Strings.EMPTY,
    val name: String = Strings.EMPTY,
)
