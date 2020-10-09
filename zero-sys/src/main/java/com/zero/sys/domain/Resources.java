package com.zero.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 系统资源实体类
 *
 * @author herenpeng
 * @since 2020-09-07 8:20
 */
@ApiModel(value = "系统资源实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_resources")
public class Resources extends BaseEntity {

    /**
     * 资源定位符
     */
    @ApiModelProperty(value = "资源定位符")
    @TableField(value = "uri", el = "uri")
    private String uri;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @TableField(value = "name", el = "name")
    private String name;

    /**
     * 资源描述信息
     */
    @ApiModelProperty(value = "资源描述信息")
    @TableField(value = "description", el = "description")
    private String description;

    /**
     * 拥有该资源的角色信息，非数据库字段
     */
    @ApiModelProperty(value = "拥有该资源的角色信息")
    @TableField(exist = false)
    private List<Role> roles;
}
