package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.Result.Result;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vod.service.TeacherService;
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
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    //查询所有讲师列表
    @GetMapping("/findAll")
    public Result findAllTeacher(){
        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }
    //逻辑删除讲师
    @DeleteMapping("/removeTeacher/{id}")
    public Result removeTeacherById(@PathVariable long id){
        boolean b = teacherService.removeById(id);
        if (b){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

}

