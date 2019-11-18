package com.library.library.queries;

import com.library.library.business.book.borrowing.dao.BorrowingListDAO;
import com.library.library.business.book.borrowing.domain.BorrowingList;
import com.library.library.business.book.copy.dao.BookCopyDAO;
import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.reader.dao.ReaderDAO;
import com.library.library.business.reader.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueriesTestSuite {
    @Autowired
    private BookCopyDAO bookCopyDAO;

    @Autowired
    private BookTitleDAO bookTitleDAO;

    @Autowired
    private ReaderDAO readerDAO;

    @Autowired
    private BorrowingListDAO borrowingListDAO;

    @Test
    public void testFindByTitleQuery() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .title("Harry Potter")
                .author("JK ROWLING")
                .publicityDate(LocalDate.now())
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .title(bookTitle)
                .status("Wypożyczona")
                .build();

        BookCopy bookCopy1 = BookCopy.builder()
                .title(bookTitle)
                .status("W bibliotece")
                .build();

        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);
        BookCopy savedBookCopy1 = bookCopyDAO.save(bookCopy1);
        //When
        List<BookCopy> bookCopies = bookCopyDAO.findByBookTitleId(savedBookTitle.getId());
        //Then
        assertEquals(2, bookCopies.size());
        //CleanUp
        bookCopyDAO.delete(savedBookCopy);
        bookCopyDAO.delete(savedBookCopy1);
        bookTitleDAO.delete(savedBookTitle);
    }

    @Test
    public void testFindByCopyAndFindByReaderQuery() {
        //Given
        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.now())
                .build();

        BookTitle bookTitle = BookTitle.builder()
                .title("Harry Potter")
                .author("JK ROWLING")
                .publicityDate(LocalDate.now())
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .title(bookTitle)
                .status("Wypożyczona")
                .build();

        BorrowingList borrowingList = BorrowingList.builder()
                .copy(bookCopy)
                .reader(reader)
                .borrowingDate(LocalDateTime.now())
                .returningDate(LocalDateTime.now())
                .build();

        BorrowingList borrowingList1 = BorrowingList.builder()
                .copy(bookCopy)
                .reader(reader)
                .borrowingDate(LocalDateTime.now())
                .returningDate(LocalDateTime.now())
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedCopy = bookCopyDAO.save(bookCopy);
        BorrowingList savedList = borrowingListDAO.save(borrowingList);
        BorrowingList savedList2 = borrowingListDAO.save(borrowingList1);

        //When
        List<BorrowingList> readerBorrowings = borrowingListDAO.findByReaderId(savedReader.getId());
        List<BorrowingList> copiesBorrowings = borrowingListDAO.findByBookCopyId(savedCopy.getId());
        //Then
        assertEquals(2, readerBorrowings.size());
        assertEquals(2, copiesBorrowings.size());
        //CleanUp
        borrowingListDAO.delete(savedList);
        borrowingListDAO.delete(savedList2);
        bookCopyDAO.delete(savedCopy);
        bookTitleDAO.delete(savedTitle);
        readerDAO.delete(savedReader);
    }
}
