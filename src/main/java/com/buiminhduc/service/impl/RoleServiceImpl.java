package com.buiminhduc.service.impl;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.common.annotation.Service;
import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.entity.Role;
import com.buiminhduc.model.respond.RoleResponse;
import com.buiminhduc.repository.RoleRepository;
import com.buiminhduc.service.RoleService;
import com.buiminhduc.util.ObjectUtil;

import java.lang.reflect.InvocationTargetException;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public RoleServiceImpl(){
        roleRepository = (RoleRepository) BeanFactory.getBeans().get("roleRepository");
    }
    @Override
    public RoleResponse findById(int id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ObjectNotFoundException {
        Role role = roleRepository.findById(id);

        if(role==null)
            throw new ObjectNotFoundException();

        RoleResponse roleResponse = new RoleResponse();

        ObjectUtil.copyProperties(role,roleResponse);

        return roleResponse;
    }
}
