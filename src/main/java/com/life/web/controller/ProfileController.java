package com.life.web.controller;

import com.life.web.service.ProfileService;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.life.web.controller
 * fileName       : ProfileController
 * author         : kodg
 * date           : 2023/06/13
 * description    : 프로필 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        kodg       최초 생성
 */

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable String userId) {

        List<BoardVo> profile = profileService.getProfile(userId);
        System.out.println(profile);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }
}
