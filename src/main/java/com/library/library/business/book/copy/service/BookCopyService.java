package com.library.library.business.book.copy.service;

import com.google.common.base.Preconditions;
import com.library.library.business.book.copy.dao.BookCopyDAO;
import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.copy.dto.BookCopyApi;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.service.BookTitleService;
import com.library.library.exceptions.BookCopyNotFoundException;
import com.library.library.exceptions.BookTitleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookCopyService {
    @Autowired
    private BookCopyDAO dao;

    @Autowired
    private BookTitleService bookTitleService;

    public BookCopy addBookCopyToDatabase(BookCopy bookCopy) {
        log.info("Saving New BookCopy");
        return dao.save(bookCopy);
    }

    public List<BookCopy> getAllBookCopys() {
        return dao.findAll();
    }

    public BookCopy getBookCopy(Long bookCopyId) throws BookCopyNotFoundException {
        log.info("Looking for bookCopy");
        Preconditions.checkArgument(bookCopyId != null, "Id must not be null");
        return dao.findById(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
    }

    public List<BookCopy> getBookCopiesByTitleAndAuthor(BookCopyApi api) throws BookTitleNotFoundException {
        log.info("Looking for BookCopy");
        Preconditions.checkArgument(api.getTitle() != null && api.getAuthor() != null, "Title cannot be null");
        BookTitle bookTitle = bookTitleService.getBookTitleByTitleAndAuthor(api.getTitle(), api.getAuthor());
        return dao.findByBookTitleId(bookTitle.getId());
    }

    public BookCopy updateBookCopy(BookCopyApi api) throws BookCopyNotFoundException {
        log.info("Looking for bookCopy");
        BookCopy bookCopyToUpdate = getBookCopy(api.getId());
        log.info("Checking if bookCopy exists");
        Preconditions.checkArgument(bookCopyToUpdate != null, "Id must not be null");
        bookCopyToUpdate.setStatus(api.getStatus() == null ? bookCopyToUpdate.getStatus() : api.getStatus());
        return bookCopyToUpdate;
    }

    public void deleteBookCopy(Long bookCopyId) {
        log.info("Deleting BookCopy");
        dao.deleteById(bookCopyId);
    }
}
