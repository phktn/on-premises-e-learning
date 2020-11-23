package com.example.demo.service.impl

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import com.example.demo.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class AccountServiceImpl(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) : AccountService {
    @Transactional(readOnly = true)
    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    override fun register(name: String, email: String, password: String, roles: Array<String>) {
        if (userRepository.findByEmail(email).isPresent) {
            throw RuntimeException("invalid name or email")
        }
        val encodedPassword = passwordEncode(password)
        val joinedRoles = joinRoles(roles)
        log.debug("name:{}, email:{}, roles:{}", name, email, joinedRoles)
        val user = User(null, name, email, encodedPassword, joinedRoles, true)
        userRepository.saveAndFlush(user)
    }

    /**
     * @param rawPassword 平文のパスワード
     * @return 暗号化されたパスワード
     */
    private fun passwordEncode(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    /**
     * @param roles ロール文字列の配列
     * @return カンマ区切りのロール文字列
     */
    private fun joinRoles(roles: Array<String>): String {
        return if (roles.isEmpty()) {
            ""
        } else Stream.of(*roles)
                .map { obj: String -> obj.trim { it <= ' ' } }
                .map { obj: String -> obj.toUpperCase() }
                .collect(Collectors.joining(","))
    }

    companion object {
        private val log = LoggerFactory.getLogger(AccountServiceImpl::class.java)
    }
}