package com.life.web.service;

import com.life.web.repository.MyPageRepository;
import com.life.web.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.life.web.service
 * fileName       : MyPageService
 * author         : kodg
 * date           : 2023/06/23
 * description    : 내정보 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        kodg       최초 생성
 */

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageRepository myPageRepository;

    public UserVo myPage(String userNo) {
        return myPageRepository.myPage(userNo);
    }
}
