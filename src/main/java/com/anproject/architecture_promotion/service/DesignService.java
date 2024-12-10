package com.anproject.architecture_promotion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anproject.architecture_promotion.dto.request.DesignRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignResponseDTO;
import com.anproject.architecture_promotion.entity.Design;
import com.anproject.architecture_promotion.mapper.DesignMapper;
import com.anproject.architecture_promotion.repository.DesignRepository;

@Service
public class DesignService {

    private final DesignRepository designRepository;
    private final DesignMapper designMapper;

    @Autowired
    public DesignService(DesignRepository designRepository, DesignMapper designMapper) {
        this.designRepository = designRepository;
        this.designMapper = designMapper;
    }

    public DesignResponseDTO save(DesignRequestDTO designRequestDTO) {
        Design design = designMapper.designRequestDTOtoDesign(designRequestDTO);
        Design savedDesign = designRepository.save(design);
        return designMapper.designToDesignResponseDTO(savedDesign);
    }

    public DesignResponseDTO update(DesignUpdateDTO designUpdateDTO) {
        Optional<Design> optionalDesign = designRepository.findById(Long.valueOf(designUpdateDTO.getId()));

        if (optionalDesign.isEmpty()) {
            throw new RuntimeException("Design not found with id: " + designUpdateDTO.getId());
        }

        Design designToUpdate = optionalDesign.get();
        designToUpdate.setDesignName(designUpdateDTO.getDesignName());
        designToUpdate.setDescription(designUpdateDTO.getDescription());
        designToUpdate.setImageUrl(designUpdateDTO.getImageUrl());

        Design updatedDesign = designRepository.save(designToUpdate);
        return designMapper.designToDesignResponseDTO(updatedDesign);
    }

    public List<DesignResponseDTO> findAll() {
        List<Design> designs = designRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return designs.stream()
                .map(designMapper::designToDesignResponseDTO)
                .collect(Collectors.toList());
    }

    public DesignResponseDTO getDesignById(Long id) {
        Optional<Design> designOptional = designRepository.findById(id);
        if (designOptional.isEmpty()) {
            throw new RuntimeException("Design not found with id: " + id);
        }
        return designMapper.designToDesignResponseDTO(designOptional.get());
    }

    public void delete(Long id) {
        Optional<Design> designOptional = designRepository.findById(id);
        if (designOptional.isEmpty()) {
            throw new RuntimeException("Design not found with id: " + id);
        }
        designRepository.deleteById(id);
    }

    public List<DesignResponseDTO> getAllDesign(){
        List<Design> designList = designRepository.findAll();
        return designList.stream().map(design -> designMapper.designToDesignResponseDTO(design)).toList();


    }




}
