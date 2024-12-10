package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.AppUserRequestDTO;
import com.anproject.architecture_promotion.dto.request.AppUserUpdateDTO;
import com.anproject.architecture_promotion.dto.request.SettingRequestDTO;
import com.anproject.architecture_promotion.dto.request.SettingUpdateDTO;
import com.anproject.architecture_promotion.dto.response.AppUserResponseDTO;
import com.anproject.architecture_promotion.dto.response.SettingResponseDTO;
import com.anproject.architecture_promotion.entity.AppUser;
import com.anproject.architecture_promotion.entity.Setting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    AppUser appUserRequestDTOtoAppUser (AppUserRequestDTO appUserRequestDTO);

    AppUser appUserUpdateDTOtoAppUser(AppUserUpdateDTO appUserUpdateDTO);

    AppUserResponseDTO appUserToAppUserResponseDTO (AppUser appUser);
}
