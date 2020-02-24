package com.buiminhduc.model.entity;

import com.buiminhduc.common.annotation.Column;
import com.buiminhduc.common.annotation.Entity;
import com.buiminhduc.common.annotation.Id;

@Entity(name = "sanpham")
public class SanPhamEntity {
    @Id(value = "maSp")
    private Integer id;
    @Column(value = "ten")
    private String tenSanPham;
    @Column(value = "hinhAnh")
    private String hinhAnh;
    @Column(value = "thongTinChiTiet")
    private String thongTinChiTiet;
    @Column(value = "giaBan")
    private Integer giaBan;
    @Column(value = "soLuong")
    private Integer soLuong;
    @Column(value = "trangThai")
    private String trangThai;
    @Column(value = "idMenuCon")
    private Integer idMenuCon;

    public SanPhamEntity() {
    }

    public SanPhamEntity(Integer id, String tenSanPham, String hinhAnh, String thongTinChiTiet, Integer giaBan, Integer soLuong, String trangThai, Integer idMenuCon) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.thongTinChiTiet = thongTinChiTiet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.idMenuCon = idMenuCon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getThongTinChiTiet() {
        return thongTinChiTiet;
    }

    public void setThongTinChiTiet(String thongTinChiTiet) {
        this.thongTinChiTiet = thongTinChiTiet;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getIdMenuCon() {
        return idMenuCon;
    }

    public void setIdMenuCon(Integer idMenuCon) {
        this.idMenuCon = idMenuCon;
    }

    @Override
    public String toString() {
        return "SanPhamEntity{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", thongTinChiTiet='" + thongTinChiTiet + '\'' +
                ", giaBan=" + giaBan +
                ", soLuong=" + soLuong +
                ", trangThai='" + trangThai + '\'' +
                ", idMenuCon=" + idMenuCon +
                '}';
    }
}
