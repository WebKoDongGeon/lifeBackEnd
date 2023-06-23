package com.life.web.repository;

import com.life.web.vo.UserVo;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.life.web.repository
 * fileName       : MyPageRepository
 * author         : kodg
 * date           : 2023/06/23
 * description    : 유저 테이블 맵핑
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        kodg       최초 생성
 */

@Repository
public interface MyPageRepository {

    UserVo myPage(String userNo);
}
