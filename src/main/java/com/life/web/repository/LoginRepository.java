package com.life.web.repository;

import com.life.web.vo.LoginVo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * packageName    : com.life.web.repository
 * fileName       : LoginRepository
 * author         : kodg
 * date           : 2023/06/01
 * description    : Login Mapper 연결 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        kodg       최초 생성
 */

@Repository
public interface LoginRepository {

    //로그인 정보(userId) 확인
    LoginVo login(LoginVo loginVo) throws Exception;

    //리프레시 토큰 발급후 해당 유저에게 저장.
    void refreshTokenUserSave(Map<String, Object> userInfo) throws Exception;

}
