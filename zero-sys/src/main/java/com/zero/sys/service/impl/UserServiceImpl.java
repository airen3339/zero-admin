package com.zero.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.sys.domain.Role;
import com.zero.sys.domain.User;
import com.zero.sys.domain.UserRole;
import com.zero.sys.mapper.RoleMapper;
import com.zero.sys.mapper.UserMapper;
import com.zero.sys.mapper.UserRoleMapper;
import com.zero.sys.property.UserProperties;
import com.zero.sys.security.jwt.util.JwtUtils;
import com.zero.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务逻辑层的实现类
 *
 * @author herenpeng
 * @since 2020-09-07 19:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public IPage<User> page(Integer currentPage, Integer size, User queryUser) throws Exception {
        Page page = new Page(currentPage, size);
        IPage<User> pageInfo = userMapper.getPage(page, queryUser);
        for (User user : pageInfo.getRecords()) {
            user.setRoles(roleMapper.getByUserId(user.getId()));
        }
        return pageInfo;
    }

    @Override
    public boolean save(User user) {
        String defaultPassword = userProperties.getDefaultPassword();
        String encodePassword = passwordEncoder.encode(defaultPassword);
        user.setPassword(encodePassword);
        return retBool(userMapper.insert(user));
    }

    @Override
    public void enabled(Integer id, Boolean enabled) throws Exception {
        User user = new User();
        user.setId(id);
        user.setEnabled(enabled);
        userMapper.updateById(user);
    }


    @Override
    public User info(String accessToken) throws Exception {
        User user = JwtUtils.getUserInfo(accessToken);
        return user;
    }

    @Override
    public void deleteUserRole(Integer userId, Integer roleId) throws Exception {
        userMapper.deleteUserRole(userId, roleId);
    }

    @Override
    public List<Role> getUserNotRoleList(Integer userId) throws Exception {
        return roleMapper.getUserNotRoleList(userId);
    }

    @Override
    public void addUserRole(Integer userId, Integer roleId) throws Exception {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);
    }


}
