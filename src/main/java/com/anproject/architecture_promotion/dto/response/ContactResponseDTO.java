package com.anproject.architecture_promotion.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String message;
    private String submissionDate;
}
