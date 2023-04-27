package com.toyproject.bookmanagement.api.dto.response.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class RentalListRespDto {
    private int bookListId;
    private String bookName;
    private int memberId;
    private boolean rentalStatus;
}
