package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.SettingRequestDTO;
import com.anproject.architecture_promotion.dto.request.SettingUpdateDTO;
import com.anproject.architecture_promotion.dto.response.SettingResponseDTO;
import com.anproject.architecture_promotion.entity.Setting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SettingMapper {

    SettingMapper INSTANCE = Mappers.getMapper(SettingMapper.class);

    Setting settingRequestDTOtoSetting (SettingRequestDTO settingRequestDTO);

    Setting settingUpdateDTOtoSetting (SettingUpdateDTO settingUpdateDTO);

    SettingResponseDTO settingToSettingResponseDTO (Setting setting);
}
