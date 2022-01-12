package com.wuziqi.service;

import com.wuziqi.datasource.datasource2.model.User;
import com.wuziqi.datasource.datasource2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userRepository.findAll();
        if (null!=userList){
            return new org.springframework.security.core.userdetails.User(userList.get(0).getUsername(),
                    userList.get(0).getPassword(),
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
