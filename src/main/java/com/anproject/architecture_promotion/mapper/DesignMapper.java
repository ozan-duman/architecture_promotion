package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.DesignRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignResponseDTO;
import com.anproject.architecture_promotion.entity.Design;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DesignMapper {

    DesignMapper INSTANCE = Mappers.getMapper(DesignMapper.class);
    Design designRequestDTOtoDesign(DesignRequestDTO designRequestDTO);
    Design desingUpdateDTOtoDesign(DesignUpdateDTO designResponseDTO);
    DesignResponseDTO designToDesignResponseDTO(Design design);
}
