package com.buiminhduc.repository;

import com.buiminhduc.model.entity.WishListEntity;

public interface WishlistRepository extends JpaRepository<WishListEntity, Integer> {
    boolean kiemTraSanPhamCoTrongGioHangChua(int maSanPham);
}
