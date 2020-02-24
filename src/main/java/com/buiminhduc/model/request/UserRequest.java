package com.buiminhduc.model.request;


public class UserRequest {
    private String userName;
    private String password;
    private String phone;
    private String email;
    private int roleId;

    public UserRequest(String userName, String password, String phone, String email, int roleId) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.roleId = roleId;
    }

    public UserRequest(String password, String phone, String email) {
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
