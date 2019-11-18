package com.library.library.serivices;

import com.library.library.business.book.borrowing.dao.BorrowingListDAO;
import com.library.library.business.book.borrowing.domain.BorrowingList;
import com.library.library.business.book.borrowing.dto.BorrowingListApi;
import com.library.library.business.book.borrowing.dto.BorrowingListDTO;
import com.library.library.business.book.borrowing.service.BorrowingListService;
import com.library.library.business.book.copy.dao.BookCopyDAO;
import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.title.dao.BookTitleDAO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.reader.dao.ReaderDAO;
import com.library.library.business.reader.domain.Reader;
import com.library.library.exceptions.BorrowingNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BorrowingListServiceTestSuite {
    @Autowired
    private BorrowingListService service;
    
    @Autowired
    private BorrowingListDAO dao;

    @Autowired
    private BookTitleDAO bookTitleDAO;

    @Autowired
    private BookCopyDAO bookCopyDAO;

    @Autowired
    private ReaderDAO readerDAO;


    @Test
    public void shouldSaveList() {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList borrowingList = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))

                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);
        //When
        BorrowingList savedList = service.addBorrowingListToDatabase(borrowingList);
        //Then
        assertEquals(1, dao.findAll().size());
        //CleanUp
        dao.delete(savedList);
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);

        System.out.println("" + 5 + 6);
        System.out.println(5 + "" + 6);
        System.out.println(5 + 6 + "");
        System.out.println(5 + 6);

        String str1 = "str1";
        String str2 = "str2";
        System.out.println(str1.concat(str2));
        System.out.println(str1);
    }

    @Test
    public void shouldGetAllLists() {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList borrowingList = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        BorrowingList borrowingList1 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        BorrowingList borrowingList2 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);

        BorrowingList savedList = service.addBorrowingListToDatabase(borrowingList);
        BorrowingList savedList1 = service.addBorrowingListToDatabase(borrowingList1);
        BorrowingList savedList2 = service.addBorrowingListToDatabase(borrowingList2);
        //When and Then
        assertEquals(3, service.getAllBorrowingLists().size());
        //CleanUp
        dao.delete(savedList);
        dao.delete(savedList1);
        dao.delete(savedList2);
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);
    }

    @Test
    public void shouldGetList() throws BorrowingNotFoundException {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        BorrowingList borrowingList1 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 1, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,2,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        BorrowingList borrowingList2 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);
        BorrowingList savedList = service.addBorrowingListToDatabase(list);
        BorrowingList savedList1 = service.addBorrowingListToDatabase(borrowingList1);
        BorrowingList savedList2 = service.addBorrowingListToDatabase(borrowingList2);
        //When
        BorrowingList foundList = service.getBorrowingList(savedList1.getId());
        //Then
        assertEquals(LocalDateTime.of(2018, 1, 15, 15, 15), foundList.getBorrowingDate());
        assertEquals(LocalDateTime.of(2018,2,10,15,15), foundList.getReturningDate());
        //CleanUp
        dao.delete(savedList);
        dao.delete(savedList1);
        dao.delete(savedList2);
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);
    }

    @Test
    public void shouldGetListByCopyIdAndByReaderId() {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        BorrowingList borrowingList1 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 1, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,2,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);

        BorrowingList savedList = service.addBorrowingListToDatabase(list);
        BorrowingList savedList1 = service.addBorrowingListToDatabase(borrowingList1);
        //When
        List<BorrowingList> borrowingsByCopyId = service.getBorrowingsByCopyId(savedBookCopy.getId());
        List<BorrowingList> borrowingsByReaderId = service.getBorrowingsByReaderId(savedReader.getId());
        //Then
        assertNotNull(borrowingsByCopyId);
        assertEquals(2, borrowingsByCopyId.size());
        assertNotNull(borrowingsByReaderId);
        assertEquals(2, borrowingsByReaderId.size());
        //CleanUp
        dao.delete(savedList);
        dao.delete(savedList1);
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);
    }

    @Test
    public void shouldUpdateList() throws BorrowingNotFoundException {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);
        BorrowingList savedList = service.addBorrowingListToDatabase(list);

        BorrowingListApi api = BorrowingListApi.builder()
                .id(savedList.getId())
                .borrowingDate(LocalDateTime.of(2018, 3, 3, 18, 6))
                .returningDate(LocalDateTime.of(2018,12,3,18,6))
                .build();

        //When
        BorrowingList updatedList = service.updateBorrowingList(api);
        //Then
        assertEquals(LocalDateTime.of(2018, 3, 3, 18, 6), updatedList.getBorrowingDate());
        assertEquals(LocalDateTime.of(2018,12,3,18,6), updatedList.getReturningDate());
        //CleanUp
        dao.delete(updatedList);
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);
    }

    @Test
    public void shouldDeleteList() {
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

        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2018, 10, 15, 15, 15))
                .returningDate(LocalDateTime.of(2018,11,10,15,15))
                .copy(bookCopy)
                .reader(reader)
                .build();

        Reader savedReader = readerDAO.save(reader);
        BookTitle savedBookTitle = bookTitleDAO.save(bookTitle);
        BookCopy savedBookCopy = bookCopyDAO.save(bookCopy);

        BorrowingList savedList = service.addBorrowingListToDatabase(list);
        //When
        service.deleteBorrowingList(savedList.getId());
        //Then
        assertEquals(0, service.getAllBorrowingLists().size());
        bookCopyDAO.delete(savedBookCopy);
        bookTitleDAO.delete(savedBookTitle);
        readerDAO.delete(savedReader);
    }
}
