package com.anproject.architecture_promotion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String password;
}
