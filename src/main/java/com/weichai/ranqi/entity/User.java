package com.weichai.ranqi.entity;

public class User {

    // 用户属性
    private Long id; // 用户唯一标识
    private String name; // 用户姓名
    private String password; // 用户密码
    private String phone; // 用户电话
    private String permissions; // 用户权限

    // 构造方法
    public User() {}

    public User(Long id, String name, String password, String phone, String permissions) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.permissions = permissions;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    // toString 方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
