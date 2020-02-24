package com.buiminhduc.service;

import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.respond.RoleResponse;

import java.lang.reflect.InvocationTargetException;

public interface RoleService {
    RoleResponse findById(int id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ObjectNotFoundException;
}
