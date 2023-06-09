package com.life.web.repository;

import com.life.web.vo.FileVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.life.web.repository
 * fileName       : FileRepository
 * author         : kodg
 * date           : 2023/06/12
 * description    : 파일업로드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        kodg       최초 생성
 */

@Repository
public interface FileRepository {

    /***
     * 파일 정보 저장
     * @param fileVo - 파일 정보
     */

    void saveFile(FileVo fileVo);
}
