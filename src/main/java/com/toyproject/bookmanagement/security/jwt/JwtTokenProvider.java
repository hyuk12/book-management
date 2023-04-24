package com.toyproject.bookmanagement.security.jwt;

import com.toyproject.bookmanagement.api.dto.response.auth.JwtTokenRespDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public JwtTokenRespDto createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        long now = (new Date()).getTime();
        Date tokenExpiresDate = new Date(now + (1000 * 60 * 60 * 24)); // token 만료 시간 30분

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(tokenExpiresDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtTokenRespDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.", e);

        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.", e);

        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.", e);

        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.", e);

        } catch (SignatureException e) {
            log.error("validateToken error", e);

        } catch (Exception e) {
            log.error("validateToken error", e);

        }
        return false;
    }

    public String getToken(String token) {
        String type = "Bearer";
        if (StringUtils.hasText(token) && token.startsWith(type)){
            return token.substring(type.length() + 1);
        }
        return null;
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
