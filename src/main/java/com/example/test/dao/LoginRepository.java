package com.example.test.dao;

import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<jpaUser,Integer> {

    jpaUser findByUsername(String username);
}
