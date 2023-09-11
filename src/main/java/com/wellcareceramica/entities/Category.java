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
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_sysid")
    private Integer categorySysid;

    @Column(nullable = false)
    @JoinColumn(name = "creator_id", referencedColumnName = "user_sysid")
    private Integer creatorId;

    private Timestamp createdDate;

    @JoinColumn(name = "modifier_id", referencedColumnName = "user_sysid")
    private Integer modifierId;

    private Timestamp modifiedDate;

    private String sysStatus;

    @Column(nullable = false)
    private String name;

}
