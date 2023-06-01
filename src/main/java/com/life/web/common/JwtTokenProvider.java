package com.life.web.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.life.web.common
 * fileName       : JwtTokenProvider
 * author         : kodg
 * date           : 2023/06/01
 * description    : Jwt 토큰구현
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        kodg       최초 생성
 */

@Service
public class JwtTokenProvider {

    @Value("${jwtSecret}")
    private String secretKey;
    private final long validityInMilliseconds = 365 * 24 * 60 * 60 * 1000L; // 1year

    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        /**
         * roles 사용자의 역할을 지정
         * 추후 사이트 규모에 따라 등급 차후지정 예정 (Enum)
         * */
        //claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims) //JWT 토큰을 생성하기 위한 빌더를 시작
                .setIssuedAt(now) //토큰 발행시간을 설정
                .setExpiration(validity) //토큰 만료시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) //HS256(HMAC SHA-256) 알고리즘과 시크릿 키를 사용하여 토큰에 서명, 이 서명은 토큰의 무결성을 보장하는 데 사용됨.
                .compact(); //JWT 토큰을 문자열로 변환
    }

}

