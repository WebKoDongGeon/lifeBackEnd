package com.life.web.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.life.web.vo
 * fileName       : FileVo
 * author         : kodg
 * date           : 2023/06/12
 * description    : 업로드파일 Vo
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        kodg       최초 생성
 */

@Getter
public class FileVo {

    private Long fileNo;                // 파일 번호 (PK)
    private Long postId;            // 게시글 번호 (FK)
    private String userId;          // 유저ID
    private String originalName;    // 원본 파일명
    private String saveName;        // 저장 파일명
    private long size;              // 파일 크기

    @Builder
    public FileVo(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
