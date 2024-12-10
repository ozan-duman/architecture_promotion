package com.anproject.architecture_promotion.repository;

import com.anproject.architecture_promotion.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long>{
}
