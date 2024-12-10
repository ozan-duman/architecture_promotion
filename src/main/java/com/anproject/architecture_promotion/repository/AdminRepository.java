package com.anproject.architecture_promotion.repository;

import com.anproject.architecture_promotion.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
