package com.horizon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer id;
    private Integer roleId;
    private String roleName;
    private String googleId;
    @Email(message = "Email must be a valid email address")
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Phone must be a valid phone number")
    private String phone;
    private String accessToken;
    private Timestamp createdDate;
    private Timestamp lastLogin;
    private Boolean gender;
    private Date dateOfBirth;
    private String profilePicture;
    private Boolean isActivated;
}
