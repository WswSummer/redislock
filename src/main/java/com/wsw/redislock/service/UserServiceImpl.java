package com.wsw.redislock.service;

import com.wsw.redislock.entity.User;
import com.wsw.redislock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 16:40 2020/9/14
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    @Override
    public List<User> findUserByPassword(String password) {
        List<User> users = userRepository.findUserByPassword(password);
        return users;
    }

    @Override
    public void deleteItByUsername(String username) {
        userRepository.deleteItByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public int updateUserByid(String username, Integer id) {
        int i = userRepository.updateUserByid(username, id);
        return i;
    }

}
