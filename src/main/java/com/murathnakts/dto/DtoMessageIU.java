package com.murathnakts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMessageIU {

    private Long senderId;

    @NotEmpty
    private String content;

    private Long groupId;
}
