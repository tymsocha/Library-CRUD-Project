package com.library.library.business.book.borrowing.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BorrowingListApi {

    @NonNull
    @ApiModelProperty
    private Long id;

    @ApiModelProperty
    private LocalDateTime borrowingDate;

    @ApiModelProperty
    private LocalDateTime returningDate;
}
