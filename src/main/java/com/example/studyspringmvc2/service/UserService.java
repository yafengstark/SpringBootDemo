package com.example.studyspringmvc2.service;




import com.example.studyspringmvc2.user.User;

import java.util.List;

/**
 * @ClassName cn.saytime.service.UserService
 * @Description
 */
public interface UserService {

    User getUserById(Integer id);

    public List<User> getUserList();

    public int add(User user);

    public int update(Integer id, User user);

    public int delete(Integer id);
}

