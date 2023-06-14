package com.life.web.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    /**
     *
     * author         : kodg
     * date            : 2023/06/02
     * description    : Jwt토큰 생성.
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     *2023/06/02          kodg             최초 생성
     * @param userId
     * @param roles
     * @return
     */
    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles must not be null or empty");
        }

        /**
         * roles 사용자의 역할을 지정
         * 추후 사이트 규모에 따라 등급 차후지정 예정 (Enum)
         * */
        claims.put("USER", roles);
        claims.put("userId", userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims) //JWT 토큰을 생성하기 위한 빌더를 시작
                .setIssuedAt(now) //토큰 발행시간을 설정
                .setExpiration(validity) //토큰 만료시간 설정
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) //HS256(HMAC SHA-256) 알고리즘과 시크릿 키를 사용하여 토큰에 서명, 이 서명은 토큰의 무결성을 보장하는 데 사용됨.
                .compact(); //JWT 토큰을 문자열로 변환
    }

    /**
     *
     * author         : kodg
     * date            : 2023/06/02
     * description    : 토큰검증
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     *2023/06/02          kodg             최초 생성
     * @param token
     * @return
     */
    public String validateRefreshTokenAndGetUserId(String refreshToken) {
        try {
            // 토큰에서 Claims 추출
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // Claims에서 사용자 아이디를 추출
            String userId = claims.getSubject();
            return userId;
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid refresh token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * author         : kodg
     * date            : 2023/06/02
     * description    : 리프레시 토큰생성
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     *2023/06/02          kodg             최초 생성
     * @param userId
     * @return
     */
    public String createRefreshToken(String userId) {
        long validityInMilliseconds = 1000 * 60 * 60 * 24 * 7; // 7일
        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(userId);
        List<String> refresh = Arrays.asList("reFresh");
        claims.put("reFreshToken", refresh);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId) // JWT 페이로드에 사용자 아이디를 포함
                .setIssuedAt(now) // 토큰 발행 시간 설정
                .setExpiration(new Date(now.getTime() + validityInMilliseconds)) // 토큰 만료 시간 설정
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) // HMAC SHA-256을 사용하여 토큰에 서명
                .compact(); // JWT를 문자열로 변환
    }


    /**
     *
     * author         : kodg
     * date            : 2023/06/02
     * description    : 리프레시 토큰 인증
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     *2023/06/02          kodg             최초 생성
     * @param refreshToken
     * @return
     */
    public String refreshToken(String refreshToken) {
        // 먼저 리프레시 토큰이 유효한지 검사합니다.
        String userId = validateRefreshTokenAndGetUserId(refreshToken);

        if (userId != null) {
            // 리프레시 토큰이 유효하다면 새로운 액세스 토큰을 생성합니다.
            // 여기서는 사용자의 역할을 간단하게 'USER'로 가정하였습니다.
            return createToken(userId, Arrays.asList("USER"));
        } else {
            // 리프레시 토큰이 유효하지 않다면 오류 메시지를 반환하거나 예외를 던집니다.
            throw new IllegalArgumentException("Invalid refresh token");
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) // secretKey는 서버에서 보관하는 비밀키입니다.
                .build()
                .parseClaimsJws(token)
                .getBody();

        String userId = claims.getSubject();

        List<String> reFreshToken = (List<String>) claims.get("reFreshToken");
        List<String> roles;
        if(claims.get("reFreshToken") != null && reFreshToken.get(0).equals("reFresh") ) {
            roles = Arrays.asList("USER");
        } else {
            roles = null;
        }
        // 'roles'는 JWT 토큰에 포함된 권한 정보입니다.
        // 'roles'는 JWT 토큰에 포함된 권한 정보입니다.

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }
}

