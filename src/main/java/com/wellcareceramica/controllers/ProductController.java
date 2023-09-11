package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.Product;
import com.wellcareceramica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @RestController
    @RequestMapping("/product")
    public class TypeController {

        @Autowired
        private ProductService productService;

        @PostMapping("/add")
        public ResponseEntity<Integer> addProduct(@RequestBody Product product){
            int returnMessage = productService.addProduct(product);
            return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
        }

        @GetMapping("/get")
        public ResponseEntity<List<Product>> getProduct(
                @RequestParam(name = "categorySysid",defaultValue = "",required = false) Integer categorySysid,
                @RequestParam(name = "typeSysid",defaultValue = "",required = false) Integer typeSysid
        ){
            List<Product> productList = productService.getProduct(categorySysid, typeSysid);
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }


    }

}
