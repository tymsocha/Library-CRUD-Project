package com.library.library.serivices;

import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import com.library.library.business.book.title.service.BookTitleService;
import com.library.library.exceptions.BookTitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookTitleServiceTestSuite {
    @Autowired
    private BookTitleService service;

    @Autowired
    private BookTitleDAO dao;

    @Test
    public void shouldSaveTitle() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        //When
        BookTitle savedTitle = service.addBookTitleToDatabase(bookTitle);
        //Then
        assertEquals(1, dao.findAll().size());
        //CleanUp
        dao.delete(savedTitle);
    }

    @Test
    public void shouldGetAllTitles() {
        //Given
        BookTitle title = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle1 = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle2 = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle savedTitle = service.addBookTitleToDatabase(title);
        BookTitle savedTitle1 = service.addBookTitleToDatabase(bookTitle1);
        BookTitle savedTitle2 = service.addBookTitleToDatabase(bookTitle2);
        //When and Then
        assertEquals(3, service.getAllBookTitles().size());
        //CleanUp
        dao.delete(savedTitle);
        dao.delete(savedTitle1);
        dao.delete(savedTitle2);
    }

    @Test
    public void shouldGetTitle() throws BookTitleNotFoundException {
        //Given
        BookTitle title = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle1 = BookTitle.builder()
                .author("John Doe")
                .title("New Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle bookTitle2 = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle savedTitle = service.addBookTitleToDatabase(title);
        BookTitle savedTitle1 = service.addBookTitleToDatabase(bookTitle1);
        BookTitle savedTitle2 = service.addBookTitleToDatabase(bookTitle2);
        //When
        BookTitle foundTitle = service.getBookTitle(savedTitle1.getId());
        //Then
        assertEquals("John Doe", foundTitle.getAuthor());
        assertEquals("New Title", foundTitle.getTitle());
        //CleanUp
        dao.delete(savedTitle);
        dao.delete(savedTitle1);
        dao.delete(savedTitle2);
    }

    @Test
    public void shouldGetTitleBYTitleAndAuthor() throws BookTitleNotFoundException {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("John Doe")
                .title("New Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle savedTitle = service.addBookTitleToDatabase(bookTitle);
        //When
        BookTitle foundTitle = service.getBookTitleByTitleAndAuthor("New Title", "John Doe");
        //Then
        assertNotNull(foundTitle);
        assertEquals("John Doe", foundTitle.getAuthor());
        assertEquals("New Title", foundTitle.getTitle());
        //CleanUp
        dao.delete(savedTitle);
    }

    @Test
    public void shouldUpdateTitle() throws BookTitleNotFoundException {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("John Doe")
                .title("New Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle savedTitle = service.addBookTitleToDatabase(bookTitle);
        BookTitleDTO dto = BookTitleDTO.builder()
                .id(savedTitle.getId())
                .author("Janet Doe")
                .title("Latest Title")
                .build();
        //When
        BookTitle updatedTitle = service.updateBookTitle(dto);
        //Then
        assertEquals("Janet Doe", updatedTitle.getAuthor());
        assertEquals("Latest Title", updatedTitle.getTitle());
        //CleanUp
        dao.delete(updatedTitle);
    }

    @Test
    public void shouldDeleteTitle() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("John Doe")
                .title("New Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();
        BookTitle savedTitle = service.addBookTitleToDatabase(bookTitle);
        //When
        service.deleteBookTitle(savedTitle.getId());
        //Then
        assertEquals(0, service.getAllBookTitles().size());
    }
}
