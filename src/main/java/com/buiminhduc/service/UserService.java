package com.buiminhduc.service;

import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.Auth;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.model.respond.UserResponse;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void insert(UserRequest request) throws SQLException;

    void update(UserRequest request, int id) throws SQLException;

    UserResponse findUserByUserNameAndPassWord(Auth auth) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ObjectNotFoundException, NoSuchMethodException, InvocationTargetException;

    List<UserResponse> findAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    void delete (int id) throws NoSuchFieldException;

}
