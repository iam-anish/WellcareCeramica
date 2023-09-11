package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.User;
import com.wellcareceramica.repositories.UserRepositories;
import com.wellcareceramica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public User addUser(User user){

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        user.setCreatedDate(ts);
        user.setSysStatus("A");

        User user1 = userRepositories.save(user);
        return user1;
    }
}
