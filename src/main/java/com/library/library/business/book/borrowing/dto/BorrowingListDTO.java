package com.library.library.business.book.borrowing.dto;

import com.library.library.business.book.copy.dto.BookCopyDTO;
import com.library.library.business.reader.dto.ReaderDTO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BorrowingListDTO {

    private Long id;

    private BookCopyDTO copy;

    private ReaderDTO reader;

    private LocalDateTime borrowingDate;

    private LocalDateTime returningDate;
}
