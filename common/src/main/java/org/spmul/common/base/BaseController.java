package org.spmul.common.base;


import org.spmul.common.util.PageUtils;
import org.spmul.common.util.Query;
import org.spmul.common.util.R;

import java.util.List;
import java.util.Map;


/**
 * 通用控制器
 * @param <T>
 */
public abstract class BaseController<T> {

    /**
     * 导出excel默认后缀
     */
    protected final static String LAST_FILE_NAME = ".xls";

    protected abstract BaseDao<T> getBaseService();

    /**
     * 数据分页
     * @param params
     * @return R
     */
    protected R list(Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<T> list = getBaseService().queryList(query);
        return list(query, list);
    }

    /**
     * 数据分页
     * @param query
     * @param list
     * @return R
     */
    protected R list( Query query, List<T> list){
        //查询列表数据
        int total = getBaseService().queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

}
