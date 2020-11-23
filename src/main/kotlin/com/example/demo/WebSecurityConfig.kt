package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    // アカウント登録時のパスワードエンコードで利用するためDI管理する。
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // @formatter:off
        web
                .debug(false)
                .ignoring()
                .antMatchers("/js/**", "/css/**")
        // @formatter:on
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http {
            authorizeRequests {
                authorize("/", permitAll)
                authorize("/signup", permitAll)
                authorize("/members/user/**", hasAuthority("ROLE_USER"))
                authorize("/images/user/**", hasAuthority("ROLE_USER"))
                authorize("/members/admin/**", hasAuthority("ROLE_ADMIN"))
                authorize("/images/admin/**", hasAuthority("ROLE_ADMIN"))
                authorize(anyRequest, authenticated)
            }
            formLogin {
                defaultSuccessUrl("/", false)
            }
            logout {
                invalidateHttpSession = true
                deleteCookies("JSESSIONID")
                logoutSuccessUrl = "/"
            }
        }
        // for /h2-console
        // http.csrf().disable();
        // http.headers().frameOptions().disable();
    }
}