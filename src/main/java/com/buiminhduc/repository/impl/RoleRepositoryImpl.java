package com.buiminhduc.repository.impl;

import com.buiminhduc.common.annotation.Repository;
import com.buiminhduc.model.entity.Role;
import com.buiminhduc.repository.RoleRepository;

@Repository
public class RoleRepositoryImpl extends BasicQuery<Role, Integer> implements RoleRepository {
}
