package org.spmul.service.impl;

import org.spmul.common.base.BaseDao;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseDao<T> {
	
	
	public abstract BaseDao<T> getBaseDao();

	@Override
	public void save(T t) {
		getBaseDao().save(t);
	}

	@Override
	public void save(Map<String, Object> map) {
		getBaseDao().save(map);
	}

	@Override
	public void saveBatch(List<T> list) {
		getBaseDao().saveBatch(list);
		
	}

	@Override
	public int update(T t) {
		return getBaseDao().update(t);
	}

	@Override
	public int update(Map<String, Object> map) {
		return getBaseDao().update(map);
	}

	@Override
	public int delete(Object id) {
		return getBaseDao().delete(id);
	}

	@Override
	public int delete(Map<String, Object> map) {
		return getBaseDao().delete(map);
	}

	@Override
	public int deleteBatch(Object[] id) {
		return getBaseDao().deleteBatch(id);
	}

	@Override
	public T queryObject(Object id) {
		return getBaseDao().queryObject(id);
	}

	@Override
	public List<T> queryList(Map<String, Object> map) {
		return getBaseDao().queryList(map);
	}

	@Override
	public List<T> queryList(Object id) {
		return getBaseDao().queryList(id);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return getBaseDao().queryTotal(map);
	}

	@Override
	public int queryTotal() {
		return getBaseDao().queryTotal();
	}

}
