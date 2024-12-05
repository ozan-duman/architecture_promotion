package com.anproject.architecture_promotion.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingRequestDTO {
    private String url;
    private String metaTag;
    private String smallLogo;
    private String largeLogo;
}
