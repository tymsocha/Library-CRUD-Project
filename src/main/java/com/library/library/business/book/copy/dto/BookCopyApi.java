package com.library.library.business.book.copy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookCopyApi {

    @NonNull
    @ApiModelProperty
    private Long id;

    @ApiModelProperty
    private String status;

    @ApiModelProperty
    private String title;

    @ApiModelProperty
    private String author;
}
