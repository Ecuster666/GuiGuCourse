package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.Result.Result;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-07-29
 */
@RestController
@RequestMapping("/admin/vod/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //查询所有讲师列表
    @GetMapping("/findAll")
    public Result findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }

    //逻辑删除讲师
    @DeleteMapping("/removeTeacher/{id}")
    public Result removeTeacherById(@PathVariable long id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //条件查询分页列表
    @ApiOperation("条件分页查询")
    @PostMapping("/findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        return teacherService.findPage(teacherQueryVo, current, limit);

    }

    //添加讲师接口
    @ApiOperation("添加讲师")
    @PostMapping("/saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //修改讲师接口
    //根据id查询接口
    @ApiOperation("根据id查询")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@PathVariable long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }
    //修改
    @ApiOperation("修改讲师")
    @PutMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean update = teacherService.updateById(teacher);
        if (update){
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }
    //批量删除讲师
    @ApiOperation("批量删除讲师")
    @DeleteMapping("/removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList){
        boolean remove = teacherService.removeByIds(idList);
        if (remove){
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

}

