package com.wellcareceramica.services;

import com.wellcareceramica.entities.Type;

import java.util.List;

public interface TypeService {
    Integer addType(Type type,Integer creatorId);

    Type getSingleType(Integer typeSysId);

    List<Type> getAllTypes(Integer categoryId);

    Type updateType(Type type,Integer modifierId);

    Integer deleteType(Integer typeSysId,Integer modifierId);

}
