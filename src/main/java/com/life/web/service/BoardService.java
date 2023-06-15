package com.life.web.service;

import com.life.web.common.FileStore;
import com.life.web.common.Pagination;
import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.repository.BoardRepository;
import com.life.web.vo.BoardVo;
import com.life.web.vo.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.life.web.service
 * fileName       : BoardService
 * author         : kodg
 * date           : 2023/06/07
 * description    : 게시판 비즈니스 로직
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileStore fileStore;
//    private final ReturnFile returnFile;

    // 파일이 저장될 경로를 지정합니다.
    // Mac이나 Windows 환경에서 모두 동작하는 코드입니다.
    // 프로젝트의 루트 디렉토리에 'uploads'라는 디렉토리를 만들어 그곳에 파일을 저장합니다.
    private final Path root = Paths.get("uploads");

    public List<BoardVo> boardList() throws Exception {
        List<BoardVo> list = boardRepository.boardList();
        return list;
    }

    public BoardVo boardDetail(String boardNo) throws Exception {
        return boardRepository.boardDetail(boardNo);
    }


    public void createBoard(BoardVo boardVo, MultipartFile image) throws IOException {

        try {
            if(image != null) {
                FileVo fileVo = fileStore.storeFile(image);

                boardVo.setSaveImageName(fileVo.getSaveName());
                boardVo.setOriginalImageName(fileVo.getOriginalName());
            }

            boardRepository.createBoard(boardVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
