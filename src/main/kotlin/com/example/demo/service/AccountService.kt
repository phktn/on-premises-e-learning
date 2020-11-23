package com.example.demo.service

import com.example.demo.entity.User

interface AccountService {
    fun findAll(): List<User>
    fun register(name: String, email: String, password: String, roles: Array<String>)
}