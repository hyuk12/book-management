package com.toyproject.bookmanagement.api.dto.response.book;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRespDto {
    private int bookId;
    private String bookName;
    private int authorId;
    private int publisherId;
    private int categoryId;
    private String coverImgUrl;

    private String authorName;
    private String publisherName;
    private String categoryName;


}
