package com.toyproject.bookmanagement.service;

import com.toyproject.bookmanagement.api.dto.request.auth.LoginReqDto;
import com.toyproject.bookmanagement.api.dto.request.auth.SignupReqDto;
import com.toyproject.bookmanagement.domain.entity.Authority;
import com.toyproject.bookmanagement.domain.entity.Member;
import com.toyproject.bookmanagement.exception.CustomException;
import com.toyproject.bookmanagement.exception.ErrorMap;
import com.toyproject.bookmanagement.repository.MemberRepository;
import com.toyproject.bookmanagement.security.jwt.JwtTokenProvider;
import com.toyproject.bookmanagement.api.dto.request.auth.JwtTokenRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public void duplicatedEmailCheck(String email) {
        if (memberRepository.findMemberByEmail(email) != null) {

            throw new CustomException("duplicated error",
                    ErrorMap.builder()
                    .put("email", "이미 존재하는 이메일입니다.")
                    .build());
        }
    }

    public void save(SignupReqDto signupReqDto) {
        Member memberEntity = signupReqDto.toEntity();
        memberRepository.save(memberEntity);


        memberRepository.saveAuthority(Authority.builder()
                                    .memberId(memberEntity.getMemberId())
                                    .roleId(1)
                                    .build());
    }

    public JwtTokenRespDto login(LoginReqDto loginReqDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());

        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.createToken(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findMemberByEmail(username);
        if (memberEntity == null) {
            throw new CustomException("Login failed",ErrorMap.builder().put("email", "존재하지 않는 사용자입니다.").put("password", "존재하지 않는 사용자입니다.").build());
        }
        return memberEntity.toPrincipal();
    }

    public boolean authenticated(String accessToken) {

        return jwtTokenProvider.validateToken(jwtTokenProvider.getToken(accessToken));
    }
}
