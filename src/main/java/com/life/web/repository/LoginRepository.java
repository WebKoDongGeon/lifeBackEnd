package com.life.web.repository;

import com.life.web.vo.LoginVo;
import org.springframework.stereotype.Repository;

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

    LoginVo login(LoginVo loginVo) throws Exception;
}
