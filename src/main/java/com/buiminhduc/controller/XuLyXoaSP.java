package com.buiminhduc.controller;

import com.buiminhduc.repository.WishlistRepository;
import com.buiminhduc.repository.impl.WhistlistRepositoryImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/XuLyXoaSP")
public class XuLyXoaSP extends HttpServlet {
    public XuLyXoaSP() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int maSP = Integer.parseInt(request.getParameter("maSanPham"));
			WishlistRepository wishlistRepository = new WhistlistRepositoryImpl();
			wishlistRepository.delete(maSP);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		response.sendRedirect("wishlist.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

}
