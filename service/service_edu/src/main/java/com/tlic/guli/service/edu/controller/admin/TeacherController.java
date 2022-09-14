package com.tlic.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlic.guli.common.base.result.R;
import com.tlic.guli.service.edu.entity.Teacher;
import com.tlic.guli.service.edu.entity.vo.TeacherQueryVo;
import com.tlic.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author T-lic
 * @since 2022-09-10
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 获取所有讲师列表
     *
     * @return 讲师列表
     */
    @GetMapping("list")
    @ApiOperation("所有讲师列表")
    public R listAll() {
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list);
    }

    /**
     * 根据id删除讲师(逻辑删除)
     *
     * @param id 讲师id
     * @return 是否成功删除，true删除成功，false删除失败
     */
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "根据id删除讲师", notes = "根据id删除讲师，逻辑删除")
    public R removeById(@ApiParam("讲师ID") @PathVariable String id) {
        boolean result = teacherService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    /**
     * 讲师分页列表
     *
     * @param page           当前页码
     * @param limit          每页条数
     * @param teacherQueryVo 讲师列表查询对象
     * @return
     */
    @ApiOperation("讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页条数", required = true) @PathVariable Long limit,
                      @ApiParam("讲师列表查询对象") TeacherQueryVo teacherQueryVo) {

        // 分页查询对象
        Page<Teacher> pageParam = new Page<>(page, limit);
        // 条件查询
        Page<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        // 全部记录
        List<Teacher> records = pageModel.getRecords();
        // 总条数
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    // @RequestBody代表参数是json格式
    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok().message("存储成功");
        } else {
            return R.ok().message("存储失败");
        }
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        Boolean result = teacherService.updateById(teacher);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "讲师对象",required = true) @PathVariable Integer id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null){
            return R.ok().data("item", teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }
}

