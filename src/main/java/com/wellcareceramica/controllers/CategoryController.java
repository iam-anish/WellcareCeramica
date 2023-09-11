package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.Category;
import com.wellcareceramica.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://172.16.105.37:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addCategory(@RequestBody Category category){
        int returnMessage = categoryService.addCategory(category);
        return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
    }

}
