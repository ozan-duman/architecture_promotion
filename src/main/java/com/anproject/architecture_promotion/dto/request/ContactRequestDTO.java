package com.anproject.architecture_promotion.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDTO {
    private String contactName;
    private String email;
    private String message;
}
