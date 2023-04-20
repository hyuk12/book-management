package com.toyproject.bookmanagement.domain.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Authority {
    private int authorityId;
    private int memberId;
    private int roleId;

    private Role role;
}
