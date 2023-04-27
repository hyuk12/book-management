package com.toyproject.bookmanagement.api.controller;

import com.toyproject.bookmanagement.api.dto.request.book.SearchBookReqDto;
import com.toyproject.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/book/{bookId}/like/status")
    public ResponseEntity<?> getLikeStatus(@PathVariable int bookId, @RequestParam int memberId) {
        return ResponseEntity.ok(bookService.getLikeStatus(bookId, memberId));
    }

    @PostMapping("/book/{bookId}/like")
    public ResponseEntity<?> setLike(@PathVariable int bookId, @RequestBody Map<String, Integer> map) {
        return ResponseEntity.ok(bookService.setLike(bookId, map.get("memberId")));
    }

    @DeleteMapping("/book/{bookId}/like")
    public ResponseEntity<?> disLike(@PathVariable int bookId, @RequestParam("memberId") int memberId) {
        return ResponseEntity.ok(bookService.disLike(bookId, memberId));
    }

    @GetMapping("/book/{bookId}/rental/list")
    public ResponseEntity<?> getRentalList(@PathVariable int bookId) {
        return ResponseEntity.ok(bookService.getRentalListByBookId(bookId));
    }
}
