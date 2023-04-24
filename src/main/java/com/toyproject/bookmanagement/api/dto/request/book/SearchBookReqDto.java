package com.toyproject.bookmanagement.api.dto.request.book;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class SearchBookReqDto {
    private int page;
    private int categoryId;
    private String searchValue;


}
