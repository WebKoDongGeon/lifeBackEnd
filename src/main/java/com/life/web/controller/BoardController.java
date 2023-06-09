package com.life.web.controller;

import com.life.web.common.PagingResponse;
import com.life.web.dto.SearchDto;
import com.life.web.service.BoardService;
import com.life.web.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static java.lang.Double.isNaN;
import static java.lang.Integer.parseInt;

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

    private final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;


    @GetMapping("")
    public ResponseEntity<List<BoardVo>> boardList() throws Exception {
        try {
            List<BoardVo> list = boardService.boardList();
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("{boardNo}")
    public ResponseEntity<?> boardDetail(@PathVariable String boardNo) throws Exception {
        try {
            System.out.println("boardNo : "+boardNo);
            Integer.parseInt(boardNo);

            BoardVo boardVo = boardService.boardDetail(boardNo);
            return ResponseEntity.status(200).body(boardVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(204).build();
        }
    }

    /**
     * 생성.
     * */
    @PostMapping("")
    public ResponseEntity<?> createBoard(@RequestPart(required = false, name="image") MultipartFile image, @RequestPart("board") BoardVo board) throws Exception {
        try{
            boardService.createBoard(board, image);

            if(board.getBoardNo() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(board.getBoardNo());
            } else {
                return ResponseEntity.badRequest().body("Failed to create post");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create post");
        }

    }

    /**
     * 수정
     * */
    @PutMapping("")
    public ResponseEntity<?> updateBoard(@RequestPart(required = false, name="image") MultipartFile image, @RequestPart("board") BoardVo board) throws Exception {
        System.out.println("image = " + image + ", board = " + board);

        try{
            int result = boardService.updateBoard(board, image);

            if(result != 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("수정 완료");
            } else {
                return ResponseEntity.badRequest().body("수정 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create post");
        }

    }

    /**
     * 삭제
     * */
    @DeleteMapping("{boardNo}")
    public ResponseEntity<?> deleteBoard(@PathVariable String boardNo) throws Exception {
        try{
            int result = boardService.deleteBoard(boardNo);

            if(result != 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("삭제 완료");
            } else {
                return ResponseEntity.badRequest().body("삭제 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create post");
        }

    }


}
