package com.library.library.business.book.title.mapper;

import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitleMapper {
    public BookTitleDTO mapBookTitleToBookTitleDTO(BookTitle bookTitle) {
        return BookTitleDTO.builder()
                .id(bookTitle.getId())
                .author(bookTitle.getAuthor())
                .publicityDate(bookTitle.getPublicityDate())
                .title(bookTitle.getTitle())
                .build();
    }

    public BookTitle mapBookTitleDTOToBookTitle(BookTitleDTO bookTitleDTO) {
        return BookTitle.builder()
                .id(bookTitleDTO.getId())
                .author(bookTitleDTO.getAuthor())
                .publicityDate(bookTitleDTO.getPublicityDate())
                .title(bookTitleDTO.getTitle())
                .build();
    }

    public List<BookTitle> mapTitleDTOListToTitleList(List<BookTitleDTO> bookTitleDTOList) {
        return bookTitleDTOList.stream().map(bookTitleDTO -> BookTitle.builder()
                    .id(bookTitleDTO.getId())
                    .author(bookTitleDTO.getAuthor())
                    .publicityDate(bookTitleDTO.getPublicityDate())
                    .title(bookTitleDTO.getTitle())
                    .build())
                .collect(Collectors.toList());
    }

    public List<BookTitleDTO> mapTitleListToTitleDTOList(List<BookTitle> bookTitleList) {
        return bookTitleList.stream().map(bookTitle -> BookTitleDTO.builder()
                    .id(bookTitle.getId())
                    .author(bookTitle.getAuthor())
                    .publicityDate(bookTitle.getPublicityDate())
                    .title(bookTitle.getTitle())
                    .build())
                .collect(Collectors.toList());
    }
}
