package com.dyfwz.service;

import com.dyfwz.model.User;

/**
 * Created by CoderQiang on 2017/9/28.
 */
public interface LoginService {
    public int addUser(User user);
    public User getUser(String userAccount);
}
