package org.spmul.entity.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author   知秋
 * @email fei6751803@163.com
 */
@Data
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = -492977002385838369L;
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    
}
