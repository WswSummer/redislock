package com.wsw.redislock.service;

import com.wsw.redislock.entity.User;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 16:35 2020/9/14
 * @Description:
 */
public interface UserService {
    User findUserByUserName(String username);

    List<User> findUserByPassword(String password);

    void deleteItByUsername(String username);

    void save(User user);

    int updateUserByid(String username, Integer id);
}
