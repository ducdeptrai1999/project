package com.buiminhduc.common.constant;

public enum RoleConstant {
    ADMIN("admin"),
    USER("user");

    private String value;

    RoleConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
