package com.library.library.business.book.title.controller;

import com.library.library.business.book.title.dto.BookTitleDTO;
import com.library.library.business.book.title.mapper.BookTitleMapper;
import com.library.library.business.book.title.service.BookTitleService;
import com.library.library.exceptions.BookTitleNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Titles")
@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("v1/library/book/titles")
public class TitlesController {
    @Autowired
    private BookTitleService service;

    @Autowired
    private BookTitleMapper mapper;

    @GetMapping(value = "getAll")
    @ApiOperation(value = "Get all book titles")
    public List<BookTitleDTO> getAll () {
        return mapper.mapTitleListToTitleDTOList(service.getAllBookTitles());
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get book title by id")
    public BookTitleDTO get(@PathVariable Long id) throws BookTitleNotFoundException {
        return mapper.mapBookTitleToBookTitleDTO(service.getBookTitle(id));
    }

    @PostMapping(value = "add")
    @ApiOperation("Add title to database")
    public BookTitleDTO add(@RequestBody BookTitleDTO dto) {
        return mapper.mapBookTitleToBookTitleDTO(service.addBookTitleToDatabase(mapper.mapBookTitleDTOToBookTitle(dto)));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "Update title")
    public BookTitleDTO update(@RequestBody BookTitleDTO dto) throws BookTitleNotFoundException {
        return mapper.mapBookTitleToBookTitleDTO(service.updateBookTitle(dto));
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete Book")
    public void delete(@PathVariable Long id) {
        service.deleteBookTitle(id);
    }
}
