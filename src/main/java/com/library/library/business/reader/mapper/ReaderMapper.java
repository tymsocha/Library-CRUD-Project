package com.library.library.business.reader.mapper;

import com.library.library.business.reader.domain.Reader;
import com.library.library.business.reader.dto.ReaderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public Reader mapReaderDTOToReader(ReaderDTO readerDTO) {
        return Reader.builder()
                .id(readerDTO.getId())
                .dateOfAccountCreation(readerDTO.getDateOfAccountCreation())
                .firstName(readerDTO.getFirstName())
                .lastName(readerDTO.getLastName())
                .build();
    }

    public ReaderDTO mapReaderToReaderDTO(Reader reader) {
        return ReaderDTO.builder()
                .id(reader.getId())
                .dateOfAccountCreation(reader.getDateOfAccountCreation())
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .build();
    }

    public List<Reader> mapReaderDTOListToReaderList(List<ReaderDTO> readerDTOList) {
        return readerDTOList.stream().map(readerDTO -> Reader.builder()
                    .id(readerDTO.getId())
                    .dateOfAccountCreation(readerDTO.getDateOfAccountCreation())
                    .firstName(readerDTO.getFirstName())
                    .lastName(readerDTO.getLastName())
                    .build())
                .collect(Collectors.toList());
    }

    public List<ReaderDTO> mapReaderListToReaderDTOList(List<Reader> readerList) {
        return readerList.stream().map(reader -> ReaderDTO.builder()
                    .id(reader.getId())
                    .dateOfAccountCreation(reader.getDateOfAccountCreation())
                    .firstName(reader.getFirstName())
                    .lastName(reader.getLastName())
                    .build())
                .collect(Collectors.toList());
    }
}
