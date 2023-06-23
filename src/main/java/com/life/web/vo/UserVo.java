package com.life.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.life.web.vo
 * fileName       : UserVo
 * author         : kodg
 * date           : 2023/06/23
 * description    : 유저정보.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        kodg       최초 생성
 */

@Getter
@Setter
public class UserVo {

    private String userNo;
    private String userId;
    private String userName;
    private String email;
    private Character gender;

}
