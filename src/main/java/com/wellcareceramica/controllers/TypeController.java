package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.Type;
import com.wellcareceramica.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addType(@RequestBody Type type){
        int returnMessage = typeService.addType(type);
        return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
    }

}
