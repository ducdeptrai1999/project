package com.buiminhduc.controller;

import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.repository.dao.ThanhVienDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/XuLyUser")
public class TimKiemUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String userName = req.getParameter("userName");
        HttpSession session = req.getSession();
        if(userName.equals("")||userName==null){
            ArrayList<UserEntity> dsLoc = new ThanhVienDAO().getDanhSach();
            session.setAttribute("dsLoc", dsLoc);
            resp.sendRedirect("/admin");
        }else{
            ArrayList<UserEntity> dsLoc = new ThanhVienDAO().locDanhSach(userName);
            session.setAttribute("dsLoc", dsLoc);
            resp.sendRedirect("/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
