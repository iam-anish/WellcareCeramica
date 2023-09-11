package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Type;
import com.wellcareceramica.repositories.TypeRepositories;
import com.wellcareceramica.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TypeImpl implements TypeService {

    @Autowired
    private TypeRepositories typeRepositories;

    @Override
    public Integer addType(Type type){

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        type.setCreatedDate(ts);
        type.setSysStatus("A");

        try{
            typeRepositories.save(type);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
