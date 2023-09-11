package com.wellcareceramica.services;

import com.wellcareceramica.entities.Product;

import java.util.List;

public interface ProductService {

    Integer addProduct(Product product);

    List<Product> getProduct(Integer categorySysid,Integer typeSysid);
}
