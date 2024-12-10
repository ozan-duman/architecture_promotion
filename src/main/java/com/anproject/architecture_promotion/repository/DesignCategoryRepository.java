package com.anproject.architecture_promotion.repository;

import com.anproject.architecture_promotion.entity.DesignCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignCategoryRepository extends JpaRepository<DesignCategory, Long> {
}
