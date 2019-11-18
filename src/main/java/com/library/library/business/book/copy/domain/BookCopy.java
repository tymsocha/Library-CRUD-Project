package com.library.library.business.book.copy.domain;

import com.library.library.business.book.title.domain.BookTitle;
import lombok.*;

import javax.persistence.*;

@NamedQuery(name = "BookCopy.findByBookTitleId", query = "FROM book_copies WHERE title_id = :ID")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BookTitle.class)
    @JoinColumn(name = "titleId")
    private BookTitle title;

    @Column(name = "Status")
    private String status;
}
