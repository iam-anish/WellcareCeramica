package com.wellcareceramica.services;

import com.wellcareceramica.entities.PageableResponse;
import com.wellcareceramica.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product,Integer creatorId);

    Product getSingleProduct(Integer productId);

    PageableResponse<Product> getProduct(Integer categorySysid, Integer typeSysid, int pageNumber, int pageSize, String sortBy, String sortDir);

    PageableResponse<Product> filterProduct(String name);

    Product updateProduct(Product product,Integer modifierId);

    Integer deleteProduct(Integer productId,Integer modifierId);

}
