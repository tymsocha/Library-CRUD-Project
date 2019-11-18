package com.library.library.business.book.title.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookTitleDTO {

    private Long id;

    private String title;

    private String author;

    private LocalDate publicityDate;
}
