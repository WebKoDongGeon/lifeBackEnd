package com.life.web.repository;

import com.life.web.vo.JoinVo;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.life.web.repository
 * fileName       : JoinRepository
 * author         : kodg
 * date           : 2023/05/30
 * description    : Join Mapper 연결 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/30        kodg       최초 생성
 */
@Repository
public interface JoinRepository {

    JoinVo join(JoinVo joinVo) throws Exception;
}
