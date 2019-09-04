package org.spmul.entity.shiro;

import lombok.Data;

import java.util.List;

@Data
public class RouteEntity {

    /*id*/
    private Long id;

    /*路径*/
    private String path;

    /*父id*/
    private Long pid;

    /*排序字段*/
    private Integer sort;

    /*权限字符串*/
    private String perms;

    /*描述*/
    private String description;

    /*子项*/
    private List<RouteEntity> children;
}
