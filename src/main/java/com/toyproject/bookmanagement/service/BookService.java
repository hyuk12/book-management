package com.toyproject.bookmanagement.service;

import com.toyproject.bookmanagement.api.dto.request.book.SearchBookReqDto;
import com.toyproject.bookmanagement.api.dto.response.book.CategoryRespDto;
import com.toyproject.bookmanagement.api.dto.response.book.GetBookRespDto;
import com.toyproject.bookmanagement.api.dto.response.book.RentalListRespDto;
import com.toyproject.bookmanagement.api.dto.response.book.SearchBookRespDto;
import com.toyproject.bookmanagement.domain.entity.Member;
import com.toyproject.bookmanagement.repository.BookRepository;
import com.toyproject.bookmanagement.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public int getLikeStatus(int bookId, int memberId) {

        Map<String, Object> map = new HashMap<>();

        map.put("bookId", bookId);
        map.put("memberId", memberId);

        return bookRepository.getLikeStatus(map);
    }

    public int setLike(int bookId, int memberId) {
        Map<String, Object> map = new HashMap<>();

        map.put("bookId", bookId);
        map.put("memberId", memberId);

        return bookRepository.setLike(map);
    }

    public int disLike(int bookId, int memberId) {
        Map<String, Object> map = new HashMap<>();

        map.put("bookId", bookId);
        map.put("memberId", memberId);

        return bookRepository.disLike(map);
    }

    public List<RentalListRespDto> getRentalListByBookId(int bookId) {
        List<RentalListRespDto> list = new ArrayList<>();

        bookRepository.getRentalList(bookId).forEach(rental -> {
            list.add(rental.toDto());
        });

        return list;
    }

    public int rentalBook(int bookListId, int memberId) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookListId", bookListId);
        map.put("memberId", memberId);

        return bookRepository.rentalBook(map);
    }

    public int returnBook(int bookListId, int memberId) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookListId", bookListId);
        map.put("memberId", memberId);

        return bookRepository.returnBook(map);
    }

    public Object bookListRegister(Integer bookId) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);

        return bookRepository.bookListRegister(map);
    }
}
