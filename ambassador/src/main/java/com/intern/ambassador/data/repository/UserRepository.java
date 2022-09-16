package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
    User deleteUserByUidAndPassword(String uid, String password);
}
