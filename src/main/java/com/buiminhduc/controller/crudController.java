package com.buiminhduc.controller;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.repository.impl.UserRepositoryImpl;
import com.buiminhduc.service.UserService;
import com.buiminhduc.service.impl.UserServiceImpl;
import com.buiminhduc.util.ObjectUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/crudController")
public class crudController extends HttpServlet {
    private UserService userService;

    public crudController() {
        userService = (UserService) BeanFactory.getBeans().get("userService");
    }

    @lombok.SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext contextChucNang = getServletContext();
        String chucNang = (String) contextChucNang.getAttribute("chucNang");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        if (chucNang == null || chucNang.equals("")) {
            chucNang = req.getParameter("chucNang");
            int id = Integer.parseInt(req.getParameter("id"));
            new UserServiceImpl().delete(id);
            resp.sendRedirect("/admin");

        } else if (chucNang.equals("Sua")) {
            ServletContext contextID = getServletContext();
            String id = (String) contextID.getAttribute("id");
            int id1 = Integer.parseInt(id);
            UserEntity userEntity=new UserRepositoryImpl().findById(id1);
            String username = req.getParameter("username");
            String password = userEntity.getPassword();
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            int roleID = userEntity.getRoleId();
            UserRequest request = new UserRequest(username,password, phone, email, roleID);
            new UserServiceImpl().update(request, id1);
            resp.sendRedirect("/admin");

        } else if (chucNang.equals("Them")) {
            int roleId = Integer.parseInt(req.getParameter("roleId"));
            UserRequest userRequest= ObjectUtil.convertParameterU(req,roleId);
            new UserServiceImpl().insert(userRequest);
            resp.sendRedirect("/admin");
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
