package com.wellcareceramica.repositories;

import com.wellcareceramica.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepositories extends JpaRepository<Category,Integer> {

    Category findByCategorySysidAndSysStatus(Integer categoryId,String sysStatus);

    List<Category> findBySysStatus(String sysStatus);
}
