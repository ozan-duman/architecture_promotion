package com.anproject.architecture_promotion.mapper;

import com.anproject.architecture_promotion.dto.request.CategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.CategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.CategoryResponseDTO;
import com.anproject.architecture_promotion.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryRequestDTOtoCategory(CategoryRequestDTO categoryRequestDTO);

    Category categoryUpdateDTOtoCategory(CategoryUpdateDTO categoryUpdateDTO);

    CategoryResponseDTO categoryToCategoryResponseDTO(Category category);


}
