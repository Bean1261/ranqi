package com.weichai.ranqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.persistence.*;

@Entity
@Table(name = "user") // 对应数据库中的表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 与 MyBatis-Plus 的 @TableId 一致
    @TableId(type = IdType.AUTO) // MyBatis-Plus 注解，兼容
    private Long id; // 用户唯一标识
    private String username; // 用户姓名
    private String password; // 用户密码
    private String phone; // 用户电话
    private String permissions; // 用户权限

    // 构造方法
    public User() {}

    public User(Long id, String username, String password, String phone, String permissions) {
        this.id = id;
        this.username = username;
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

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
