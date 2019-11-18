package com.library.library.mappers;

import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.copy.dto.BookCopyDTO;
import com.library.library.business.book.copy.mapper.BookCopyMapper;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookCopyMapperTestSuite {
    @Autowired
    private BookCopyMapper mapper;

    @Test
    public void shouldMapCopyToDTO() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitle)
                .build();

        //When
        BookCopyDTO dto = mapper.mapCopyToCopyDTO(bookCopy);
        //Then
        assertEquals(1L, dto.getId(), 0.001);
        assertEquals("Borrowed", dto.getStatus());
        assertEquals(1L, dto.getTitle().getId(), 0.0001);
        assertEquals("Title", dto.getTitle().getTitle());
        assertEquals("Author", dto.getTitle().getAuthor());
        assertEquals(LocalDate.of(2018, 10, 15), dto.getTitle().getPublicityDate());
    }

    @Test
    public void shouldMapDTOToCopy() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopyDTO bookCopyDTO = BookCopyDTO.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitleDTO)
                .build();

        //When
        BookCopy copy = mapper.mapCopyDTOToCopy(bookCopyDTO);
        //Then
        assertEquals(1L, copy.getId(), 0.001);
        assertEquals("Borrowed", copy.getStatus());
        assertEquals(1L, copy.getTitle().getId(), 0.0001);
        assertEquals("Title", copy.getTitle().getTitle());
        assertEquals("Author", copy.getTitle().getAuthor());
        assertEquals(LocalDate.of(2018, 10, 15), copy.getTitle().getPublicityDate());
    }

    @Test
    public void shouldMapCopyListTODTOList() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitle)
                .build();
        BookCopy bookCopy1 = BookCopy.builder()
                .id(2L)
                .status("At library")
                .title(bookTitle)
                .build();
        BookCopy bookCopy2 = BookCopy.builder()
                .id(3L)
                .status("Borrowed")
                .title(bookTitle)
                .build();

        List<BookCopy> bookCopies = new ArrayList<>();
        bookCopies.add(bookCopy);
        bookCopies.add(bookCopy1);
        bookCopies.add(bookCopy2);
        //When
        List<BookCopyDTO> dtos = mapper.mapCopyListToCopyDTOList(bookCopies);
        //Then
        assertEquals(3, dtos.size());
    }

    @Test
    public void shouldMapDTOListTOCopyList() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopyDTO bookCopyDTO = BookCopyDTO.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitleDTO)
                .build();
        BookCopyDTO bookCopyDTO1 = BookCopyDTO.builder()
                .id(2L)
                .status("At library")
                .title(bookTitleDTO)
                .build();
        BookCopyDTO bookCopyDTO2 = BookCopyDTO.builder()
                .id(3L)
                .status("Borrowed")
                .title(bookTitleDTO)
                .build();

        List<BookCopyDTO> bookCopyDTOS = new ArrayList<>();
        bookCopyDTOS.add(bookCopyDTO);
        bookCopyDTOS.add(bookCopyDTO1);
        bookCopyDTOS.add(bookCopyDTO2);
        //When
        List<BookCopy> bookCopies = mapper.mapCopyDTOListToCopyList(bookCopyDTOS);
        //Then
        assertEquals(3, bookCopies.size());
    }
}
