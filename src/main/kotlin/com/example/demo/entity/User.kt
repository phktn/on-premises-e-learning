package com.example.demo.entity

import org.apache.logging.log4j.util.Strings
import javax.persistence.*

@Table(name = "user")
@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "name", length = 60, nullable = false)
        val name: String = Strings.EMPTY,

        @Column(name = "email", length = 120, nullable = false, unique = true)
        val email: String = Strings.EMPTY,

        @Column(name = "password", length = 120, nullable = false)
        val password: String = Strings.EMPTY,

        @Column(name = "roles", length = 120)
        val roles: String? = null,

        @Column(name = "enable_flag", nullable = false)
        val enable: Boolean = false,
)