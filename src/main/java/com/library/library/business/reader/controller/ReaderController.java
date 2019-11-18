package com.library.library.business.reader.controller;

import com.library.library.business.reader.dto.ReaderApi;
import com.library.library.business.reader.dto.ReaderDTO;
import com.library.library.business.reader.mapper.ReaderMapper;
import com.library.library.business.reader.service.ReaderService;
import com.library.library.exceptions.ReaderNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Readers")
@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("v1/library/readers")
public class ReaderController {
    @Autowired
    private ReaderService service;

    @Autowired
    private ReaderMapper mapper;

    @GetMapping(value = "getAll")
    @ApiOperation(value = "Get all readers")
    public List<ReaderDTO> getAll() {
        return mapper.mapReaderListToReaderDTOList(service.getAllReaders());
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get reader by id")
    public ReaderDTO getById(@PathVariable Long id) throws ReaderNotFoundException {
        return mapper.mapReaderToReaderDTO(service.getReader(id));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "Add new Reader")
    public ReaderDTO addReader(@RequestBody ReaderDTO dto) {
        return mapper.mapReaderToReaderDTO(service.addReaderToDatabase(mapper.mapReaderDTOToReader(dto)));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "Update Reader")
    public ReaderDTO updateReader(@RequestBody ReaderApi api) throws ReaderNotFoundException {
        return mapper.mapReaderToReaderDTO(service.updateReader(api));
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete Reader")
    public void deleteReader(@PathVariable Long id) {
        service.deleteReader(id);
    }
}
