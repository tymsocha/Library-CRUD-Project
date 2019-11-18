package com.library.library.business.book.borrowing.controller;

import com.library.library.business.book.borrowing.dto.BorrowingListApi;
import com.library.library.business.book.borrowing.dto.BorrowingListDTO;
import com.library.library.business.book.borrowing.mapper.BorrowingListMapper;
import com.library.library.business.book.borrowing.service.BorrowingListService;
import com.library.library.exceptions.BorrowingNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Rentings")
@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("v1/library/rentings")
public class RentingController {
    @Autowired
    private BorrowingListService service;

    @Autowired
    private BorrowingListMapper mapper;

    @GetMapping(value = "getAll")
    @ApiOperation(value = "Get All rentings")
    public List<BorrowingListDTO> getAllRentings() {
        return mapper.mapBorrowingsToDTOs(service.getAllBorrowingLists());
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get By Id")
    public BorrowingListDTO getById(@PathVariable Long id) throws BorrowingNotFoundException {
        return mapper.mapBorrowingListToBorrowingListDTO(service.getBorrowingList(id));
    }

    @PostMapping(value = "rent")
    @ApiOperation(value = "Rent a Book")
    public BorrowingListDTO rentABook(@RequestBody BorrowingListDTO dto) {
        return mapper.mapBorrowingListToBorrowingListDTO(
                service.addBorrowingListToDatabase(mapper.mapBorrowingListDTOToBorrowingList(dto)));
    }

    @GetMapping(value = "getAll/reader/{readerId}")
    @ApiOperation(value = "Get all reader rentings")
    public List<BorrowingListDTO> getAllReaderRentings(@PathVariable Long readerId) {
        return mapper.mapBorrowingsToDTOs(service.getBorrowingsByReaderId(readerId));
    }

    @GetMapping(value = "getAll/copy/{copyId}")
    @ApiOperation(value = "Get all reader rentings")
    public List<BorrowingListDTO> getAllCopiesRented(@PathVariable Long copyId) {
        return mapper.mapBorrowingsToDTOs(service.getBorrowingsByCopyId(copyId));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "Update renting")
    public BorrowingListDTO updateRenting(@RequestBody BorrowingListApi api) throws BorrowingNotFoundException {
        return mapper.mapBorrowingListToBorrowingListDTO(service.updateBorrowingList(api));
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete Renting")
    public void deleteRenting(@PathVariable Long id) {
        service.deleteBorrowingList(id);
    }
}
