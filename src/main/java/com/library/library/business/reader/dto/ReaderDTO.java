package com.library.library.business.reader.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReaderDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime dateOfAccountCreation;
}
