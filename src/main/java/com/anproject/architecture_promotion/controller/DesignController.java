package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.DesignCategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignResponseDTO;
import com.anproject.architecture_promotion.service.DesignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DesignController {

    private final DesignService designService;
    @Autowired
    public DesignController(DesignService designService) {
        this.designService = designService;
    }


    @PostMapping("/save")
    public ResponseEntity<DesignResponseDTO> saveDesign (@Valid @RequestBody DesignRequestDTO designRequestDTO){
        DesignResponseDTO design = designService.save(designRequestDTO);
        return new ResponseEntity<>(design, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<DesignResponseDTO> deleteDesign (@PathVariable Long id){
        designService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DesignResponseDTO> updateDesign (@PathVariable Long id, @Valid @RequestBody DesignUpdateDTO designUpdateDTO){
        designUpdateDTO.setId(id);
        DesignResponseDTO designResponseDTO = designService.update(designUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<DesignResponseDTO> getDesignById(@PathVariable Long id){
        DesignResponseDTO designResponseDTO = designService.getDesignById(id);
        return new ResponseEntity<>(designResponseDTO,HttpStatus.OK);


    }

    @GetMapping("/all")
    public ResponseEntity<List<DesignResponseDTO>> getAllDesign(){
        List<DesignResponseDTO> designResponseDTOList = designService.getAllDesign();
        return new ResponseEntity<>(designResponseDTOList,HttpStatus.OK);


    }





}
