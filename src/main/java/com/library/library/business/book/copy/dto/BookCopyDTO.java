package com.library.library.business.book.copy.dto;

import com.library.library.business.book.borrowing.dto.BorrowingListDTO;
import com.library.library.business.book.title.dto.BookTitleDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookCopyDTO {
    private Long id;

    private BookTitleDTO title;

    private String status;

    private List<BorrowingListDTO> borrowingList;
}
