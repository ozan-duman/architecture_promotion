package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.ContactRequestDTO;
import com.anproject.architecture_promotion.dto.request.ContactUpdateDTO;
import com.anproject.architecture_promotion.dto.response.ContactResponseDTO;
import com.anproject.architecture_promotion.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact contactRequestDTOtoContact (ContactRequestDTO contactRequestDTO);
    @Mapping(source = "submissionDate" , target = "submissionDate")
    Contact contactUpdateDTOtoContact (ContactUpdateDTO contactUpdateDTO);

    @Mapping(source = "submissionDate" , target = "submissionDate", qualifiedByName = "formatLocalDateTime")
    ContactResponseDTO contactToContactResponseDTO (Contact contact);


    @Named("formatLocalDateTime")
    static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.ofPattern("dd-MM-yyyy");
        return dateTime != null ? dateTime.format(formatter) : null;
    }


}
