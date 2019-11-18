package com.library.library.business.book.copy.dao;

import com.library.library.business.book.copy.domain.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookCopyDAO extends CrudRepository<BookCopy, Long> {
    @Override
    List<BookCopy> findAll();

    @Query
    List<BookCopy> findByBookTitleId(@Param("ID") Long id);
}
