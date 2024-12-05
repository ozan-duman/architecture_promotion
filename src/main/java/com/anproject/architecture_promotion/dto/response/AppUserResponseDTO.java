package com.anproject.architecture_promotion.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponseDTO {
    private Long id;
    private String name;
    private String bio;
    private String email;
    private String profileImage;
}
