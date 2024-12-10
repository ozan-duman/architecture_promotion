package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.DesignCategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignCategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignCategoryResponseDTO;
import com.anproject.architecture_promotion.entity.DesignCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DesignMapper.class, CategoryMapper.class})
public interface DesignCategoryMapper {
    DesignCategoryMapper INSTANCE = Mappers.getMapper(DesignCategoryMapper.class);

    @Mapping(source = "designId", target = "design.id")
    @Mapping(source = "categoryId", target = "category.id")
    DesignCategory designCategoryRequestDTOtoDesignCategory(DesignCategoryRequestDTO designCategoryRequestDTO);

    @Mapping(source = "designId", target = "design.id")
    @Mapping(source = "categoryId", target = "category.id")
    DesignCategory designCategoryUpdateDTOtoDesignCategory(DesignCategoryUpdateDTO designCategoryUpdateDTO);

    @Mapping(source = "design", target = "design")
    @Mapping(source = "category", target = "category")
    DesignCategoryResponseDTO designCategoryToDesignCategoryResponseDTO(DesignCategory designCategory);
}