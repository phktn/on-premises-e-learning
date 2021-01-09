package com.example.demo.auth

import com.example.demo.entity.User
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * ユーザー認証情報
 */
class SimpleLoginUser
/**
 * データベースより検索したuserエンティティよりSpring Securityで使用するユーザー認証情報の
 * インスタンスを生成する
 *
 * @param user userエンティティ
 */(  // DBより検索したuserエンティティ
    // 認証・認可以外でアプリケーションから利用されるのでフィールドに定義
    val user: User
) : org.springframework.security.core.userdetails.User(
    user.email, user.password, user.enable, true, true,
    true, convertGrantedAuthorities(user.roles)
) {

    companion object {
        private val log = LoggerFactory.getLogger(SimpleLoginUser::class.java)

        /**
         * カンマ区切りのロールをSimpleGrantedAuthorityのコレクションへ変換する
         *
         * @param roles カンマ区切りのロール
         * @return SimpleGrantedAuthorityのコレクション
         */
        fun convertGrantedAuthorities(roles: String?): Set<GrantedAuthority> {
            return if (roles.isNullOrEmpty()) {
                emptySet()
            } else Stream.of(*roles.split(",").toTypedArray())
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toSet())
        }
    }
}
