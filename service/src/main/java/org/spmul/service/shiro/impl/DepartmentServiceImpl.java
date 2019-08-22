package org.spmul.service.shiro.impl;


import org.spmul.dao.shiro.DepartmentDao;
import org.spmul.entity.shiro.DepartmentEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spmul.common.base.BaseDao;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentEntity> implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public BaseDao<DepartmentEntity> getBaseDao() {
		return departmentDao;
	}
	
}
