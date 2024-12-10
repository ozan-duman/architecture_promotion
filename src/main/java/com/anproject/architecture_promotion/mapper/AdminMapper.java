package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.AdminRequestDTO;
import com.anproject.architecture_promotion.dto.request.AdminUpdateDTO;
import com.anproject.architecture_promotion.dto.response.AdminResponseDTO;
import com.anproject.architecture_promotion.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin adminRequestDTOtoAdmin (AdminRequestDTO adminRequestDTO);

    Admin adminUpdateDTOtoAdmin (AdminUpdateDTO adminUpdateDTO);

    AdminResponseDTO adminToaAdminResponseDTO (Admin admin);

}
