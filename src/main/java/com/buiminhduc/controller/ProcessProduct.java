package com.buiminhduc.controller;

import com.buiminhduc.model.entity.WishListEntity;
import com.buiminhduc.model.respond.UserResponse;
import com.buiminhduc.repository.WishlistRepository;
import com.buiminhduc.repository.impl.WhistlistRepositoryImpl;
import com.buiminhduc.service.session.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/XuLyMuaSanPham")
public class ProcessProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int maSP  = Integer.parseInt(req.getParameter("maSanPham"));
        UserResponse userResponse = (UserResponse) SessionUtil.getSession(req,"USER");
        WishlistRepository wishlistRepository = new WhistlistRepositoryImpl();
        int userId = userResponse.getId();
        WishListEntity wishListEntity = new WishListEntity(maSP,1,userId);
        if (userResponse != null){
            if(wishlistRepository.kiemTraSanPhamCoTrongGioHangChua(maSP)){
                try {
                    wishListEntity.setSoLuongBan(wishListEntity.getSoLuongBan()+1);
                    wishlistRepository.update(wishListEntity,maSP);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    wishlistRepository.insert(wishListEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/wishlist.jsp");
        }else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
