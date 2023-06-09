package com.life.web.controller;

import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.service.BoardService;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<PagingResponse<BoardVo>> boardList(@RequestParam(required = false) SearchDto searchDto) throws Exception {

        try {

            PagingResponse<BoardVo> list = boardService.boardList(searchDto);

            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("")

    public ResponseEntity<BoardVo> createBoard(@RequestPart("image") MultipartFile image, @RequestPart("board") BoardVo board) throws Exception {

        boardService.createBoard(board, image);
//        return new ResponseEntity<>(board, HttpStatus.CREATED);
        return null;
    }


}
