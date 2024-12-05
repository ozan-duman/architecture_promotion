package com.anproject.architecture_promotion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingUpdateDTO {

    @NotBlank
    private Long id;

    @NotNull
    private String url;

    @NotNull
    private String metaTag;

    @NotNull
    private String smallLogo;

    @NotNull
    private String largeLogo;
}
