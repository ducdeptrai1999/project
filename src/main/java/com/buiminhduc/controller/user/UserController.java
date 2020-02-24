package com.buiminhduc.controller.user;

import com.buiminhduc.autowire.BeanFactory;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.service.UserService;
import com.buiminhduc.service.session.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class UserController extends HttpServlet {
    private UserService userService;
    public UserController(){
        userService= (UserService) BeanFactory.getBeans().get("userService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("MODEL",((UserResponse)SessionUtil.getSession(req,"USER")));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/homepage/user.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
    }
}
