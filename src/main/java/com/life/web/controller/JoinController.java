package com.life.web.controller;

import com.life.web.SecurityConfig;
import com.life.web.service.JoinService;
import com.life.web.vo.JoinVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.life.web.controller
 * fileName       : JoinController
 * author         : kodg
 * date           : 2023/05/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/30        kodg       최초 생성
 */
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    private final SecurityConfig securityConfig;


    @PostMapping("")
    public ResponseEntity<Void> join(@RequestBody JoinVo joinVo) throws Exception {

        try {
            //회원Vo
            JoinVo vo1 = new JoinVo();

            vo1.setGender(joinVo.getGender());
            vo1.setUserId(joinVo.getUserId());
            vo1.setEmail(joinVo.getEmail());
            vo1.setUserPw(securityConfig.passwordEncoder().encode(joinVo.getUserPw()));

            joinService.join(vo1);
        } catch(Exception e) {
            System.out.println(e);
        }

        return ResponseEntity.status(200).build();
    }

}
