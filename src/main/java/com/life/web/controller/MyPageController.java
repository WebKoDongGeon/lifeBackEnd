package com.life.web.controller;

import com.life.web.service.MyPageService;
import com.life.web.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.life.web.controller
 * fileName       : MyPageController
 * author         : kodg
 * date           : 2023/06/23
 * description    : 내정보 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        kodg       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("{userNo}")
    public ResponseEntity<?> myPage(@PathVariable String userNo) {

        UserVo userVo = myPageService.myPage(userNo);

        System.out.println("userVo = " + userVo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
