package com.oshayer.blogbackend.repository;

import com.oshayer.blogbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {



}
