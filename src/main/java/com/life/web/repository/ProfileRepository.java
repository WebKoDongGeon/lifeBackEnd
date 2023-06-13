package com.life.web.repository;

import com.life.web.vo.BoardVo;
import com.life.web.vo.LoginVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.life.web.repository
 * fileName       : ProfileRepository
 * author         : kodg
 * date           : 2023/06/13
 * description    : 프로필 테이블 맵핑
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        kodg       최초 생성
 */
@Repository
public interface ProfileRepository {

    List<BoardVo> getProfile(String userId);
}
