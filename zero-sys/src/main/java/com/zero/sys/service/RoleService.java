package com.zero.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zero.common.base.service.BaseService;
import com.zero.sys.entity.Role;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户角色的业务逻辑层接口
 *
 * @author herenpeng
 * @since 2020-09-14 20:28
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 分页查询角色信息
     *
     * @param currentPage 当前页码
     * @param size        每页大小
     * @param queryRole   角色查询条件
     * @return
     * @throws Exception 抛出异常
     */
    IPage<Role> page(Integer currentPage, Integer size, Role queryRole) throws Exception;

    /**
     * 获取所有的用户角色信息
     *
     * @param queryRole 角色查询条件
     * @return
     * @throws Exception 抛出异常
     */
    List<Role> list(Role queryRole) throws Exception;

    /**
     * 分页查询逻辑删除的系统角色表数据
     *
     * @param currentPage 当前页面数，页面从1开始
     * @param size        当前页的大小，默认为10
     * @param queryRole   查询系统角色表的条件
     * @return 分页查询逻辑删除的系统角色表数据
     * @throws Exception 抛出异常
     */
    IPage<Role> recoverPage(Integer currentPage, Integer size, Role queryRole) throws Exception;

    /**
     * 通过主键恢复逻辑删除的系统角色表数据
     *
     * @param id 系统角色表主键
     * @throws Exception 抛出异常
     */
    void recover(Integer id) throws Exception;

    /**
     * 回收站删除，通过系统角色表主键彻底删除系统角色表数据
     *
     * @param id 系统角色表主键
     * @throws Exception 抛出异常
     */
    void recoverDelete(Integer id) throws Exception;


    /**
     * 导出角色列表数据的Excel文件
     *
     * @param queryRole 查询角色的条件
     * @param response  HttpServletResponse对象
     * @throws Exception 抛出异常
     */
    void exportExcel(Role queryRole, HttpServletResponse response) throws Exception;
}
