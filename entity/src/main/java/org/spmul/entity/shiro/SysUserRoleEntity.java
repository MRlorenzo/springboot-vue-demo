package org.spmul.entity.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author   知秋
 * @email fei6751803@163.com
 */
@Data
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = -1016205852225935441L;
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    
    
}
