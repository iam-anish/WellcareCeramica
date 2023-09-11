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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_sysid")
    private Integer productSysid;

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

    @Column(nullable = false)
    @JoinColumn(name = "type_sysid", referencedColumnName = "type_sysid")
    private Integer typeSysid;

    @Column(nullable = false)
    @JoinColumn(name = "category_sysid", referencedColumnName = "category_sysid")
    private Integer categorySysid;

    @Column(nullable = false)
    private String size;

    private String imageId;

    private String pdfId;

}
