package com.wellcareceramica.repositories;

import com.wellcareceramica.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Integer> {

}
