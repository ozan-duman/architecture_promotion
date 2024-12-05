package com.anproject.architecture_promotion.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignResponseDTO {
    private Long id;
    private String designName;
    private String description;
    private String imageUrl;
    private String createdAt;

    private String design;
    private String category;

}
