package com.library.library.business.book.title.dao;

import com.library.library.business.book.title.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookTitleDAO extends CrudRepository<BookTitle, Long> {
    @Override
    List<BookTitle> findAll();

    Optional<BookTitle> findByTitle(String title);

    Optional<BookTitle> findByTitleAndAuthor(String title, String author);
}
