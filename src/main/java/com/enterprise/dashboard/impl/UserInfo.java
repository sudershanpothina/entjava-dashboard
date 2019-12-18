package com.enterprise.dashboard.impl;

import com.enterprise.dashboard.dao.GenericDao;
import com.enterprise.dashboard.model.Role;
import com.enterprise.dashboard.model.User;

import java.sql.Date;

public class UserInfo {
    public void updateUser(String id , String firstName, String lastName, String userName,
                            String password, Date sqlDate, String imageUrl,
                           String roleName, GenericDao userDao, GenericDao roleDao) {
        Role role = null;
        User user = (User) userDao.getById(Integer.parseInt(
                id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setDob(sqlDate);
        user.setImageUrl(imageUrl);

        if(user.getRole() != null) {
            role = user.getRole();
        } else {
            role = new Role();
        }
        role.setUser(user);
        role.setName(roleName);
        role.setUserName(user.getUserName());
        role.setUser(user);
        userDao.saveOrUpdate(user);
        roleDao.saveOrUpdate(role);
    }
    public void createUser(String firstName, String lastName, String userName, String password, Date sqlDate,
                           String teamId, String roleName, GenericDao userDao, GenericDao roleDao){
        User user = new User(firstName,
                lastName,
                userName,
                password,
                sqlDate,
                teamId);
        user.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJJHxTYMlVcuxOjif7Nu6LGy4Amfa8NXYhmS0MfBf0Ilnbm12j&s");

        Role role = new Role();
        role.setUserName(user.getUserName());
        role.setName(roleName);
        role.setUser(user);
        userDao.saveOrUpdate(user);
        roleDao.saveOrUpdate(role);
    }
}
