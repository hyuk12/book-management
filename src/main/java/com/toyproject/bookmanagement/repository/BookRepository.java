package com.toyproject.bookmanagement.repository;

import com.toyproject.bookmanagement.domain.entity.book.Book;
import com.toyproject.bookmanagement.domain.entity.book.Category;
import com.toyproject.bookmanagement.domain.entity.book.RentalList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookRepository {
    Book getBook(int bookId);
   List<Book> getBookList(Map<String, Object> map);
   public int getTotalCount(Map<String, Object> map);
   public List<Category> getCategoryList();
   int getLikeCount(int bookId);
   int getLikeStatus(Map<String, Object> map);
   int setLike(Map<String, Object> map);
   int disLike(Map<String, Object> map);
   List<RentalList> getRentalList(int bookId);
   int rentalBook(Map<String, Object> map);
   int returnBook(Map<String, Object> map);
}
