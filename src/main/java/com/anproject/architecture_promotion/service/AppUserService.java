package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.AppUserRequestDTO;
import com.anproject.architecture_promotion.dto.response.AppUserResponseDTO;
import com.anproject.architecture_promotion.entity.AppUser;
import com.anproject.architecture_promotion.mapper.AppUserMapper;
import com.anproject.architecture_promotion.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    public AppUserResponseDTO saveUser(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = appUserMapper.appUserRequestDTOtoAppUser(appUserRequestDTO);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return appUserMapper.appUserToAppUserResponseDTO(savedAppUser);
    }

    public AppUserResponseDTO update(AppUserRequestDTO appUserRequestDTO, Long id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isEmpty()) {
            throw new RuntimeException("AppUser not found with id: " + id);
        }

        AppUser appUserToUpdate = optionalAppUser.get();
        appUserToUpdate.setName(appUserRequestDTO.getName());
        appUserToUpdate.setBio(appUserRequestDTO.getBio());
        appUserToUpdate.setProfileImage(appUserRequestDTO.getProfileImage());

        AppUser updatedAppUser = appUserRepository.save(appUserToUpdate);
        return appUserMapper.appUserToAppUserResponseDTO(updatedAppUser);
    }

    public List<AppUserResponseDTO> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return appUsers.stream()
                .map(appUserMapper::appUserToAppUserResponseDTO)
                .collect(Collectors.toList());
    }

    public AppUserResponseDTO findById(Long id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if (appUserOptional.isEmpty()) {
            throw new RuntimeException("AppUser not found with id: " + id);
        }
        return appUserMapper.appUserToAppUserResponseDTO(appUserOptional.get());
    }

    public void deleteUser(Long id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if (appUserOptional.isEmpty()) {
            throw new RuntimeException("AppUser not found with id: " + id);
        }
        appUserRepository.deleteById(id);
    }
}
