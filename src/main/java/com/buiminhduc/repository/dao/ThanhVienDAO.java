package com.buiminhduc.repository.dao;

import com.buiminhduc.model.entity.UserEntity;
import com.buiminhduc.util.MySqlConnectionUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class ThanhVienDAO implements ObjectDAO{
	public static ArrayList<UserEntity> dsThanhVien = new ArrayList<>();
	public static Set<String > dsHoThanhVien = new TreeSet<>();
	public ThanhVienDAO(){
		dsThanhVien.removeAll(dsThanhVien);
	}
	@Override
	public ArrayList<UserEntity> getDanhSach() {
		ResultSet rs;
		try {
			rs = new MySqlConnectionUtil().chonDuLieuTuDTB("select * from user");
			while(rs.next()){
				dsThanhVien.add(new UserEntity(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return dsThanhVien;
	}
	@Override
	public Set<String> getDanhSachTheoHo() {
		ResultSet rs;
		try {
			rs = new MySqlConnectionUtil().chonDuLieuTuDTB("select * from user");
			while(rs.next()){
				dsHoThanhVien.add(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return dsHoThanhVien;
	}
	public ArrayList<UserEntity> locDanhSach(String user_name){
		ArrayList<UserEntity> ds = new ArrayList<>();
		ResultSet rs;
		try {
			rs = new MySqlConnectionUtil().chonDuLieuTuDTB("select * from user where user_name='"+user_name+"'");
			while(rs.next()){
				ds.add(new UserEntity(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return ds;
	}
}
