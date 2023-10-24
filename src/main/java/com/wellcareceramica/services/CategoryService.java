package com.wellcareceramica.services;

import com.wellcareceramica.entities.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    Integer addCategory(Category category,Integer creatorId);

    Category updateCategory(Category category,Integer modifierId);

    List<Category> getAllCategory();

    Integer deleteCategory(Integer categoryId,Integer modifierId);


}
