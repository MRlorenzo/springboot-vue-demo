package org.spmul.web.controller.pub;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.R;
import org.spmul.entity.shiro.DepartmentEntity;
import org.spmul.service.shiro.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public R addDep(@RequestBody DepartmentEntity departmentEntity){
        departmentService.save(departmentEntity);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateDep(@RequestBody DepartmentEntity departmentEntity){
        departmentService.update(departmentEntity);
        return R.ok();
    }

    @GetMapping("/del/{id}")
    @RequiresPermissions("department:del")
    public R delDep(@PathVariable("id") Long depId){
        departmentService.delete(depId);
        return R.ok();
    }
}
