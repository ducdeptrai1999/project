package com.buiminhduc.repository.impl;

import com.buiminhduc.model.entity.SanPhamEntity;
import com.buiminhduc.repository.SanPhamRepository;

import java.sql.SQLException;
import java.util.List;

public class SanPhamRepositoryImpl extends BasicQuery<SanPhamEntity, Integer> implements SanPhamRepository {
    @Override
    public List<SanPhamEntity> findAll()  {
        return super.findAll();
    }

    @Override
    public SanPhamEntity findById(Integer id) {
        return super.findById(id);
    }
}
