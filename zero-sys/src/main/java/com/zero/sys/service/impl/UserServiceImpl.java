package com.zero.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.sys.domain.User;
import com.zero.sys.mapper.RoleMapper;
import com.zero.sys.mapper.UserMapper;
import com.zero.sys.property.UserProperties;
import com.zero.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务逻辑层的实现类
 *
 * @author herenpeng
 * @since 2020-09-07 19:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserProperties userProperties;

    @Override
    public IPage<User> page(Integer currentPage, Integer size) throws Exception {
        Page page = new Page(currentPage, size);
        IPage<User> pageInfo = userMapper.selectPage(page, null);
        for (User user : pageInfo.getRecords()) {
            user.setRoles(roleMapper.getByUserId(user.getId()));
        }
        return pageInfo;
    }

    @Override
    public void enabled(Integer id, Boolean enabled) throws Exception {
        User user = new User();
        user.setId(id);
        user.setEnabled(enabled);
        userMapper.updateById(user);
    }

    @Override
    public void insert(User user) throws Exception {
        String defaultPassword = userProperties.getDefaultPassword();
        String encodePassword = passwordEncoder.encode(defaultPassword);
        user.setPassword(encodePassword);
        userMapper.insert(user);
    }

    @Override
    public void delete(Integer id) throws Exception {
        userMapper.deleteById(id);
    }


}
