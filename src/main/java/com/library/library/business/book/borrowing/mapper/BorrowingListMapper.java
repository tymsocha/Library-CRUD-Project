package com.library.library.business.book.borrowing.mapper;

import com.library.library.business.book.borrowing.domain.BorrowingList;
import com.library.library.business.book.borrowing.dto.BorrowingListDTO;
import com.library.library.business.book.copy.mapper.BookCopyMapper;
import com.library.library.business.reader.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowingListMapper {
    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private ReaderMapper readerMapper;

    public BorrowingListDTO mapBorrowingListToBorrowingListDTO(BorrowingList borrowingList) {
        return BorrowingListDTO.builder()
                .id(borrowingList.getId())
                .borrowingDate(borrowingList.getBorrowingDate())
                .copy(bookCopyMapper.mapCopyToCopyDTO(borrowingList.getCopy()))
                .reader(readerMapper.mapReaderToReaderDTO(borrowingList.getReader()))
                .returningDate(borrowingList.getReturningDate())
                .build();
    }

    public BorrowingList mapBorrowingListDTOToBorrowingList(BorrowingListDTO borrowingListDTO) {
        return BorrowingList.builder()
                .id(borrowingListDTO.getId())
                .borrowingDate(borrowingListDTO.getBorrowingDate())
                .copy(bookCopyMapper.mapCopyDTOToCopy(borrowingListDTO.getCopy()))
                .reader(readerMapper.mapReaderDTOToReader(borrowingListDTO.getReader()))
                .returningDate(borrowingListDTO.getReturningDate())
                .build();
    }

    public List<BorrowingListDTO> mapBorrowingsToDTOs(List<BorrowingList> borrowingLists) {
        return borrowingLists.stream().map(borrowingList -> BorrowingListDTO.builder()
                    .id(borrowingList.getId())
                    .borrowingDate(borrowingList.getBorrowingDate())
                    .copy(bookCopyMapper.mapCopyToCopyDTO(borrowingList.getCopy()))
                    .reader(readerMapper.mapReaderToReaderDTO(borrowingList.getReader()))
                    .returningDate(borrowingList.getReturningDate())
                    .build())
                .collect(Collectors.toList());
    }

    public List<BorrowingList> mapDTOsToLists(List<BorrowingListDTO> borrowingListDTOS) {
        return borrowingListDTOS.stream().map(borrowingListDTO -> BorrowingList.builder()
                    .id(borrowingListDTO.getId())
                    .borrowingDate(borrowingListDTO.getBorrowingDate())
                    .copy(bookCopyMapper.mapCopyDTOToCopy(borrowingListDTO.getCopy()))
                    .reader(readerMapper.mapReaderDTOToReader(borrowingListDTO.getReader()))
                    .returningDate(borrowingListDTO.getReturningDate())
                    .build())
                .collect(Collectors.toList());
    }


}
