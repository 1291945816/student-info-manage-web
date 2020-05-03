package model.pojo;

import java.io.Serializable;

/**
 * @author: Hps
 * @date: 2020/4/20 21:45
 * @description:
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private String admin_username;
    private String admin_password;

    public Admin() {
    }

    public Admin(String admin_username, String admin_password) {
        this.admin_username = admin_username;
        this.admin_password = admin_password;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_username='" + admin_username + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }

}
