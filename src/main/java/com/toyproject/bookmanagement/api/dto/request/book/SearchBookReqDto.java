package com.toyproject.bookmanagement.api.dto.request.book;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter @Setter
public class SearchBookReqDto {
    private int page;
    private String searchValue;
    private List<Integer> categoryIds;


}
