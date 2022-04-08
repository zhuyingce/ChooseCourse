package com.wust.system.serrvice.impl;

import com.wust.system.dao.DepartmentDao;
import com.wust.system.entity.Department;
import com.wust.system.serrvice.DepartmentService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
@Transactional
@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Result getAll() {

        List<Department> departments = departmentDao.query();

        return Result.success("获取成功", departments);
    }
}
