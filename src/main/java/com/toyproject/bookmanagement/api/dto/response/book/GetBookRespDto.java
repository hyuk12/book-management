package com.toyproject.bookmanagement.api.dto.response.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class GetBookRespDto {
    private int bookId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private String categoryName;
    private String coverImgUrl;
}
