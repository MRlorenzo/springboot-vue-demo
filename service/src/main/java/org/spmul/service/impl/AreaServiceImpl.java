package org.spmul.service.impl;

import org.spmul.common.base.BaseDao;
import org.spmul.dao.AreaDao;
import org.spmul.entity.AreaEntity;
import org.spmul.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<AreaEntity> implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Override
	public BaseDao<AreaEntity> getBaseDao() {
		return areaDao;
	}
	
	@Override
	public void save(AreaEntity t) {
		t.setCreateTime(new Date());
		areaDao.save(t);
	}
	
}
