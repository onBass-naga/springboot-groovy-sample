package com.example.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping(path = "loginForm")
    String loginForm() {
        return "login/index"
    }
}
