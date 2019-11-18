package com.library.library.mappers;

import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import com.library.library.business.book.title.mapper.BookTitleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookTitleMapperTestSuite {
    @Autowired
    private BookTitleMapper mapper;

    @Test
    public void shouldMapTitleToDTO() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        //When
        BookTitleDTO dto = mapper.mapBookTitleToBookTitleDTO(bookTitle);
        //Then
        assertEquals(1L, dto.getId(), 0.001);
        assertEquals("Title", dto.getTitle());
        assertEquals("Author", dto.getAuthor());
        assertEquals(LocalDate.of(2018, 10, 15), dto.getPublicityDate());
    }

    @Test
    public void shouldMapDTOToTitle() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        //When
        BookTitle title = mapper.mapBookTitleDTOToBookTitle(bookTitleDTO);
        //Then
        assertEquals(1L, title.getId(), 0.001);
        assertEquals("Title", title.getTitle());
        assertEquals("Author", title.getAuthor());
        assertEquals(LocalDate.of(2018, 10, 15), title.getPublicityDate());
    }

    @Test
    public void shouldMapTitleListTODTOList() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitleDTO bookTitleDTO1 = BookTitleDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitleDTO bookTitleDTO2 = BookTitleDTO.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        List<BookTitleDTO> bookTitleDTOS = new ArrayList<>();
        bookTitleDTOS.add(bookTitleDTO);
        bookTitleDTOS.add(bookTitleDTO1);
        bookTitleDTOS.add(bookTitleDTO2);
        //When
        List<BookTitle> titles = mapper.mapTitleDTOListToTitleList(bookTitleDTOS);
        //Then
        assertEquals(3, titles.size());
    }

    @Test
    public void shouldMapDTOListTOTitleList() {
        //Given
        BookTitle title = BookTitle.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle1 = BookTitle.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle2 = BookTitle.builder()
                .id(1L)
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        List<BookTitle> bookTitles = new ArrayList<>();
        bookTitles.add(title);
        bookTitles.add(bookTitle1);
        bookTitles.add(bookTitle2);
        //When
        List<BookTitleDTO> titles = mapper.mapTitleListToTitleDTOList(bookTitles);
        //Then
        assertEquals(3, titles.size());
    }
}
