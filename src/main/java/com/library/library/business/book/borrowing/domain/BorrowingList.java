package com.library.library.business.book.borrowing.domain;

import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.reader.domain.Reader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
            name = "BorrowingList.findByBookCopyId",
            query = "FROM list_of_borrowings WHERE copy_id = :id"
        ),
        @NamedQuery(
            name = "BorrowingList.findByReaderId",
            query = "FROM list_of_borrowings WHERE reader_id = :id"
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "list_of_borrowings")
public class BorrowingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BookCopy.class)
    @JoinColumn(name = "copyId")
    private BookCopy copy;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Reader.class)
    @JoinColumn(name = "readerId")
    private Reader reader;

    @Column(name = "borrowed")
    private LocalDateTime borrowingDate;

    @Column(name = "returned")
    private LocalDateTime returningDate;
}
