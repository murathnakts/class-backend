package com.murathnakts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMessageIU {

    @NotNull
    private Long senderId;

    @NotEmpty
    private String content;
}
