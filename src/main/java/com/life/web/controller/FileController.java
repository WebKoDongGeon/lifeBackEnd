package com.life.web.controller;

import com.life.web.common.FileStore;
import com.life.web.service.FileService;
import com.life.web.vo.BoardVo;
import com.life.web.vo.FileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;


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

@Controller
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileStore fileStore;

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestPart(required = false, name="image") MultipartFile file
            , @RequestPart("board") BoardVo board
            , @RequestPart("postId") long postId
    ) throws IOException {
        try {
            if(file != null) {
                FileVo uploadFile = fileStore.storeFile(file);


                uploadFile.setUserId(board.getUserId());
                uploadFile.setPostId(postId);
                uploadFile.setRegDt(board.getRegDt());
                //이미지파일이 업로드되었을때 DB에 정보저장.
                fileService.saveFile(uploadFile);
                return ResponseEntity.status(HttpStatus.OK).body("이미지 저장에 성공하였습니다.");
            } else {

                return ResponseEntity.status(HttpStatus.OK).body("이미지 파일이 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("이미지 파일이 없습니다.");
        }
    }

//    @GetMapping("/files/{id}")
//    public String items(@PathVariable Long id) {
//
//        //넘겨진 아이디로 아이템을 찾는다.
//    }

    @ResponseBody
    @GetMapping("/iamges/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws IOException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
