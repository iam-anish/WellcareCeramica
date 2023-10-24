package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Type;
import com.wellcareceramica.repositories.TypeRepositories;
import com.wellcareceramica.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TypeImpl implements TypeService {

    @Autowired
    private TypeRepositories typeRepositories;

    @Override
    public Integer addType(Type type,Integer creatorId){

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        type.setCreatedDate(ts);
        type.setSysStatus("A");
        type.setCreatorId(creatorId);

        try{
            typeRepositories.save(type);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public Type getSingleType(Integer typeSysId) {
        Type type = typeRepositories.findByTypeSysidAndSysStatus(typeSysId,"A");
        return type;
    }

    @Override
    public List<Type> getAllTypes(Integer categoryId) {
        List<Type> typeList = new ArrayList<>();
        if(categoryId==null) {
           typeList  = typeRepositories.findBySysStatus("A");
        }else {
            typeList = typeRepositories.findByCategorySysidAndSysStatus(categoryId,"A");
        }
        return typeList;
    }

    @Override
    public Type updateType(Type type, Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        
        Type type1 = typeRepositories.findByTypeSysidAndSysStatus(type.getTypeSysid(),"A");
        type1.setModifierId(modifierId);
        type1.setName(type.getName());
        type1.setModifiedDate(ts);
        type1.setCategorySysid(type.getCategorySysid());
        
        return typeRepositories.save(type1);
    }

    @Override
    public Integer deleteType(Integer typeSysId, Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        Type type1 = typeRepositories.findByTypeSysidAndSysStatus(typeSysId,"A");
        type1.setModifierId(modifierId);
        type1.setSysStatus("D");
        
        try {
            typeRepositories.save(type1);
            return  1;
        }catch (Exception e){
            return 0;
        }
    }
}
