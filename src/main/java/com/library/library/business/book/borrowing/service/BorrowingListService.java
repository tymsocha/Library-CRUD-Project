package com.library.library.business.book.borrowing.service;

import com.google.common.base.Preconditions;
import com.library.library.business.book.borrowing.dao.BorrowingListDAO;
import com.library.library.business.book.borrowing.domain.BorrowingList;
import com.library.library.business.book.borrowing.dto.BorrowingListApi;
import com.library.library.exceptions.BorrowingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BorrowingListService {
    @Autowired
    private BorrowingListDAO dao;

    public BorrowingList addBorrowingListToDatabase(BorrowingList borrowingList) {
        log.info("Saving New BorrowingList");
        return dao.save(borrowingList);
    }

    public List<BorrowingList> getAllBorrowingLists() {
        return dao.findAll();
    }

    public BorrowingList getBorrowingList(Long borrowingListId) throws BorrowingNotFoundException {
        log.info("Looking for borrowingList");
        Preconditions.checkArgument(borrowingListId != null, "Id must not be null");
        return dao.findById(borrowingListId).orElseThrow(BorrowingNotFoundException::new);
    }

    public List<BorrowingList> getBorrowingsByCopyId(Long copyId) {
        log.info("Looking for BorrowingList");
        Preconditions.checkArgument(copyId != null, "Copy Id cannot be null");
        return dao.findByBookCopyId(copyId);
    }

    public List<BorrowingList> getBorrowingsByReaderId(Long readerId) {
        log.info("Looking for BorrowingList");
        Preconditions.checkArgument(readerId != null, "Reader Id cannot be null");
        return dao.findByReaderId(readerId);
    }

    public BorrowingList updateBorrowingList(BorrowingListApi api) throws BorrowingNotFoundException {
        log.info("Looking for borrowingList");
        BorrowingList borrowingListToUpdate = getBorrowingList(api.getId());
        log.info("Checking if borrowingList exists");
        Preconditions.checkArgument(borrowingListToUpdate != null, "Id must not be null");
        borrowingListToUpdate.setBorrowingDate(
                api.getBorrowingDate() == null ? borrowingListToUpdate.getBorrowingDate() : api.getBorrowingDate());
        borrowingListToUpdate.setReturningDate(
                api.getReturningDate() == null ? borrowingListToUpdate.getReturningDate() : api.getReturningDate());
        return borrowingListToUpdate;
    }

    public void deleteBorrowingList(Long borrowingListId) {
        log.info("Deleting BorrowingList");
        dao.deleteById(borrowingListId);
    }
}
