package com.life.web.dto;

import com.life.web.common.Pagination;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.life.web.dto
 * fileName       : SearchDto
 * author         : kodg
 * date           : 2023/06/07
 * description    : 페이징 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */

@Getter
@Setter
public class SearchDto {

    private int page; // 현재 페이지 번호.
    private int recordSize; // 페이지당 출력할 데이터 개수
    private int pageSize; // 화면 하단에 출력할 페이지 사이즈
    private String keyword; // 검색 키워드
    private String searchType; // 검색 유형
    private Pagination pagination;

    //객체가 생성되는 시점에 현재 페이지 번호는 1로, 페이지당 출력할 데이터 개수와 하단에 출력할 페이지 개수를 10으로 초기화합니다
    public SearchDto () {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }
}
