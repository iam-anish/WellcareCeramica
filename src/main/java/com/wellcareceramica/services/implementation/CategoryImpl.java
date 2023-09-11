package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Category;
import com.wellcareceramica.entities.User;
import com.wellcareceramica.repositories.CategoryRepositories;
import com.wellcareceramica.repositories.UserRepositories;
import com.wellcareceramica.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepositories categoryRepositories;

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public Integer addCategory(Category category) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        category.setCreatedDate(ts);
        category.setSysStatus("A");

        try {
            categoryRepositories.save(category);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
