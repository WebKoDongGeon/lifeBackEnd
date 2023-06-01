package com.life.web.controller;

import com.life.web.service.LoginService;
import com.life.web.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * packageName    : com.life.web.controller
 * fileName       : LoginController
 * author         : kodg
 * date           : 2023/06/01
 * description    : 로그인 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        kodg       최초 생성
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<LoginVo> login(@RequestBody LoginVo loginVo) {
        try {
            LoginVo login = loginService.login(loginVo);
            if(login != null) {

            }
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
