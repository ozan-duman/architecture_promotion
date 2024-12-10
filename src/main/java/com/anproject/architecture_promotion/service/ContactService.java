package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.ContactRequestDTO;
import com.anproject.architecture_promotion.dto.request.ContactUpdateDTO;
import com.anproject.architecture_promotion.dto.response.ContactResponseDTO;
import com.anproject.architecture_promotion.entity.Contact;
import com.anproject.architecture_promotion.exception.ApiNotFoundException;
import com.anproject.architecture_promotion.mapper.ContactMapper;
import com.anproject.architecture_promotion.repository.ContactRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    private LocalDateTime submissionDate;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    // Save new contact
    public ContactResponseDTO save(ContactRequestDTO contactRequestDTO) {
        // DTO'yu entity'ye çevir
        Contact contact = contactMapper.contactRequestDTOtoContact(contactRequestDTO);
        // Veritabanına kaydet
        Contact savedContact = contactRepository.save(contact);
        // Kaydedilen entity'yi DTO'ya çevir
        return contactMapper.contactToContactResponseDTO(savedContact);
    }

    // Update existing contact
    public ContactResponseDTO updateContact(ContactUpdateDTO contactUpdateDTO) {
        // Güncellenecek contact'ı id ile bul
        Optional<Contact> optionalContact = contactRepository.findById(contactUpdateDTO.getId());

        if (optionalContact.isEmpty()) {
            throw new RuntimeException("Contact not found with id: " + contactUpdateDTO.getId());
        }

        // Mevcut contact'ı al ve güncelle
        Contact contactToUpdate = optionalContact.get();
        contactToUpdate.setContactName(contactUpdateDTO.getName());
        contactToUpdate.setEmail(contactUpdateDTO.getEmail());
        contactToUpdate.setMessage(contactUpdateDTO.getMessage());
        contactToUpdate.setSubmissionDate(contactUpdateDTO.getSubmissionDate());

        // Güncellenmiş contact'ı kaydet
        Contact updatedContact = contactRepository.save(contactToUpdate);
        // Güncellenmiş contact'ı DTO'ya çevir ve döndür
        return contactMapper.contactToContactResponseDTO(updatedContact);
    }

    // Get all contacts
    public List<ContactResponseDTO> findAll() {
        List<Contact> contacts = contactRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return contacts.stream()
                .map(contactMapper::contactToContactResponseDTO)
                .collect(Collectors.toList());
    }

    // Get contact by id
    public ContactResponseDTO findById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        // Bulunan contact'ı DTO'ya çevir ve döndür
        return contactMapper.contactToContactResponseDTO(contactOptional.get());
    }

    // Delete contact by id
    public void delete(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    public ContactResponseDTO getContactById (Long contactId){
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ApiNotFoundException("İletişim bilgileri bulunamadı."));
        return contactRepository.findById(contactId)
                .map(contactMapper:: contactToContactResponseDTO)
                .orElse(null);

    }

    public List<ContactResponseDTO> getAllCategories(){
        List<Contact> contactList = contactRepository.findAll();
        return contactList.stream()
                .map(setting -> contactMapper.contactToContactResponseDTO(setting))
                .toList();


    }

    @PrePersist
    protected void onCreate() {
        this.submissionDate = LocalDateTime.now();
    }


}
