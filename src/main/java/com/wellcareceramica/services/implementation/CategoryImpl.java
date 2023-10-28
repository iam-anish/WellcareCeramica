package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Category;
import com.wellcareceramica.entities.Type;
import com.wellcareceramica.entities.User;
import com.wellcareceramica.repositories.CategoryRepositories;
import com.wellcareceramica.repositories.TypeRepositories;
import com.wellcareceramica.repositories.UserRepositories;
import com.wellcareceramica.services.CategoryService;
import com.wellcareceramica.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepositories categoryRepositories;

    @Autowired
    private TypeRepositories typeRepositories;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public Integer addCategory(Category category,Integer creatorId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        category.setCreatedDate(ts);
        category.setSysStatus("A");
        category.setCreatorId(creatorId);

        try {
            categoryRepositories.save(category);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public Category updateCategory(Category category, Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        Category category1 = categoryRepositories.findByCategorySysidAndSysStatus(category.getCategorySysid(),"A");
        category1.setName(category.getName());
        category1.setModifiedDate(ts);
        category1.setModifierId(modifierId);

        return categoryRepositories.save(category1);
    }

    @Override
    public List<Category> getAllCategory() {

        return categoryRepositories.findBySysStatus("A");
    }

    @Override
    public Integer deleteCategory(Integer categoryId, Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        Category category1 = categoryRepositories.findByCategorySysidAndSysStatus(categoryId,"A");
        category1.setSysStatus("D");
        category1.setModifiedDate(ts);
        category1.setModifierId(modifierId);

        try {
            List<Type> typeList = typeRepositories.findByCategorySysidAndSysStatus(categoryId, "A");
            for (Type type:
                 typeList) {
                 typeService.deleteType(type.getTypeSysid(),modifierId);
            }
            categoryRepositories.save(category1);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
