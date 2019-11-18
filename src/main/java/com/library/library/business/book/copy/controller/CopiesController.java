package com.library.library.business.book.copy.controller;

import com.library.library.business.book.copy.dto.BookCopyApi;
import com.library.library.business.book.copy.dto.BookCopyDTO;
import com.library.library.business.book.copy.mapper.BookCopyMapper;
import com.library.library.business.book.copy.service.BookCopyService;
import com.library.library.exceptions.BookCopyNotFoundException;
import com.library.library.exceptions.BookTitleNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Copies")
@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("v1/library/copies")
public class CopiesController {
    @Autowired
    private BookCopyService service;
    
    @Autowired
    private BookCopyMapper mapper;

    @GetMapping(value = "getAll")
    @ApiOperation(value = "Get All rentings")
    public List<BookCopyDTO> getAllRentings() {
        return mapper.mapCopyListToCopyDTOList(service.getAllBookCopys());
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get By Id")
    public BookCopyDTO getById(@PathVariable Long id) throws BookCopyNotFoundException {
        return mapper.mapCopyToCopyDTO(service.getBookCopy(id));
    }

    @GetMapping(value = "getAll/title")
    @ApiOperation(value = "Get all by title and author")
    public List<BookCopyDTO> getAllByTitleAndAuthor(@RequestBody BookCopyApi api) throws BookTitleNotFoundException {
        return mapper.mapCopyListToCopyDTOList(service.getBookCopiesByTitleAndAuthor(api));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "Add copy")
    public BookCopyDTO addCopy(@RequestBody BookCopyDTO dto) {
        return mapper.mapCopyToCopyDTO(service.addBookCopyToDatabase(mapper.mapCopyDTOToCopy(dto)));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "Update copy")
    public BookCopyDTO updateCopy(@RequestBody BookCopyApi api) throws BookCopyNotFoundException {
        return mapper.mapCopyToCopyDTO(service.updateBookCopy(api));
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete copy")
    public void deleteCopy(@PathVariable Long id) {
        service.deleteBookCopy(id);
    }
}
