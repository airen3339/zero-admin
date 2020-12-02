package com.zero.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zero.common.base.service.BaseService;
import com.zero.sys.entity.Role;
import com.zero.sys.entity.User;

import java.util.List;

/**
 * 用户业务逻辑层接口
 *
 * @author herenpeng
 * @since 2020-08-04 22:42
 */
public interface UserService extends BaseService<User> {

    /**
     * 分页查询用户数据
     *
     * @param currentPage 当前页面数，页面从1开始
     * @param size        当前页的大小，默认为10
     * @param queryUser   查询用户的条件
     * @return 分页查询所有的用户数据
     * @throws Exception 抛出异常
     */
    IPage<User> page(Integer currentPage, Integer size, User queryUser) throws Exception;

    /**
     * 查询所有的用户数据
     *
     * @param queryUser 查询用户的条件
     * @return 查询所有的用户数据
     * @throws Exception 抛出异常
     */
    List<User> list(User queryUser) throws Exception;

    /**
     * 启用或者禁用该用户账号
     *
     * @param id      用户id
     * @param enabled true为启用，false为禁用
     * @throws Exception 抛出异常
     */
    void enabled(Integer id, Boolean enabled) throws Exception;

    /**
     * 通过accessToken解析用户信息并返回
     *
     * @param accessToken cookie中的accessToken信息
     * @return 用户信息
     * @throws Exception 抛出异常
     */
    User info(String accessToken) throws Exception;

    /**
     * 删除用户的角色
     *
     * @param userId 用户主键
     * @param roleId 角色主键
     * @throws Exception
     */
    void deleteUserRole(Integer userId, Integer roleId) throws Exception;

    /**
     * 获取该用户没有的角色信息
     *
     * @param userId 用户主键
     * @return 用户未拥有的角色集合
     * @throws Exception 抛出异常
     */
    List<Role> getUserNotRoleList(Integer userId) throws Exception;

    /**
     * 添加用户角色关系
     *
     * @param userId 用户主键
     * @param roleId 角色主键
     * @throws Exception 抛出异常
     */
    void addUserRole(Integer userId, Integer roleId) throws Exception;

    /**
     * 分页查询逻辑删除的用户数据
     *
     * @param currentPage 当前页面数，页面从1开始
     * @param size        当前页的大小，默认为10
     * @param queryUser   查询用户的条件
     * @return 分页查询逻辑删除的用户数据
     * @throws Exception 抛出异常
     */
    IPage<User> recoverPage(Integer currentPage, Integer size, User queryUser) throws Exception;

    /**
     * 通过主键恢复逻辑删除的用户数据
     *
     * @param id 用户主键
     * @throws Exception 抛出异常
     */
    void recover(Integer id) throws Exception;

    /**
     * 回收站删除，通过用户主键彻底删除用户数据
     *
     * @param id 用户主键
     * @throws Exception 抛出异常
     */
    void recoverDelete(Integer id) throws Exception;


}
