package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.ContactRequestDTO;
import com.anproject.architecture_promotion.dto.request.ContactUpdateDTO;
import com.anproject.architecture_promotion.dto.response.ContactResponseDTO;
import com.anproject.architecture_promotion.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/save")
    public ResponseEntity<ContactResponseDTO> saveContatct (@Valid @RequestBody ContactRequestDTO contactRequestDTO){
        ContactResponseDTO contact = contactService.save(contactRequestDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long id){
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact (@PathVariable Long id, @Valid @RequestBody ContactUpdateDTO contactUpdateDTO){
        contactUpdateDTO.setId(id);
        ContactResponseDTO contactResponseDTO = contactService.updateContact(contactUpdateDTO);
        return new ResponseEntity<>(contactResponseDTO, HttpStatus.OK);

    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById (@PathVariable Long id){
        ContactResponseDTO contactResponseDTO = contactService.getContactById(id);
        return new ResponseEntity<>(contactResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts(){
        List<ContactResponseDTO> contactResponseDTOList = contactService.getAllCategories();
        return new ResponseEntity<>(contactResponseDTOList,HttpStatus.OK);

    }





}
