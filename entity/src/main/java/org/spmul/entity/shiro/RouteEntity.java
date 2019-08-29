package org.spmul.entity.shiro;

import lombok.Data;

import java.util.List;

@Data
public class RouteEntity {

    /*id*/
    private Long id;

    /*路径*/
    private String path;

    /*组件地址*/
    private String component;

    /*路由名称*/
    private String name;

    /*重定向地址*/
    private String redirect;

    /*将始终显示根菜单*/
    private Boolean alwaysShow;

    /*是否隐藏*/
    private Boolean hidden;

    /*不知道怎么解释，跟路由的配置有关（json）*/
    private String meta;

    /*父id*/
    private Long pid;

    /*排序字段*/
    private Integer sort;

    /*权限字符串*/
    private String perms;

    /*子项*/
    private List<RouteEntity> children;
}
