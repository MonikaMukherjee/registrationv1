package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

//retrieve data
   private RegistrationRepository registrationRepository;
   private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository) { //constructor based dependency injection
        this.registrationRepository = registrationRepository;
        this.modelMapper=modelMapper;
    }

    public List<RegistrationDto> getRegistrations(){
//using java 8 features in my project,which help me to convert all entity to dto.
       List<Registration> registrations= registrationRepository.findAll();
       List<RegistrationDto>dtos=registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }
//create registration
public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity
       Registration registration= mapToEntity(registrationDto);
       Registration savedEntity = registrationRepository.save(registration);
       
        //copy entity to dto
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }
    //delete registration
        public void deleteRegistration(long id){
        registrationRepository.deleteById(id);

    }
    //update registration
        public Registration updateRegistration(long id,Registration registration){
        Registration r=registrationRepository.findById(id).get();
        r.setId(id);
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity=registrationRepository.save(r);
        return savedEntity;

    }
    //this method take dto to entity 
       Registration mapToEntity(RegistrationDto registrationDto){
       Registration registration=new Registration();
       registration.setName(registrationDto.getName());
       registration.setEmail(registrationDto.getEmail());
       registration.setMobile(registrationDto.getMobile());
       return registration;
    }

    //now convert an entity object to dto obj
        RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
        
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                //use supplier,its does not take input ,it's only produces output
                ()->new ResourceNotFoundException("Record not found")
        ); //first it search record by id,if the record is found the data is stored in registration object.but if not found then its automatically goes to this part.its acts like an if else condition.
        //now convert this object
       return mapToDto(registration);
    }
}

