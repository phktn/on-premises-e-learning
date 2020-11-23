package com.example.demo.controller

import com.example.demo.auth.SimpleLoginUser
import com.example.demo.entity.User
import com.example.demo.service.ContentsService
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping(value = ["members"])
class ContentsController(private val contentsService: ContentsService) {
    @GetMapping(value = ["/"])
    fun any(principal: Principal): String {
        val authentication = principal as Authentication
        val loginUser = authentication.principal as SimpleLoginUser
        log.info("#any id:{}, name:{}", loginUser.user.id, loginUser.user.name)
        contentsService.doService()
        return "members/index"
    }

    @GetMapping(value = ["user"])
    fun user(@AuthenticationPrincipal loginUser: SimpleLoginUser): String {
        log.info("#user id:{}, name:{}", loginUser.user.id, loginUser.user.name)
        return "members/user/index"
    }

    @GetMapping(value = ["admin"])
    fun admin(@AuthenticationPrincipal(expression = "user") user: User): String {
        log.info("#admin id:{}, name:{}", user.id, user.name)
        return "members/admin/index"
    }

    companion object {
        private val log = LoggerFactory.getLogger(ContentsController::class.java)
    }
}