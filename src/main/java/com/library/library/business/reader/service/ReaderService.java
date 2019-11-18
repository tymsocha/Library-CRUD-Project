package com.library.library.business.reader.service;

import com.google.common.base.Preconditions;
import com.library.library.business.reader.dao.ReaderDAO;
import com.library.library.business.reader.domain.Reader;
import com.library.library.business.reader.dto.ReaderApi;
import com.library.library.exceptions.ReaderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReaderService {
    @Autowired
    private ReaderDAO dao;

    public Reader addReaderToDatabase(Reader reader) {
        log.info("Saving New Reader");
        return dao.save(reader);
    }

    public List<Reader> getAllReaders() {
        return dao.findAll();
    }

    public Reader getReader(Long readerId) throws ReaderNotFoundException {
        log.info("Looking for reader");
        Preconditions.checkArgument(readerId != null, "Id must not be null");
        return dao.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader getReaderByName(String firstName, String lastName) throws ReaderNotFoundException {
        log.info("Looking for Reader");

        Preconditions.checkArgument(firstName != null && lastName != null, "Name cannot be null");

        return dao.findByFirstNameAndLastName(firstName, lastName).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader updateReader(ReaderApi api) throws ReaderNotFoundException {
        log.info("Looking for reader");
        Reader readerToUpdate = getReader(api.getId());
        log.info("Checking if reader exists");

        readerToUpdate.setFirstName(api.getFirstName() == null ? readerToUpdate.getFirstName() : api.getFirstName());
        readerToUpdate.setLastName(api.getLastName() == null ? readerToUpdate.getLastName() : api.getLastName());
        readerToUpdate.setDateOfAccountCreation(LocalDateTime.now());
        return readerToUpdate;
    }

    public void deleteReader(Long readerId) {
        log.info("Deleting Reader");
        dao.deleteById(readerId);
    }
}
