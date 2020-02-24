package com.buiminhduc.controller.user;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.model.request.UserRequest;
import com.buiminhduc.paging.PageRequest;
import com.buiminhduc.repository.impl.UserRepositoryImpl;
import com.buiminhduc.service.UserService;
import com.buiminhduc.service.impl.UserServiceImpl;
import com.buiminhduc.util.FormUtil;
import com.buiminhduc.util.MySqlConnectionUtil;
import com.buiminhduc.util.ObjectUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            UserRequest userRequest= ObjectUtil.convertParameterU(req,2);
            new UserServiceImpl().insert(userRequest);
            resp.sendRedirect("/login");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
