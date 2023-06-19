package com.life.web.repository;

import com.life.web.dto.SearchDto;
import com.life.web.vo.BoardVo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName    : com.life.web.repository
 * fileName       : BoardRepository
 * author         : kodg
 * date           : 2023/06/07
 * description    : 게시판 Mapper 연결 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */

@Repository
public interface BoardRepository {

    List<BoardVo> boardList() throws Exception;

    BoardVo boardDetail(String boardNo);

    //게시글 수 카운팅
    int count(SearchDto searchDto);


    void createBoard (BoardVo boardVo);

    int updateBoard (BoardVo boardVo);

}
