package com.wuziqi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuziqi.mapper.UserMapper;
import com.wuziqi.model.ChessRecord;
import com.wuziqi.model.USER;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<USER> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("username",username);
        List<USER> userList = userMapper.selectList(queryWrapper);
        if (null!=userList){
            return new User(userList.get(0).getUsername(),
                    userList.get(0).getPassword(),
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
