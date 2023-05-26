package com.hariwebinfotech.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registration")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer regId;
    private String username;
    private String email_id;
    private String password;
    private String re_password;
    private String role;
}
