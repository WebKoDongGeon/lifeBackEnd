package com.life.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

/**
 * packageName    : com.life.web.vo
 * fileName       : BoardVo
 * author         : kodg
 * date           : 2023/06/07
 * description    : 게시판 Vo
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */
@Getter
@Setter
@ToString
public class BoardVo {

    private int boardNo;
    private String company;
    private String title;
    private String content;
    private String skill;
    private String startProject;
    private String endProject;
    private String saveImageName;
    private String originalImageName;
    private String userId;
    private String regDt;
    private String modDt;
}
