package com.life.web.controller;

import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.service.BoardService;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.life.web.controller
 * fileName       : BoardController
 * author         : kodg
 * date           : 2023/06/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("")
    public ResponseEntity<PagingResponse<BoardVo>> boardList(@RequestBody SearchDto searchDto) throws Exception {

        try {
            System.out.println("다치고 망가져도나~");
            PagingResponse<BoardVo> list = boardService.boardList(searchDto);

            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }
}
