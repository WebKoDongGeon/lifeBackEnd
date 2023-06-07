package com.life.web.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.life.web.common
 * fileName       : PagingResponse
 * author         : kodg
 * date           : 2023/06/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        kodg       최초 생성
 */
@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }


}
