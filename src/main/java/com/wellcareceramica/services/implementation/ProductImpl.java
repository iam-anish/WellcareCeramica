package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Product;
import com.wellcareceramica.repositories.ProductRepositories;
import com.wellcareceramica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepositories productRepositories;

    @Override
    public Integer addProduct(Product product) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        product.setCreatedDate(ts);
        product.setSysStatus("A");

        try {
            productRepositories.save(product);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<Product> getProduct(Integer categorySysid, Integer typeSysid) {
        List<Product> productList = new ArrayList<>();
        if(categorySysid==null&&typeSysid==null){
            productList = productRepositories.findBySysStatus("A");
        } else if (typeSysid==null) {
            productList = productRepositories.findBySysStatusAndCategorySysid("A",categorySysid);
        }
        else {
            productList = productRepositories.findBySysStatusAndCategorySysidAndTypeSysid("A",categorySysid,typeSysid);
        }
        return productList;
    }
}
