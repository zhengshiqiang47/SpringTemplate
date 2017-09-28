package com.dyfwz.service.impl;

import com.dyfwz.dao.BaseDAO;
import com.dyfwz.model.User;
import com.dyfwz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CoderQiang on 2017/9/28.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BaseDAO<User> userBaseDAO;

    @Override
    public int addUser(User user) {
        userBaseDAO.save(user);
        return 1;
    }

    @Override
    public User getUser(String userAccount) {
        return null;
    }
}
