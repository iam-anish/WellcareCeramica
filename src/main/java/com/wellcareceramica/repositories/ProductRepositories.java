package com.wellcareceramica.repositories;

import com.wellcareceramica.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product,Integer> {

    List<Product> findBySysStatus(String sysStatus);

    List<Product> findBySysStatusAndCategorySysidAndTypeSysid(String sysStatus,Integer categorySysid,Integer TypeSysid);

    List<Product> findBySysStatusAndCategorySysid(String sysStatus,Integer categorySysid);
}
