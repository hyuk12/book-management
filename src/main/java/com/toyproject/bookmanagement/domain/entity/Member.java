package com.toyproject.bookmanagement.domain.entity;

import com.toyproject.bookmanagement.security.PrincipalDetailUser;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Member {
    private int memberId;
    private String email;
    private String password;
    private String name;
    private String provider;

    private List<Authority> authorities;

    public PrincipalDetailUser toPrincipal() {


        return PrincipalDetailUser.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .authorities(authorities)
                .build();
    }
}
