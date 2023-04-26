package com.toyproject.bookmanagement.service;

import com.toyproject.bookmanagement.api.dto.request.book.SearchBookReqDto;
import com.toyproject.bookmanagement.api.dto.response.book.CategoryRespDto;
import com.toyproject.bookmanagement.api.dto.response.book.GetBookRespDto;
import com.toyproject.bookmanagement.api.dto.response.book.SearchBookRespDto;
import com.toyproject.bookmanagement.domain.entity.Member;
import com.toyproject.bookmanagement.repository.BookRepository;
import com.toyproject.bookmanagement.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public GetBookRespDto getBook(int bookId){
        return bookRepository.getBook(bookId).toGetBookDto();
    }

    public  Map<String, Object> getBookList(SearchBookReqDto searchBookReqDto){
        List<SearchBookRespDto> list = new ArrayList<>();

        int index = (searchBookReqDto.getPage() - 1) * 20;
        Map<String, Object> map = new HashMap<>();
        map.put("index", index);
        map.put("categoryIds", searchBookReqDto.getCategoryIds());
        map.put("searchValue", searchBookReqDto.getSearchValue());

        bookRepository.getBookList(map).forEach(book -> {
            list.add(book.toDto());
        });

        int totalCount = bookRepository.getTotalCount(map);
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", totalCount);
        result.put("bookList", list);

        return result;

    }

    public List<CategoryRespDto> getCategoryList(){
        List<CategoryRespDto> list = new ArrayList<>();
        bookRepository.getCategoryList().forEach(category -> {
            list.add(category.toDto());
        });
        return list;
    }

    public int getLikeCount(int bookId) {
        return bookRepository.getLikeCount(bookId);
    }

    public int getLikeStatus(int bookId) {

        Map<String, Object> map = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("bookId", bookId);
        Member memberEntity = memberRepository.findMemberByEmail(email);
        map.put("memberId", memberEntity.getMemberId());

        return bookRepository.getLikeStatus(map);
    }
}
