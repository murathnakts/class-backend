package com.murathnakts.dto;

import com.murathnakts.enums.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private RoleType role;
}
