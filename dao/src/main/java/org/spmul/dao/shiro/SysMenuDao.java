
package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author   知秋
 * @email fei6751803@163.com
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenuEntity> queryUserList(Long userId);

    /**
     * 查询管理员菜单，所有菜单
     * @return
     */
    List<SysMenuEntity> queryListAllMenu();

    /**
     * 查找某个用户id的菜单
     * @return
     */
    List<SysMenuEntity> queryUserListAllMenu(Long userId);
}
