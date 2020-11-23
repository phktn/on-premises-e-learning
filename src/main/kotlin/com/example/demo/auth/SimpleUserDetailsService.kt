package com.example.demo.auth

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimpleUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    /**
     * メールアドレスで検索したユーザーのuserエンティティをSimpleLoginUserクラスのインスタンスへ変換する
     *
     * @param email 検索するユーザーのメールアドレス
     * @return メールアドレスで検索できたユーザーのユーザー情報
     * @throws UsernameNotFoundException メールアドレスでユーザーが検索できなかった場合にスローする。
     */
    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        log.debug("loadUserByUsername(email):[{}]", email)
        return userRepository.findByEmail(email)
                .map { user: User? -> SimpleLoginUser(user!!) }
                .orElseThrow { UsernameNotFoundException("User not found by email:[$email]") }
    }

    companion object {
        private val log = LoggerFactory.getLogger(SimpleUserDetailsService::class.java)
    }
}