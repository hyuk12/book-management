package com.toyproject.bookmanagement.repository;

import com.toyproject.bookmanagement.domain.entity.Authority;
import com.toyproject.bookmanagement.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    Member findMemberByEmail(String email);
    void save(Member member);
    void saveAuthority(Authority authority);

}
