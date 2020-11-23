package com.example.demo.service.impl

import com.example.demo.auth.SimpleLoginUser
import com.example.demo.service.ContentsService
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ContentsServiceImpl : ContentsService {
    override fun doService() {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication
        val loginUser = authentication.principal as SimpleLoginUser
        log.info("#doService id:{}, name:{}", loginUser.user.id, loginUser.user.name)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ContentsServiceImpl::class.java)
    }
}