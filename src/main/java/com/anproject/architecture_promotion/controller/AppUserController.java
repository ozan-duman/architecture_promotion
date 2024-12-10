package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.AppUserRequestDTO;
import com.anproject.architecture_promotion.dto.response.AppUserResponseDTO;
import com.anproject.architecture_promotion.entity.AppUser;
import com.anproject.architecture_promotion.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AppUserResponseDTO> getUserById(@PathVariable Long id){
        AppUserResponseDTO appUserResponseDTO = appUserService.findById(id);
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<AppUserResponseDTO> saveUser(@Valid @RequestBody AppUserRequestDTO appUserRequestDTO){
       AppUserResponseDTO user =  appUserService.saveUser(appUserRequestDTO);
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        appUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


}
