package com.library.library.mappers;

import com.library.library.business.book.borrowing.domain.BorrowingList;
import com.library.library.business.book.borrowing.dto.BorrowingListDTO;
import com.library.library.business.book.borrowing.mapper.BorrowingListMapper;
import com.library.library.business.book.copy.domain.BookCopy;
import com.library.library.business.book.copy.dto.BookCopyDTO;
import com.library.library.business.book.title.domain.BookTitle;
import com.library.library.business.book.title.dto.BookTitleDTO;
import com.library.library.business.reader.domain.Reader;
import com.library.library.business.reader.dto.ReaderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BorrowingListMapperTestSuite {
    @Autowired
    private BorrowingListMapper mapper;

    @Test
    public void shouldMapListToDTO() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitle)
                .build();

        Reader reader = Reader.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(reader)
                .copy(bookCopy)
                .build();

        //When
        BorrowingListDTO dto = mapper.mapBorrowingListToBorrowingListDTO(list);
        //Then
        assertEquals(1L, dto.getId(), 0.001);
        assertEquals(LocalDateTime.of(2019, 7, 15, 15, 15), dto.getBorrowingDate());
        assertEquals(LocalDateTime.of(2019, 7, 28, 15, 15), dto.getReturningDate());
        assertNotNull(dto.getReader());
        assertNotNull(dto.getCopy());
    }

    @Test
    public void shouldMapDTOToList() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopyDTO bookCopyDTO = BookCopyDTO.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitleDTO)
                .build();

        ReaderDTO readerDTO = ReaderDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingListDTO borrowingListDTO = BorrowingListDTO.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(readerDTO)
                .copy(bookCopyDTO)
                .build();

        //When
        BorrowingList list = mapper.mapBorrowingListDTOToBorrowingList(borrowingListDTO);
        //Then
        assertEquals(1L, list.getId(), 0.001);
        assertEquals(LocalDateTime.of(2019, 7, 15, 15, 15), list.getBorrowingDate());
        assertEquals(LocalDateTime.of(2019, 7, 28, 15, 15), list.getReturningDate());
        assertNotNull(list.getReader());
        assertNotNull(list.getCopy());
    }

    @Test
    public void shouldMapListsToDTOList() {
        //Given
        BookTitle bookTitle = BookTitle.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitle)
                .build();

        Reader reader = Reader.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingList list = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(reader)
                .copy(bookCopy)
                .build();

        BorrowingList list1 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(reader)
                .copy(bookCopy)
                .build();

        BorrowingList list2 = BorrowingList.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(reader)
                .copy(bookCopy)
                .build();

        List<BorrowingList> borrowingLists = new ArrayList<>();
        borrowingLists.add(list);
        borrowingLists.add(list1);
        borrowingLists.add(list2);
        //When
        List<BorrowingListDTO> dtos = mapper.mapBorrowingsToDTOs(borrowingLists);
        //Then
        assertEquals(3, dtos.size());
    }

    @Test
    public void shouldMapDTOListToLists() {
        //Given
        BookTitleDTO bookTitleDTO = BookTitleDTO.builder()
                .title("Title")
                .author("Author")
                .id(1L)
                .publicityDate(LocalDate.of(2018, 10, 15))
                .build();

        BookCopyDTO bookCopyDTO = BookCopyDTO.builder()
                .id(1L)
                .status("Borrowed")
                .title(bookTitleDTO)
                .build();

        ReaderDTO readerDTO = ReaderDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        BorrowingListDTO listDTO = BorrowingListDTO.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(readerDTO)
                .copy(bookCopyDTO)
                .build();

        BorrowingListDTO listDTO1 = BorrowingListDTO.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(readerDTO)
                .copy(bookCopyDTO)
                .build();

        BorrowingListDTO listDTO2 = BorrowingListDTO.builder()
                .borrowingDate(LocalDateTime.of(2019, 7, 15, 15, 15))
                .returningDate(LocalDateTime.of(2019, 7, 28, 15, 15))
                .id(1L)
                .reader(readerDTO)
                .copy(bookCopyDTO)
                .build();

        List<BorrowingListDTO> borrowingListDTOS = new ArrayList<>();
        borrowingListDTOS.add(listDTO);
        borrowingListDTOS.add(listDTO1);
        borrowingListDTOS.add(listDTO2);
        //When
        List<BorrowingList> list = mapper.mapDTOsToLists(borrowingListDTOS);
        //Then
        assertEquals(3, list.size());
    }
}
