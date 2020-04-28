package controller.dao.service;

import controller.pojo.Admin;
import controller.pojo.User;

/**
 * @author: Hps
 * @date: 2020/4/28 21:04
 * @description:
 */
public interface LoginDao {
    public User login(User loginUser);
    public Admin login(Admin loginAdmin);

}
