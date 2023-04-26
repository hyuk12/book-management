package com.toyproject.bookmanagement.api.dto.response.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class CategoryRespDto {
    private int categoryId;
    private String categoryName;
}
