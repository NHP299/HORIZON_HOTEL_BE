package com.horizon.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AccountDto {
    private Integer id;
    private Integer roleId;
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
