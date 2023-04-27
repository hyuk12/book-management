package com.toyproject.bookmanagement.domain.entity.book;

import com.toyproject.bookmanagement.api.dto.response.book.RentalListRespDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RentalList {
    private int bookListId;
    private String bookName;
    private int memberId;

    public RentalListRespDto toDto() {
        return RentalListRespDto.builder()
                .bookListId(bookListId)
                .bookName(bookName)
                .memberId(memberId)
                .rentalStatus(memberId == 0)
                .build();
    }
}
