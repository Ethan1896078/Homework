package team.t9001.saad.model;

/**
 * desc:用户实体类
 * Created by huangzhe on 2016/11/22.
 */
public class User {
    /** 用户编号 */
    private int userId;
    /** 用户昵称 */
    private String username;
    /** 用户类型 - 0：注册用户；1：超级管理员；2：管理员；3：实验室用户*/
    private int type;
    /** 状态 - 1：可用；0：不可用*/
    private int status;
    /** 登录账号 */
    private String account;
    /** 登录密码（MD5加密） */
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
        status = 1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
