package com.library.library.business.book.title.service;

import com.google.common.base.Preconditions;
import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import com.library.library.exceptions.BookTitleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookTitleService {
    @Autowired
    private BookTitleDAO dao;

    public BookTitle addBookTitleToDatabase(BookTitle bookTitle) {
        log.info("Saving New BookTitle");
        return dao.save(bookTitle);
    }

    public List<BookTitle> getAllBookTitles() {
        return dao.findAll();
    }

    public BookTitle getBookTitle(Long bookTitleId) throws BookTitleNotFoundException {
        log.info("Looking for bookTitle");
        Preconditions.checkArgument(bookTitleId != null, "Id must not be null");
        return dao.findById(bookTitleId).orElseThrow(BookTitleNotFoundException::new);
    }

    public BookTitle getBookTitleByTitleAndAuthor(String title, String author) throws BookTitleNotFoundException {
        log.info("Looking for BookTitle");

        Preconditions.checkArgument(title != null, "Title cannot be null");

        return dao.findByTitleAndAuthor(title, author).orElseThrow(BookTitleNotFoundException::new);
    }

    public BookTitle updateBookTitle(BookTitleDTO dto) throws BookTitleNotFoundException {
        log.info("Looking for bookTitle");
        BookTitle bookTitleToUpdate = getBookTitle(dto.getId());
        log.info("Checking if bookTitle exists");
        Preconditions.checkArgument(bookTitleToUpdate != null, "Id must not be null");
        bookTitleToUpdate.setTitle(dto.getTitle() == null ? bookTitleToUpdate.getTitle(): dto.getTitle());
        bookTitleToUpdate.setAuthor(dto.getAuthor() == null ? bookTitleToUpdate.getAuthor() : dto.getAuthor());
        bookTitleToUpdate.setPublicityDate(
                dto.getPublicityDate() == null ? bookTitleToUpdate.getPublicityDate() : dto.getPublicityDate());
        return bookTitleToUpdate;
    }

    public void deleteBookTitle(Long bookTitleId) {
        log.info("Deleting BookTitle");
        dao.deleteById(bookTitleId);
    }
}
