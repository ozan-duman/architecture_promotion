package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.DesignCategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.DesignCategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.DesignCategoryResponseDTO;
import com.anproject.architecture_promotion.service.DesignCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/design-category")
public class DesignCategoryController {

    private final DesignCategoryService designCategoryService;

    @Autowired
    public DesignCategoryController(DesignCategoryService designCategoryService) {
        this.designCategoryService = designCategoryService;
    }

    // POST Mapping to save a new DesignCategory
    @PostMapping("/save")
    public ResponseEntity<DesignCategoryResponseDTO> saveDesignCategory(@Valid @RequestBody DesignCategoryRequestDTO designCategoryRequestDTO) {
        DesignCategoryResponseDTO designCategoryResponseDTO = designCategoryService.save(designCategoryRequestDTO);
        return new ResponseEntity<>(designCategoryResponseDTO, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesignCategory(@PathVariable Long id) {
        designCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DesignCategoryResponseDTO> updateDesignCategory(@PathVariable Long id, @Valid @RequestBody DesignCategoryUpdateDTO designCategoryUpdateDTO) {
        designCategoryUpdateDTO.setId(id);
        DesignCategoryResponseDTO designCategoryResponseDTO = designCategoryService.update(designCategoryUpdateDTO);
        return new ResponseEntity<>(designCategoryResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<DesignCategoryResponseDTO> getDesignCategoryById(@PathVariable Long id) {
        DesignCategoryResponseDTO designCategoryResponseDTO = designCategoryService.getByDesignCategoryId(id);
        return new ResponseEntity<>(designCategoryResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<DesignCategoryResponseDTO>> getAllDesignCategories() {
        List<DesignCategoryResponseDTO> designCategoryResponseDTOList = designCategoryService.getAllDesignCategory();
        return new ResponseEntity<>(designCategoryResponseDTOList, HttpStatus.OK);
    }
}
