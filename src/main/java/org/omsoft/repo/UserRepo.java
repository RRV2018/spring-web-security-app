package org.omsoft.repo;

import org.omsoft.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<MyUser,Integer> {
    MyUser findByUserName(String uname);

}
