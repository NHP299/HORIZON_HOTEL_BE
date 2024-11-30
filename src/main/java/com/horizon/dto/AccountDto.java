package com.horizon.dto;

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
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String accessToken;
    private Timestamp createdDate;
    private Timestamp lastLogin;
    private Boolean gender;
    private Date dateOfBirth;
    private String profilePicture;
    private Boolean isActivated;
}
