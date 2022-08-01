package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.Result.Result;
import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-01
 */
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    //点播课程列表
    @GetMapping("/getCourse/{page}/{limit}")
    public Result getCourse(@PathVariable long page,
                            @PathVariable long limit,
                            CourseQueryVo courseQueryVo){
        Page<Course> coursePage=new Page<>(page,limit);
        Map<String,Object> map=courseService.getPageCourse(coursePage,courseQueryVo);
        return Result.ok(map);
    }
    //添加课程
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }
    //根据id获取课程信息
    @GetMapping("/get/{id}")
    public Result get(@PathVariable long id){
        CourseFormVo courseFormVo=courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);
    }
    //修改课程信息
    @PutMapping("/update")
    public Result update(@RequestBody CourseFormVo vo){
        courseService.updateCourseById(vo);
        return Result.ok(vo.getId());
    }
    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        boolean result = courseService.publishCourseById(id);
        return Result.ok(result);
    }
    @ApiOperation(value = "删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        return Result.ok();
    }

}

