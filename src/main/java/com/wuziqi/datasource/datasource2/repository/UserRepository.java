package com.wuziqi.datasource.datasource2.repository;

import com.wuziqi.datasource.datasource2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);

    List<User> findByUsernameAndCreateTime(String admin, String s);

    List<User> findByUsernameOrCreateTime(String admin, String s);
}
