package com.anproject.architecture_promotion.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingResponseDTO {
    private Long id;
    private String url;
    private String metaTag;
    private String smallLogo;
    private String largeLogo;
}
