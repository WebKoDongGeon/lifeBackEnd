package com.life.web.service;

import com.life.web.repository.JoinRepository;
import com.life.web.vo.JoinVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.life.web.service
 * fileName       : JoinService
 * author         : kodg
 * date           : 2023/05/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/30        kodg       최초 생성
 */
@Service
@RequiredArgsConstructor //생성자 주입을 위한 Lombok라이브러리 어노테이션
public class JoinService {

    private final JoinRepository joinRepository;

    public void join(JoinVo joinVo) throws Exception {
        joinRepository.join(joinVo);
    }
}
