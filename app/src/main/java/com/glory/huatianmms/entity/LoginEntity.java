package com.glory.huatianmms.entity;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc 登录实体类
 */
public class LoginEntity {

    public boolean isLOGINRESULT() {
        return LOGINRESULT;
    }

    public void setLOGINRESULT(boolean LOGINRESULT) {
        this.LOGINRESULT = LOGINRESULT;
    }

    public UserEntity getUSER() {
        return USER == null ? new UserEntity() : USER;
    }

    public void setUSER(UserEntity USER) {
        this.USER = USER;
    }

    private boolean LOGINRESULT;

    private UserEntity USER;
}
