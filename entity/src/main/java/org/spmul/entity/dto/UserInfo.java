package org.spmul.entity.dto;

import lombok.Data;
import org.spmul.entity.shiro.RouteEntity;

import java.util.List;

@Data
public class UserInfo {

    /*角色列表ids*/
    private List<Long> roleIds;

    // 名字
    private String name;

    // 角色列表
    private List<String> roles;

    // 描述
    private String introduction;

    // 路由列表
    private List<RouteEntity> routes;
}
