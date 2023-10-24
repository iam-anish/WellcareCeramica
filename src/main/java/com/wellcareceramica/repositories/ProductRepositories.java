package com.wellcareceramica.repositories;

import com.wellcareceramica.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product,Integer> {

    Page<Product> findBySysStatus(String sysStatus,Pageable pageable);

    Page<Product> findBySysStatusAndCategorySysidAndTypeSysid(String sysStatus,Integer categorySysid,Integer TypeSysid,Pageable pageable);

    Page<Product> findBySysStatusAndCategorySysid(String sysStatus,Integer categorySysid,Pageable pageable);

    Product findByProductSysidAndSysStatus(Integer productId,String sysStatus);
}
