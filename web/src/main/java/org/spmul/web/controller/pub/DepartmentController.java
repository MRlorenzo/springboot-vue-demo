package org.spmul.web.controller.pub;

import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.R;
import org.spmul.entity.shiro.DepartmentEntity;
import org.spmul.service.shiro.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController<DepartmentEntity>{

    @Autowired
    private DepartmentService departmentService;

    @Override
    protected BaseDao<DepartmentEntity> getBaseService() {
        return departmentService;
    }

    @GetMapping("/departments")
    public R departments(){
        return R.ok().put("list" , getBaseService().queryList(null));
    }
}
