package com.library.library.business.book.copy.mapper;

import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.copy.dto.BookCopyDTO;
import com.library.library.business.book.title.mapper.BookTitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    @Autowired
    private BookTitleMapper mapper;

    public BookCopy mapCopyDTOToCopy(BookCopyDTO bookCopyDTO) {
        return BookCopy.builder()
                .id(bookCopyDTO.getId())
                .status(bookCopyDTO.getStatus())
                .title(mapper.mapBookTitleDTOToBookTitle(bookCopyDTO.getTitle()))
                .build();
    }

    public BookCopyDTO mapCopyToCopyDTO(BookCopy bookCopy) {
        return BookCopyDTO.builder()
                .id(bookCopy.getId())
                .status(bookCopy.getStatus())
                .title(mapper.mapBookTitleToBookTitleDTO(bookCopy.getTitle()))
                .build();
    }

    public List<BookCopyDTO> mapCopyListToCopyDTOList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream().map(bookCopy -> BookCopyDTO.builder()
                    .id(bookCopy.getId())
                    .status(bookCopy.getStatus())
                    .title(mapper.mapBookTitleToBookTitleDTO(bookCopy.getTitle()))
                    .build())
                .collect(Collectors.toList());
    }

    public List<BookCopy> mapCopyDTOListToCopyList(List<BookCopyDTO> bookCopyDTOList) {
        return bookCopyDTOList.stream().map(bookCopyDTO -> BookCopy.builder()
                .id(bookCopyDTO.getId())
                .status(bookCopyDTO.getStatus())
                .title(mapper.mapBookTitleDTOToBookTitle(bookCopyDTO.getTitle()))
                .build())
                .collect(Collectors.toList());
    }
}
