package com.library.library.business.reader.dao;

import com.library.library.business.reader.domain.Reader;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.repository.CrudRepository;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

public interface ReaderDAO extends CrudRepository<Reader, Long> {
    @Override
    Optional<Reader> findById(Long aLong);

    @Override
    List<Reader> findAll();

    Optional<Reader> findByFirstNameAndLastName(String firstName, String lastName);
}
