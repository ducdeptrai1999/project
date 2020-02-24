package com.buiminhduc.converter;

import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.util.ObjectUtil;

import java.lang.reflect.InvocationTargetException;

public class UserConverter {
    public static UserEntity converToEntity(UserRequest userRequest){
        UserEntity userEntity = new UserEntity();
        try {
            ObjectUtil.copyProperties(userRequest, userEntity);
        } catch (NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
    public static UserEntity converToEntity(UserRequest userRequest, int id){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        try {
            ObjectUtil.copyProperties(userRequest, userEntity);
        } catch (NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
    public static UserResponse converToRespond(UserEntity entity) {
        UserResponse respond = new UserResponse();
        try {
            ObjectUtil.copyProperties(entity, respond);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return respond;
    }
}
