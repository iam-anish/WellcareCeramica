package com.wellcareceramica.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_sysid")
    private Integer userSysid;

    private Timestamp createdDate;

    private String sysStatus;

    private String name;

    private Integer employeeId;

    private String email;

    private String contact;

    private String password;
}
