package com.buiminhduc.repository.dao;

import com.buiminhduc.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.Set;

public interface ObjectDAO {
		public ArrayList<UserEntity> getDanhSach();
		public Set<String> getDanhSachTheoHo();
}
