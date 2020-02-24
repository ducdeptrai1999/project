package com.buiminhduc.service.impl;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.common.annotation.Service;
import com.buiminhduc.converter.UserConverter;
import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.Auth;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.repository.UserRepository;
import com.buiminhduc.repository.impl.UserRepositoryImpl;
import com.buiminhduc.service.UserService;
import com.buiminhduc.util.ObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = (UserRepository) BeanFactory.getBeans().get("userRepository");
    }

    @Override
    public void insert(UserRequest request) throws SQLException {
        UserEntity entity = UserConverter.converToEntity(request);
        new UserRepositoryImpl().insert(entity);
    }

    @Override
    public void update(UserRequest request, int id1) throws SQLException {
        UserEntity entity = UserConverter.converToEntity(request, id1);
        new UserRepositoryImpl().update(entity);
    }

    @Override
    public UserResponse findUserByUserNameAndPassWord(Auth auth) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ObjectNotFoundException, NoSuchMethodException, InvocationTargetException {
        Optional<UserEntity> userEntity = userRepository.findByUserNameAndPassWord(auth.getUserName(), auth.getPassword());

        UserResponse userResponse = new UserResponse();
        userEntity.orElseThrow(ObjectNotFoundException::new);
        ObjectUtil.copyProperties(userEntity.get(),userResponse);

        return userResponse;
    }

    @Override
    public List<UserResponse> findAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<UserEntity> userEntities= userRepository.findAll();
        return userEntities
                .stream()
                .map(UserConverter::converToRespond)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) throws NoSuchFieldException {
        new UserRepositoryImpl().delete(id);
    }


}
