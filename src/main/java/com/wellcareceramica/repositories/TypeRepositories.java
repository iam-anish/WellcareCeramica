package com.wellcareceramica.repositories;

import com.wellcareceramica.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepositories extends JpaRepository<Type,Integer> {

    Type findByTypeSysidAndSysStatus(Integer typeSysId,String sysStatus);

    List<Type> findByCategorySysidAndSysStatus(Integer categoryId,String sysStatus);

    List<Type> findBySysStatus(String sysStatus);

}
