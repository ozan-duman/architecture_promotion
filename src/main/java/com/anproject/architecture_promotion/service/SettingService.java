package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.SettingRequestDTO;
import com.anproject.architecture_promotion.dto.request.SettingUpdateDTO;
import com.anproject.architecture_promotion.dto.response.CategoryResponseDTO;
import com.anproject.architecture_promotion.dto.response.SettingResponseDTO;
import com.anproject.architecture_promotion.entity.Category;
import com.anproject.architecture_promotion.entity.Setting;
import com.anproject.architecture_promotion.exception.ApiNotFoundException;
import com.anproject.architecture_promotion.mapper.SettingMapper;
import com.anproject.architecture_promotion.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettingService {

    private final SettingRepository settingRepository;
    private final SettingMapper settingMapper;

    @Autowired
    public SettingService(SettingRepository settingRepository, SettingMapper settingMapper) {
        this.settingRepository = settingRepository;
        this.settingMapper = settingMapper;
    }


    public SettingResponseDTO save(SettingRequestDTO settingRequestDTO) {
        Setting setting = settingMapper.settingRequestDTOtoSetting(settingRequestDTO);
        Setting savedSetting = settingRepository.save(setting);
        return settingMapper.settingToSettingResponseDTO(savedSetting);
    }

    public SettingResponseDTO updateSetting(SettingUpdateDTO settingUpdateDTO) {
        Optional<Setting> optionalSetting = settingRepository.findById(settingUpdateDTO.getId());

        if (optionalSetting.isEmpty()) {
            throw new RuntimeException("Değiştirilmek istenen ayar için id bulunamadı" + settingUpdateDTO.getId());
        }

        Setting settingToUpdate = optionalSetting.get();
        settingToUpdate.setUrl(settingUpdateDTO.getUrl());
        settingToUpdate.setMetaTag(settingUpdateDTO.getMetaTag());
        settingToUpdate.setSmallLogo(settingUpdateDTO.getSmallLogo());
        settingToUpdate.setLargeLogo(settingUpdateDTO.getLargeLogo());

        Setting updatedSetting = settingRepository.save(settingToUpdate);
        return settingMapper.settingToSettingResponseDTO(updatedSetting);
    }

    public List<SettingResponseDTO> findAll() {
        List<Setting> settings = settingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return settings.stream()
                .map(settingMapper::settingToSettingResponseDTO)
                .collect(Collectors.toList());
    }

    public SettingResponseDTO getById(Long id) {
        Optional<Setting> settingOptional = settingRepository.findById(id);
        if (settingOptional.isEmpty()) {
            throw new RuntimeException("Setting not found with id: " + id);
        }
        return settingMapper.settingToSettingResponseDTO(settingOptional.get());
    }

    public void delete(Long id) {
        Optional<Setting> settingOptional = settingRepository.findById(id);
        if (settingOptional.isEmpty()) {
            throw new RuntimeException("Setting not found with id: " + id);
        }
        settingRepository.deleteById(id);
    }


    public SettingResponseDTO getSettingById(Long settingId) {
        Setting setting = settingRepository.findById(settingId)
                .orElseThrow(() -> new ApiNotFoundException("Kategori bulunamadı."));
        // Find the category and return response DTO if present
        return settingRepository.findById(settingId)
                .map(settingMapper::settingToSettingResponseDTO)
                .orElse(null); // Return null if not found
    }

    // Find all categories
    public List<SettingResponseDTO> getAllSettings() {
        List<Setting> settingList = settingRepository.findAll();
        return settingList.stream()
                .map(setting -> settingMapper.settingToSettingResponseDTO(setting))
                .toList(); // Convert each category to response DTO
    }






}
