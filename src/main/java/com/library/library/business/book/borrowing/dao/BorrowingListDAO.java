package com.library.library.business.book.borrowing.dao;

import com.library.library.business.book.borrowing.domain.BorrowingList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingListDAO extends CrudRepository<BorrowingList, Long> {
    @Query
    List<BorrowingList> findByBookCopyId(@Param("id") Long bookCopyId);

    @Query
    List<BorrowingList> findByReaderId(@Param("id") Long readerId);

    @Override
    List<BorrowingList> findAll();
}
