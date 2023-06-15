package com.life.web.service;

import com.life.web.repository.FileRepository;
import com.life.web.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    public void saveFile(FileVo fileVo) throws Exception{
        fileRepository.saveFile(fileVo);
    }


}
