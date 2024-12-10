package com.anproject.architecture_promotion.repository;

import com.anproject.architecture_promotion.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}
