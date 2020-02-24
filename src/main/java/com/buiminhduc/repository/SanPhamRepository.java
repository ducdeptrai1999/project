package com.buiminhduc.repository;

import com.buiminhduc.model.entity.SanPhamEntity;

import java.sql.SQLException;
import java.util.List;


public interface SanPhamRepository extends JpaRepository<SanPhamEntity, Integer> {
    List<SanPhamEntity> findAll();
    SanPhamEntity findById(Integer id);
}
