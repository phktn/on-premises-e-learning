package com.example.demo.controller

import com.example.demo.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class IndexController(private val accountService: AccountService) {
    /**
     * トップページ
     *
     * @param signupForm サインアップフォームデータ
     * @param model      モデル（ユーザーリスト）
     * @return index
     */
    @GetMapping(value = ["/"])
    fun index(@ModelAttribute("signup") signupForm: SignupForm, model: Model): String {
        val userList = accountService.findAll()
        model.addAttribute("users", userList)
        return "index"
    }

    /**
     * アカウント登録処理
     *
     * @param signupForm         サインアップフォームデータ
     * @param redirectAttributes リダイレクト先へメッセージを送るため
     * @return index (redirect)
     */
    @PostMapping(value = ["signup"])
    fun signup(@ModelAttribute("signup") signupForm: SignupForm, redirectAttributes: RedirectAttributes): String {
        // TODO 暫定的に2つのロールを付与する
        val roles = arrayOf("ROLE_USER", "ROLE_ADMIN")
        accountService.register(signupForm.name, signupForm.email, signupForm.password, roles)
        redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録が完了しました")
        return "redirect:/"
    }

    companion object {
        private val log = LoggerFactory.getLogger(IndexController::class.java)
    }
}