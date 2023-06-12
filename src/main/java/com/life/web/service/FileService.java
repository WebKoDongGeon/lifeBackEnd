package com.life.web.service;

import com.life.web.dto.FileRequest;
import com.life.web.repository.FileRepository;
import com.life.web.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.life.web.service
 * fileName       : FileService
 * author         : kodg
 * date           : 2023/06/12
 * description    : 파일업로드 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        kodg       최초 생성
 */

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Path root = Paths.get("uploads");

    @Transactional
    public void saveFiles(MultipartFile file) throws Exception{
        //1. 파일 저장 경로 설정: 실제 서비스되는 위치(프로젝트 외부에 저장)
        //루트에 디렉토리가 없을시 생성.
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        String uploadPath = root.toString()+"/";

        //2.원본 파일 이름 알아오기
        String originalFileName = file.getOriginalFilename();

        //3. 파일 이름이 중복되지 않도록 파일 이름 변경: 서버에 저장할 이름
        //UUID 사용
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid.toString() + "_" + originalFileName;

        //4. 파일 생성
        File newFile = new File(uploadPath + savedFileName);

        //5.서버로 전송
        file.transferTo(newFile);
    }


}
