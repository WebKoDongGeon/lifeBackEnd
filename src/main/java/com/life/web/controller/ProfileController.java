package com.life.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * packageName    : com.life.web.controller
 * fileName       : ProfileController
 * author         : kodg
 * date           : 2023/06/09
 * description    : 프로필 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        kodg       최초 생성
 */

@RestController
public class ProfileController {


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 클라이언트가 업로드한 파일의 원래 이름을 가져옵니다.
        String fileName = file.getOriginalFilename();

        // 파일을 저장할 기본 경로를 지정합니다.
        String fileBasePath = "/images/";

        // 저장할 파일의 전체 경로를 File 객체로 만듭니다.
        File destinationFile = new File(fileBasePath + fileName);

        // 업로드한 파일을 목적지 파일로 복사합니다. 이 과정에서 파일이 파일 시스템에 저장됩니다.
        file.transferTo(destinationFile);

        // 파일이 저장된 경로를 문자열로 반환합니다.
        return fileBasePath + fileName;
    }
}







