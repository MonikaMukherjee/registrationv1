package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {
    @NotEmpty

    @Size(min=2,message = "minimum should be 2 letters")
    private String name;
    //want to validate email
    @Email //this check whether this format of email id is given is right or wrong
    private String email;

    @Size(min = 10, max = 10, message = "should be 10 digits")
    private String mobile;

}