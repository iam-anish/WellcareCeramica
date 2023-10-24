package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.Category;
import com.wellcareceramica.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add/{creatorId}")
    public ResponseEntity<Integer> addCategory(@RequestBody Category category,@PathVariable Integer creatorId){
        int returnMessage = categoryService.addCategory(category,creatorId);
        return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
    }

    @PutMapping("/update/{modifierId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer modifierId){
        Category category1 = categoryService.updateCategory(category,modifierId);
        return new ResponseEntity<>(category1,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @DeleteMapping("delete/{categoryId}/{modifierId}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable Integer categoryId,@PathVariable Integer modifierId){
        Integer returnMsg = categoryService.deleteCategory(categoryId, modifierId);
        return new ResponseEntity<>(returnMsg,HttpStatus.OK);
    }

}
