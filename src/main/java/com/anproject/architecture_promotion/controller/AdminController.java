package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.AdminRequestDTO;
import com.anproject.architecture_promotion.dto.request.AdminUpdateDTO;
import com.anproject.architecture_promotion.dto.request.ContactUpdateDTO;
import com.anproject.architecture_promotion.dto.response.AdminResponseDTO;
import com.anproject.architecture_promotion.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolvedModule;
import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/save")
    public ResponseEntity<AdminResponseDTO> saveAdmin (@Valid @RequestBody AdminRequestDTO adminRequestDTO){
        AdminResponseDTO admin = adminService.save(adminRequestDTO  );
        return new  ResponseEntity<>(admin, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AdminResponseDTO> deleteAdmin (@PathVariable Long id){
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminResponseDTO> updateAdmin (@PathVariable Long id, @Valid @RequestBody AdminUpdateDTO adminUpdateDTO){
        adminUpdateDTO.setId(id);
        AdminResponseDTO adminResponseDTO = adminService.updateAdmin(adminUpdateDTO);
        return new ResponseEntity<>(adminResponseDTO,HttpStatus.OK );

    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id){
        AdminResponseDTO adminResponseDTO = adminService.getAdminById(id);
        return new ResponseEntity<>(adminResponseDTO,HttpStatus.OK);

    }



}
