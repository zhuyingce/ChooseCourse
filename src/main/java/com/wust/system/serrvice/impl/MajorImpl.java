package com.wust.system.serrvice.impl;

import com.wust.system.dao.MajorDao;
import com.wust.system.entity.Major;
import com.wust.system.serrvice.MajorService;
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
@Service
@Transactional
public class MajorImpl implements MajorService {

    @Autowired
    private MajorDao majorDao;

    @Override
    public Result getByDeptId(Integer deptId) {

        List<Major> majors = majorDao.queryByDeptId(deptId);

        return Result.success("获取成功", majors);
    }
}
