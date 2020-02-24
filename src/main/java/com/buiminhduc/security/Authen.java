package com.buiminhduc.security;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.common.constant.RoleConstant;
import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.request.Auth;
import com.buiminhduc.model.respond.RoleResponse;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.service.RoleService;
import com.buiminhduc.service.UserService;
import com.buiminhduc.service.session.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Authen {
    private UserService userService;
    private RoleService roleService;

    public Authen(){
        userService = (UserService) BeanFactory.getBeans().get("userService");
        this.roleService = (RoleService) BeanFactory.getBeans().get("roleService");
    }
    public static Authen of(){
        return new Authen();
    }
    public String auth(Auth auth, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, ObjectNotFoundException, InstantiationException, SQLException, NoSuchMethodException, NoSuchFieldException {
        UserResponse userResponse = userService.findUserByUserNameAndPassWord(auth);
        SessionUtil.setSession(request, "USER", userResponse);

        String url=null ;
        RoleResponse roleResponse = roleService.findById(userResponse.getRoleId());
        if(RoleConstant.ADMIN.getValue().equals(roleResponse.getName())){
            url="/admin";
        }else if(RoleConstant.USER.getValue().equals(roleResponse.getName())){
            url="/home";
        }
        return url;
    }


}
