package com.life.web.controller;

import com.life.web.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * packageName    : com.life.web.controller
 * fileName       : FileController
 * author         : kodg
 * date           : 2023/06/12
 * description    : 파일업로드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        kodg       최초 생성
 */

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String fileUpload(@RequestPart(required = false, name="image") MultipartFile file) throws Exception {

        fileService.saveFiles(file);

        return "";
    }
}
