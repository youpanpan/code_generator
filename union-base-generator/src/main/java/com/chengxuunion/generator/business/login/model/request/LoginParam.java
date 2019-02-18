package com.chengxuunion.generator.business.login.model.request;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:22
 * @Modified By:
 */
public class LoginParam {

    /**
     * 用户名/邮箱
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

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
}
