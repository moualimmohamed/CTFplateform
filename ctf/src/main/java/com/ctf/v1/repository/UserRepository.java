package com.ctf.v1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
}
