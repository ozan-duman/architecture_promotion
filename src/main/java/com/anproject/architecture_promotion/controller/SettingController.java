package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.SettingRequestDTO;
import com.anproject.architecture_promotion.dto.request.SettingUpdateDTO;
import com.anproject.architecture_promotion.dto.response.SettingResponseDTO;
import com.anproject.architecture_promotion.service.SettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping("/save")
    public ResponseEntity<SettingResponseDTO> saveCategory (@Valid @RequestBody SettingRequestDTO settingRequestDTO){
        SettingResponseDTO setting = settingService.save(settingRequestDTO);
        return new ResponseEntity<>(setting,HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSettingImage (@PathVariable Long id){
        settingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")

    public ResponseEntity<SettingResponseDTO> updateSetting(@PathVariable Long id, @Valid @RequestBody SettingUpdateDTO settingUpdateDTO){
        settingUpdateDTO.setId(id);
        SettingResponseDTO setting = settingService.updateSetting(settingUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SettingResponseDTO> getSettingById (@PathVariable Long id){
        SettingResponseDTO settingResponseDTO = settingService.getSettingById(id);
        return new ResponseEntity<>(settingResponseDTO, HttpStatus.OK);


    }

    @GetMapping("/all")
    public ResponseEntity<List<SettingResponseDTO>> getAllSettings(){
        List<SettingResponseDTO> settingResponseDTOList = settingService.getAllSettings();
        return new ResponseEntity<>(settingResponseDTOList,HttpStatus.OK);

    }


}
