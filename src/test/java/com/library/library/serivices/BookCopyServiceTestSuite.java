package com.library.library.serivices;

import com.library.library.business.book.copy.dao.BookCopyDAO;
import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.copy.dto.BookCopyApi;
import com.library.library.business.book.copy.service.BookCopyService;
import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.service.BookTitleService;
import com.library.library.exceptions.BookCopyNotFoundException;
import com.library.library.exceptions.BookTitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookCopyServiceTestSuite {
    @Autowired
    private BookCopyService service;
    
    @Autowired
    private BookCopyDAO dao;

    @Autowired
    private BookTitleService bookTitleService;

    @Autowired
    private BookTitleDAO bookTitleDAO;

    @Test
    public void shouldSaveCopy() {
        //Given
        BookCopy bookCopy = BookCopy.builder()
                .status("Borrowed")
                .build();
        //When
        BookCopy savedCopy = service.addBookCopyToDatabase(bookCopy);
        //Then
        assertEquals(1, dao.findAll().size());
        //CleanUp
        dao.delete(savedCopy);
    }

    @Test
    public void shouldGetAllCopies() {
        //Given
        BookCopy copy = BookCopy.builder()
                .status("Borrowed")
                .build();
        BookCopy bookCopy1 = BookCopy.builder()
                .status("Borrowed")
                .build();
        BookCopy bookCopy2 = BookCopy.builder()
                .status("Borrowed")
                .build();
        BookCopy savedCopy = service.addBookCopyToDatabase(copy);
        BookCopy savedCopy1 = service.addBookCopyToDatabase(bookCopy1);
        BookCopy savedCopy2 = service.addBookCopyToDatabase(bookCopy2);
        //When and Then
        assertEquals(3, service.getAllBookCopys().size());
        //CleanUp
        dao.delete(savedCopy);
        dao.delete(savedCopy1);
        dao.delete(savedCopy2);
    }

    @Test
    public void shouldGetCopy() throws BookCopyNotFoundException {
        //Given
        BookCopy copy = BookCopy.builder()
                .status("Available")
                .build();
        BookCopy bookCopy1 = BookCopy.builder()
                .status("Borrowed")
                .build();
        BookCopy bookCopy2 = BookCopy.builder()
                .status("Available")
                .build();
        BookCopy savedCopy = service.addBookCopyToDatabase(copy);
        BookCopy savedCopy1 = service.addBookCopyToDatabase(bookCopy1);
        BookCopy savedCopy2 = service.addBookCopyToDatabase(bookCopy2);
        //When
        BookCopy foundCopy = service.getBookCopy(savedCopy1.getId());
        //Then
        assertEquals("Borrowed", foundCopy.getStatus());
        //CleanUp
        dao.delete(savedCopy);
        dao.delete(savedCopy1);
        dao.delete(savedCopy2);
    }

    @Test
    public void shouldGetCopiesByTitleAndAuthor() throws BookTitleNotFoundException {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .title(bookTitle)
                .status("Available")
                .build();

        BookCopy bookCopy1 = BookCopy.builder()
                .title(bookTitle)
                .status("Available")
                .build();

        BookTitle savedTitle = bookTitleService.addBookTitleToDatabase(bookTitle);
        BookCopy savedCopy = service.addBookCopyToDatabase(bookCopy);
        BookCopy savedCopy1 = service.addBookCopyToDatabase(bookCopy1);

        BookCopyApi api = BookCopyApi.builder()
                .id(savedCopy.getId())
                .author("Author")
                .title("Title")
                .status("Borrowed")
                .build();
        //When
        List<BookCopy> foundCopies = service.getBookCopiesByTitleAndAuthor(api);
        //Then
        assertEquals(2, foundCopies.size());
        //CleanUp
        dao.delete(savedCopy);
        dao.delete(savedCopy1);
        bookTitleDAO.delete(savedTitle);
    }

    @Test
    public void shouldUpdateCopy() throws BookCopyNotFoundException {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookTitle savedTitle = bookTitleService.addBookTitleToDatabase(bookTitle);

        BookCopy bookCopy = BookCopy.builder()
                .title(bookTitle)
                .status("Available")
                .build();

        BookCopy savedCopy = service.addBookCopyToDatabase(bookCopy);

        BookCopyApi api = BookCopyApi.builder()
                .id(savedCopy.getId())
                .author("Author")
                .title("Title")
                .status("Borrowed")
                .build();
        //When
        BookCopy updatedCopy = service.updateBookCopy(api);
        //Then
        assertEquals("Borrowed", updatedCopy.getStatus());
        //CleanUp
        dao.delete(updatedCopy);
        bookTitleDAO.delete(savedTitle);
    }

    @Test
    public void shouldDeleteCopy() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .author("Author")
                .title("Title")
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookTitle savedTitle = bookTitleService.addBookTitleToDatabase(bookTitle);

        BookCopy bookCopy = BookCopy.builder()
                .title(bookTitle)
                .status("Available")
                .build();
        BookCopy savedCopy = service.addBookCopyToDatabase(bookCopy);
        //When
        service.deleteBookCopy(savedCopy.getId());
        //Then
        assertEquals(0, service.getAllBookCopys().size());
        //CleanUp
        bookTitleDAO.delete(savedTitle);
    }

}
