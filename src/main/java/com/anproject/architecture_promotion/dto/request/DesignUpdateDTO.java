package com.anproject.architecture_promotion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignUpdateDTO {

    @NotNull
    private String id;

    @NotBlank
    private String designName;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;
}
