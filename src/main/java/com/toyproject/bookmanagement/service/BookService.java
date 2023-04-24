package com.toyproject.bookmanagement.service;

import com.toyproject.bookmanagement.api.dto.request.book.SearchBookReqDto;
import com.toyproject.bookmanagement.api.dto.response.book.SearchBookRespDto;
import com.toyproject.bookmanagement.domain.entity.book.Book;
import com.toyproject.bookmanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<SearchBookRespDto> getBookList(SearchBookReqDto searchBookReqDto){

        int index = (searchBookReqDto.getPage() - 1) * 20;

        Map<String, Object> map = new HashMap<>();
        map.put("index", index);

        List<Book> bookList = bookRepository.getBookList(map);

        List<SearchBookRespDto> searchBookRespDtoList = new ArrayList<>();
        for(Book book : bookList){
            searchBookRespDtoList.add(book.toDto());
        }

        return searchBookRespDtoList;

    }
}
