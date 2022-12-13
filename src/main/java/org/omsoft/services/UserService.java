package org.omsoft.services;

import org.omsoft.entity.MyUser;
import org.omsoft.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public MyUser loadUser(String uname){
        return repo.findByUserName(uname);
    }

}
