package com.library.library.mappers;

import com.library.library.business.reader.domain.Reader;
import com.library.library.business.reader.dto.ReaderDTO;
import com.library.library.business.reader.mapper.ReaderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReaderMapperTestSuite {
    @Autowired
    private ReaderMapper mapper;

    @Test
    public void shouldMapReaderToReaderDTO() {
        //Given
        Reader reader = Reader.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        //When
        ReaderDTO dto = mapper.mapReaderToReaderDTO(reader);
        //Then
        assertEquals(1L, dto.getId(), 0.001);
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals(LocalDateTime.of(2018, 1, 2, 15, 15), dto.getDateOfAccountCreation());
    }

    @Test
    public void shouldMapReaderDTOToReader() {
        //Given
        ReaderDTO readerDTO = ReaderDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();
        //When
        Reader reader = mapper.mapReaderDTOToReader(readerDTO);
        //Then
        assertEquals(1L, reader.getId(), 0.001);
        assertEquals("John", reader.getFirstName());
        assertEquals("Doe", reader.getLastName());
        assertEquals(LocalDateTime.of(2018, 1, 2, 15, 15), reader.getDateOfAccountCreation());
    }

    @Test
    public void shouldMapReaderListToReaderDTOList() {
        //Given
        Reader reader = Reader.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        Reader reader1 = Reader.builder()
                .id(2L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        Reader reader2 = Reader.builder()
                .id(3L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        List<Reader> readers = new ArrayList<>();
        readers.add(reader);
        readers.add(reader1);
        readers.add(reader2);
        //When
        List<ReaderDTO> dtos = mapper.mapReaderListToReaderDTOList(readers);
        //Then
        assertEquals(3, dtos.size());
    }

    @Test
    public void shouldMapReaderDTOListToReaderList() {
        //Given
        ReaderDTO readerDTO = ReaderDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        ReaderDTO readerDTO1 = ReaderDTO.builder()
                .id(2L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        ReaderDTO readerDTO2 = ReaderDTO.builder()
                .id(3L)
                .firstName("John")
                .lastName("Doe")
                .dateOfAccountCreation(LocalDateTime.of(2018, 1, 2, 15, 15))
                .build();

        List<ReaderDTO> readerDTOS = new ArrayList<>();
        readerDTOS.add(readerDTO);
        readerDTOS.add(readerDTO1);
        readerDTOS.add(readerDTO2);
        //When
        List<Reader> readers = mapper.mapReaderDTOListToReaderList(readerDTOS);
        //Then
        assertEquals(3, readers.size());
    }
}
