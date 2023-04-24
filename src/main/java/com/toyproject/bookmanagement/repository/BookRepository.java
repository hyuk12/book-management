package com.toyproject.bookmanagement.repository;

import com.toyproject.bookmanagement.domain.entity.book.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookRepository {
   List<Book> getBookList(Map<String, Object> map);
}
