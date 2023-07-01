package com.SpringApp.SpringApp.repository;

import com.SpringApp.SpringApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
