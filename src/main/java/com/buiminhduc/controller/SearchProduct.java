package com.buiminhduc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category")
public class SearchProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("2")){
            req.setAttribute("Sql","select * from sanpham where idMenuCon=2;");
        }
        if (action.equals("3")){
            req.setAttribute("Sql","select * from sanpham where idMenuCon=2;");
        }
        if (action.equals("4")){
            req.setAttribute("Sql","select * from sanpham where idMenuCon=2;");
        }
        if (action.equals("5    ")){
            req.setAttribute("Sql","select * from sanpham where idMenuCon=2;");
        }
        resp.sendRedirect("/index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
