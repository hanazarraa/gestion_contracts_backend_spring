package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.username = ?1 and u.password =?2",nativeQuery = true)
    List<User> findByUserNameAndPassword(String username,String password);
    @Query(value = "SELECT * FROM users u WHERE u.username = ?1",nativeQuery = true)
    List<User> findByUserName(String username);


}
