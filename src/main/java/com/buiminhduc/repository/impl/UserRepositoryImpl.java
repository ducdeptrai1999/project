package com.buiminhduc.repository.impl;

import com.buiminhduc.common.annotation.Repository;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.repository.UserRepository;
import com.buiminhduc.util.ObjectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BasicQuery<UserEntity, Integer> implements UserRepository {
    @Override
    public Optional<UserEntity> findByUserNameAndPassWord(String userName, String password) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, userName);
        ps.setObject(2, password);
        ResultSet rs = ps.executeQuery();
        UserEntity userEntity = null;
        while (rs.next()) {
            userEntity = (UserEntity) ObjectUtil.map(tClass, rs);
        }
        return Optional.ofNullable(userEntity);
    }

    @Override
    public <S extends UserEntity> S insert(UserEntity entity) throws SQLException {
        return super.insert(entity);
    }

    @Override
    public <S extends UserEntity> List<S> findAll(){
        return super.findAll();
    }

    @Override
    public <S extends UserEntity> S findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public void update(UserEntity entity, int ID) throws SQLException {
        super.update(entity);
    }
}
