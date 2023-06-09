package com.life.web.dto;

import lombok.Builder;
import lombok.Getter;


/**
 * packageName    : com.life.web.dto
 * fileName       : FileRequest
 * author         : kodg
 * date           : 2023/06/09
 * description    : 파일정보 저장
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        kodg       최초 생성
 */

@Getter
public class FileRequest {

    private Long fileNo; // 파일 번호
    private String userId; //유저 아이디
    private String originalName;    // 원본 파일명
    private String saveName;        // 저장 파일명
    private long size;              // 파일 크기

    @Builder
    public FileRequest(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
