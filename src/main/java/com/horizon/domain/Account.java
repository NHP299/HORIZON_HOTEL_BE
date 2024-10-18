package com.horizon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

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
