package com.murathnakts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGroupIU extends DtoBase {

    @NotEmpty
    private String groupName;

    @NotEmpty
    private String description;
}
