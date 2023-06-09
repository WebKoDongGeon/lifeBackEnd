package com.life.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.life.web.vo
 * fileName       : JoinVo
 * author         : kodg
 * date           : 2023/05/30
 * description    : 회원가입 VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/30        kodg       최초 생성
 */
@Getter
@Setter
@ToString
public class JoinVo {
    private Integer userNo;
    private String userId;
    private String userName;
    private String userPw;
    private String email;
    private Character gender;
    private String refreshToken;
}
