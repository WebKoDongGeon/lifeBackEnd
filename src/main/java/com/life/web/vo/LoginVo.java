package com.life.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.life.web.vo
 * fileName       : LoginVo
 * author         : kodg
 * date           : 2023/06/01
 * description    : 로그인 VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        kodg       최초 생성
 */

@Getter
@Setter
@ToString
public class LoginVo {

    private Integer userNo;
    private String userId;
    private String userName;
    private String userPw;
    private String refreshToken;


}
