package com.life.web.service;

import com.life.web.common.JwtTokenProvider;
import com.life.web.repository.LoginRepository;
import com.life.web.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.life.web.service
 * fileName       : LoginService
 * author         : kodg
 * date           : 2023/06/01
 * description    : 로그인 시 기능구현.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        kodg       최초 생성
 */

@Service
@RequiredArgsConstructor //생성자 주입을 위한 Lombok라이브러리 어노테이션
public class LoginService {

    private final LoginRepository loginRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Map<String, Object> login(LoginVo loginVo) {
        Map<String, Object> userInfo = new HashMap<>();

        try {
            //회원이 입력한 userId로 일치하는 정보 찾기.
            LoginVo user = loginRepository.login(loginVo);

            System.out.println("user = " + user);
            //로그인 아이디요청한 아이디의 비밀번호가 입력한 비밀번호와 같으면.
            if(passwordEncoder.matches(loginVo.getUserPw(), user.getUserPw()) ) {
                //로그인 성공시 Jwt 토큰부여.
                String accessToken = jwtTokenProvider.createToken(user.getUserId(), Arrays.asList("USER"));
                String createRefreshToken = null;
                
                if(user.getRefreshToken() == null || !jwtTokenProvider.validateToken(user.getRefreshToken())) {
                    createRefreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());
                } else {
                    createRefreshToken = user.getRefreshToken();
                }

                //리프레시 토큰 DB저장.
                Map<String, Object> userUpdate = new HashMap<>();

                userUpdate.put("userNo", user.getUserNo());
                userUpdate.put("refreshToken", createRefreshToken);
                loginRepository.refreshTokenUserSave(userUpdate);


                LoginVo user1 = new LoginVo();
                user1.setUserId(user.getUserId());
                user1.setUserNo(user.getUserNo());

                userInfo.put("accessToken", accessToken);
                userInfo.put("refreshToken", createRefreshToken);
                userInfo.put("userInfo", user1);

            } else {
                //로그인 실패.
                userInfo.put("userInfo", null);
            }


            return userInfo;
        } catch (Exception e) {
            userInfo.put("fail", e);

            return userInfo;
        }

    }
}
