package com.buiminhduc.security;

import com.buiminhduc.common.constant.RoleConstant;
import com.buiminhduc.exception.ObjectNotFoundException;
import com.buiminhduc.model.respond.RoleResponse;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.service.RoleService;
import com.buiminhduc.service.impl.RoleServiceImpl;
import com.buiminhduc.service.session.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebFilter(urlPatterns = {"/admin", "/home", "/insertacc.jsp","/wishlist.jsp"})
public class AuthenticationFilter implements Filter {
    private RoleService roleService;

    public AuthenticationFilter(){
        roleService = new RoleServiceImpl();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserResponse userResponse = (UserResponse) SessionUtil.getSession(request,"USER");
        RoleResponse roleResponse = null;
        try {
            if (userResponse == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/error/404.jsp");
                rd.forward(request,response);
            } else {
                roleResponse = roleService.findById(userResponse.getRoleId());
                if (RoleConstant.ADMIN.getValue().equals(roleResponse.getName()) || RoleConstant.USER.getValue().equals(roleResponse.getName())) {
                    filterChain.doFilter(request,response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/error/404.jsp");
                    rd.forward(request,response);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
