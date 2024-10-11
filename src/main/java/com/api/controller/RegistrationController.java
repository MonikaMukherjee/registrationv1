package com.api.controller;

import com.api.entity.Registration;

import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {

        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    //create Registration method

    @PostMapping //use for saving the data in database
    public ResponseEntity<?> createRegistration(

           @Valid @RequestBody RegistrationDto registrationDto,  //if I do not give this @valid then spring validation won't work.it ensure data going to object firstly we validating it
//I use special class binding,if it is having some error I wnt to return back to error in postman
      BindingResult result

    ) {
       if(result.hasErrors()){
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
       }

        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);  //whenever you create a record in the database the status code in postman 201

    }

    //delete a record
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
            @RequestParam long id
    ) {

        registrationService.deleteRegistration(id);
return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    //update method
    @PutMapping ("/{id}")  //help us update database via api
    public ResponseEntity<Registration>updateRegistration(
        @PathVariable long id,  //how value supplied to the url as path parameter not query parameter
        @RequestBody Registration registration) {
        Registration updateReg = registrationService.updateRegistration(id, registration);
        return new ResponseEntity<>(updateReg, HttpStatus.OK);
    }
    //build one more method that fetch record by id number
    @GetMapping("/{id}") //use {id} bcz of making different url,before I also use getMapping
    public ResponseEntity<RegistrationDto>getRegistrationById(
            @PathVariable long id

    ){
        //controller calls service layer
        RegistrationDto dto= registrationService.getRegistrationById(id);
return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
