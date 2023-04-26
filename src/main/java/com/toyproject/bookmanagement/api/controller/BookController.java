package com.toyproject.bookmanagement.api.controller;

import com.toyproject.bookmanagement.api.dto.request.book.SearchBookReqDto;
import com.toyproject.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable int bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBookList(SearchBookReqDto searchBookReqDto) {
        return ResponseEntity.ok(bookService.getBookList(searchBookReqDto));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategoryList() {
        return ResponseEntity.ok(bookService.getCategoryList());
    }

    @GetMapping("/book/{bookId}/like")
    public ResponseEntity<?> getLikeCount(@PathVariable int bookId) {
        return ResponseEntity.ok(bookService.getLikeCount(bookId));
    }
}
