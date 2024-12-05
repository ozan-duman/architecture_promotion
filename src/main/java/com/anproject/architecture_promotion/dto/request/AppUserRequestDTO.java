package com.anproject.architecture_promotion.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserRequestDTO {
    private String name;
    private String bio;
    private String email;
    private String profileImage;
}
