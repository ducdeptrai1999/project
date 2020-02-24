package com.buiminhduc.repository;

import com.buiminhduc.model.entity.UserEntity;

import java.sql.SQLException;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserNameAndPassWord(String userName, String passWord) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;
}
