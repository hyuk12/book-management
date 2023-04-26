package com.toyproject.bookmanagement.domain.entity.book;

import com.toyproject.bookmanagement.api.dto.response.book.CategoryRespDto;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Category {
    private int categoryId;
    private String categoryName;

    public CategoryRespDto toDto() {
        return CategoryRespDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}
