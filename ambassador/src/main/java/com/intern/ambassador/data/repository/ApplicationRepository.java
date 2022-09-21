package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Apply, Long> {
}
