package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.Type;
import com.wellcareceramica.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/type")
public class TypeController{

    @Autowired
    private TypeService typeService;

    @PostMapping("/add/{creatorId}")
    public ResponseEntity<Integer> addType(@RequestBody Type type,@PathVariable Integer creatorId){
        int returnMessage = typeService.addType(type,creatorId);
        return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Type>> getTypes(@RequestParam(name = "categorySysid", defaultValue = "", required = false) Integer categorySysid){
        List<Type> typeList = typeService.getAllTypes(categorySysid);
        return new ResponseEntity<>(typeList,HttpStatus.OK);
    }

    @PutMapping("/update/{modifierId}")
    public ResponseEntity<Type> updateType(@RequestBody Type type,@PathVariable Integer modifierId){
        Type type1 = typeService.updateType(type,modifierId);
        return new ResponseEntity<>(type1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{typeId}/{modifierId}")
    public ResponseEntity<Integer> deleteType(@PathVariable Integer typeId,@PathVariable Integer modifierId){
        Integer returnMsg = typeService.deleteType(typeId,modifierId);
        return new ResponseEntity<>(returnMsg,HttpStatus.OK);
    }

}
