package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.DesignCategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignCategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignCategoryResponseDTO;
import com.anproject.architecture_promotion.dto.response.SettingResponseDTO;
import com.anproject.architecture_promotion.entity.Category;
import com.anproject.architecture_promotion.entity.Design;
import com.anproject.architecture_promotion.entity.DesignCategory;
import com.anproject.architecture_promotion.entity.Setting;
import com.anproject.architecture_promotion.mapper.DesignCategoryMapper;
import com.anproject.architecture_promotion.repository.DesignCategoryRepository;
import com.anproject.architecture_promotion.repository.DesignRepository;
import com.anproject.architecture_promotion.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignCategoryService {

    private final DesignCategoryRepository designCategoryRepository;
    private final DesignRepository designRepository;
    private final CategoryRepository categoryRepository;
    private final DesignCategoryMapper designCategoryMapper;

    @Autowired
    public DesignCategoryService(DesignCategoryRepository designCategoryRepository,
                                 DesignRepository designRepository,
                                 CategoryRepository categoryRepository,
                                 DesignCategoryMapper designCategoryMapper) {
        this.designCategoryRepository = designCategoryRepository;
        this.designRepository = designRepository;
        this.categoryRepository = categoryRepository;
        this.designCategoryMapper = designCategoryMapper;
    }

    public DesignCategoryResponseDTO save(DesignCategoryRequestDTO designCategoryRequestDTO) {
        Optional<Design> designOptional = designRepository.findById(designCategoryRequestDTO.getDesignId());
        Optional<Category> categoryOptional = categoryRepository.findById(designCategoryRequestDTO.getCategoryId());

        if (designOptional.isEmpty()) {
            throw new RuntimeException("Design not found with id: " + designCategoryRequestDTO.getDesignId());
        }

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + designCategoryRequestDTO.getCategoryId());
        }

        DesignCategory designCategory = new DesignCategory();
        designCategory.setDesign(designOptional.get());
        designCategory.setCategory(categoryOptional.get());

        DesignCategory savedDesignCategory = designCategoryRepository.save(designCategory);
        return designCategoryMapper.designCategoryToDesignCategoryResponseDTO(savedDesignCategory);
    }

    public DesignCategoryResponseDTO update(DesignCategoryUpdateDTO designCategoryUpdateDTO) {
        Optional<DesignCategory> optionalDesignCategory = designCategoryRepository.findById(designCategoryUpdateDTO.getId());

        if (optionalDesignCategory.isEmpty()) {
            throw new RuntimeException("DesignCategory not found with id: " + designCategoryUpdateDTO.getId());
        }

        Optional<Design> designOptional = designRepository.findById(designCategoryUpdateDTO.getDesignId());
        Optional<Category> categoryOptional = categoryRepository.findById(designCategoryUpdateDTO.getCategoryId());

        if (designOptional.isEmpty()) {
            throw new RuntimeException("Design not found with id: " + designCategoryUpdateDTO.getDesignId());
        }

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + designCategoryUpdateDTO.getCategoryId());
        }

        DesignCategory designCategoryToUpdate = optionalDesignCategory.get();
        designCategoryToUpdate.setDesign(designOptional.get());
        designCategoryToUpdate.setCategory(categoryOptional.get());

        DesignCategory updatedDesignCategory = designCategoryRepository.save(designCategoryToUpdate);
        return designCategoryMapper.designCategoryToDesignCategoryResponseDTO(updatedDesignCategory);
    }

    public List<DesignCategoryResponseDTO> findAll() {
        List<DesignCategory> designCategories = designCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return designCategories.stream()
                .map(designCategoryMapper::designCategoryToDesignCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    public DesignCategoryResponseDTO getByDesignCategoryId(Long id) {
        Optional<DesignCategory> designCategoryOptional = designCategoryRepository.findById(id);
        if (designCategoryOptional.isEmpty()) {
            throw new RuntimeException("DesignCategory not found with id: " + id);
        }
        return designCategoryMapper.designCategoryToDesignCategoryResponseDTO(designCategoryOptional.get());
    }

    public void delete(Long id) {
        Optional<DesignCategory> designCategoryOptional = designCategoryRepository.findById(id);
        if (designCategoryOptional.isEmpty()) {
            throw new RuntimeException("DesignCategory not found with id: " + id);
        }
        designCategoryRepository.deleteById(id);
    }

    public List<DesignCategoryResponseDTO> getAllDesignCategory() {
        List<DesignCategory> designCategoryList = designCategoryRepository.findAll();
        return designCategoryList.stream()
                .map(designCategory -> designCategoryMapper.designCategoryToDesignCategoryResponseDTO(designCategory))
                .toList(); // Convert each category to response DTO
    }

}
