package com.example.test.dao;

import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimitRepository extends JpaRepository<jpaLimit,Integer> {
    List<jpaLimit> findByUserId(Integer UserId);
}
