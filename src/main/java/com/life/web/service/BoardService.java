package com.life.web.service;

import com.life.web.common.Pagination;
import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.repository.BoardRepository;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public PagingResponse<BoardVo> boardList(SearchDto searchDto) throws Exception{
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = boardRepository.count(searchDto);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, searchDto);
        searchDto.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<BoardVo> list = boardRepository.boardList(searchDto);
        return new PagingResponse<>(list, pagination);
    }
}
