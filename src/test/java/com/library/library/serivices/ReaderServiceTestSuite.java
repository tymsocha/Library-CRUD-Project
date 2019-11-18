package com.library.library.serivices;

import com.library.library.business.reader.dao.ReaderDAO;
import com.library.library.business.reader.domain.Reader;
import com.library.library.business.reader.dto.ReaderApi;
import com.library.library.business.reader.service.ReaderService;
import com.library.library.exceptions.ReaderNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReaderServiceTestSuite {
    @Autowired
    private ReaderService service;

    @Autowired
    private ReaderDAO dao;

    @Test
    public void shouldSaveReader() {
        //Given
        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        //When
        Reader savedReader = service.addReaderToDatabase(reader);
        //Then
        assertEquals(1, dao.findAll().size());
        //CleanUp
        dao.delete(savedReader);
    }

    @Test
    public void shouldGetAllReaders() {
        //Given
        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader reader1 = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader reader2 = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader savedReader = service.addReaderToDatabase(reader);
        Reader savedReader1 = service.addReaderToDatabase(reader1);
        Reader savedReader2 = service.addReaderToDatabase(reader2);
        //When and Then
        assertEquals(3, service.getAllReaders().size());
        //CleanUp
        dao.delete(savedReader);
        dao.delete(savedReader1);
        dao.delete(savedReader2);
    }

    @Test
    public void shouldGetReader() throws ReaderNotFoundException {
        //Given
        Reader reader = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader reader1 = Reader.builder()
                .firstName("Mary")
                .lastName("Poppins")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader reader2 = Reader.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader savedReader = service.addReaderToDatabase(reader);
        Reader savedReader1 = service.addReaderToDatabase(reader1);
        Reader savedReader2 = service.addReaderToDatabase(reader2);
        //When
        Reader foundReader = service.getReader(savedReader1.getId());
        //Then
        assertEquals("Mary", foundReader.getFirstName());
        assertEquals("Poppins", foundReader.getLastName());
        //CleanUp
        dao.delete(savedReader);
        dao.delete(savedReader1);
        dao.delete(savedReader2);
    }

    @Test
    public void shouldGetReaderByName() throws ReaderNotFoundException {
        //Given
        Reader reader = Reader.builder()
                .firstName("Mary")
                .lastName("Poppins")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader savedReader = service.addReaderToDatabase(reader);
        //When
        Reader foundReader = service.getReaderByName("Mary", "Poppins");
        //Then
        assertNotNull(foundReader);
        //CleanUp
        dao.delete(savedReader);
    }

    @Test
    public void shouldUpdateReader() throws ReaderNotFoundException {
        //Given
        Reader reader = Reader.builder()
                .firstName("Mary")
                .lastName("Poppins")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader savedReader = service.addReaderToDatabase(reader);
        ReaderApi api = ReaderApi.builder()
                .id(savedReader.getId())
                .firstName("John")
                .lastName("Doe").build();
        //When
        Reader updatedReader = service.updateReader(api);
        //Then
        assertEquals("John", updatedReader.getFirstName());
        assertEquals("Doe", updatedReader.getLastName());
        //CleanUp
        dao.delete(updatedReader);
    }

    @Test
    public void shouldDeleteReader() {
        //Given
        Reader reader = Reader.builder()
                .firstName("Mary")
                .lastName("Poppins")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        Reader savedReader = service.addReaderToDatabase(reader);
        //When
        service.deleteReader(savedReader.getId());
        //Then
        assertEquals(0, dao.findAll().size());
    }
}
