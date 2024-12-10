package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.AdminRequestDTO;
import com.anproject.architecture_promotion.dto.request.AdminUpdateDTO;
import com.anproject.architecture_promotion.dto.response.AdminResponseDTO;
import com.anproject.architecture_promotion.dto.response.ContactResponseDTO;
import com.anproject.architecture_promotion.entity.Admin;
import com.anproject.architecture_promotion.mapper.AdminMapper;
import com.anproject.architecture_promotion.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public AdminResponseDTO save(AdminRequestDTO adminRequestDTO) {
        Admin admin = adminMapper.adminRequestDTOtoAdmin(adminRequestDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.adminToaAdminResponseDTO(savedAdmin);
    }

    public AdminResponseDTO updateAdmin(AdminUpdateDTO adminUpdateDTO) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminUpdateDTO.getId());

        if (optionalAdmin.isEmpty()) {
            throw new RuntimeException("Admin not found with id: " + adminUpdateDTO.getId());
        }

        Admin adminToUpdate = optionalAdmin.get();
        adminToUpdate.setNickname(adminToUpdate.getNickname());
        adminToUpdate.setPassword(adminUpdateDTO.getPassword());

        Admin updatedAdmin = adminRepository.save(adminToUpdate);
        return adminMapper.adminToaAdminResponseDTO(updatedAdmin);
    }

    public List<AdminResponseDTO> findAll() {
        List<Admin> admins = adminRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return admins.stream()
                .map(adminMapper::adminToaAdminResponseDTO)
                .collect(Collectors.toList());
    }

    public AdminResponseDTO getAdminById(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        return adminMapper.adminToaAdminResponseDTO(adminOptional.get());
    }

    public void delete(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }




}
