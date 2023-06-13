package com.life.web.common;

import com.life.web.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * packageName    : com.life.web.common
 * fileName       : FileStore
 * author         : kodg
 * date           : 2023/06/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        kodg       최초 생성
 */
@Component
@RequiredArgsConstructor
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;
    private final ResourceLoader resourceLoader;


//    //루트에 디렉토리가 없을시 생성.
//        if (!Files.exists(root)) {
//        Files.createDirectories(root);
//    }
//    // 파일이 저장될 경로를 지정합니다.
//    // Mac이나 Windows 환경에서 모두 동작하는 코드입니다.
//    // 프로젝트의 루트 디렉토리에 'uploads'라는 디렉토리를 만들어 그곳에 파일을 저장합니다.
//    private final Path root = Paths.get("uploads");


    public String getFullPath(String fileName) throws IOException {
        /**
         * 위의 코드에서 ResourceLoader를 주입받아
         * getFullPath() 메소드에서 resourceLoader.getResource()를 사용하여
         * uploads 폴더의 절대 경로를 얻습니다.
         * 경로를 찾을 때 file:./ 접두어를 사용하여 현재 디렉토리에서 파일을 찾도록 설정합니다.
         */

        //추가적으로 경로에 디렉토리 없을때 생성하는 로직도 추후개발에 포함.
        Resource resource = resourceLoader.getResource("file:./" + fileDir + fileName);

        return resource.getFile().getAbsolutePath();
    }

    public FileVo storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return  null;
        }

        //레어코일.jpeg
        String originalFileName = multipartFile.getOriginalFilename();

        String storeFileName = createStoreFileName(originalFileName);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new FileVo(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
        //확장자명
        String ext = extracted(originalFileName);
        //서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        //저장할 파일명.
        return uuid + "." + ext;
    }

    private String extracted(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);


    }


}
