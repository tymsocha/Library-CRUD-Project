package com.library.library.business.reader.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReaderApi {

    @NonNull
    @ApiModelProperty
    private Long id;

    @ApiModelProperty
    private String firstName;

    @ApiModelProperty
    private String lastName;
}
