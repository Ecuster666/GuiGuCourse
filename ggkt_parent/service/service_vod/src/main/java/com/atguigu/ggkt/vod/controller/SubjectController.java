package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.Result.Result;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vod.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/admin/vod/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //课程分类列表
    //懒加载，每次查询一层数据
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable long id){
        List<Subject> list=subjectService.selectSubjectListById(id);
        return Result.ok(list);
    }
    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public Result exportData(HttpServletResponse response) {
        subjectService.exportData(response);
        return Result.ok();
    }
    @ApiOperation(value = "导入")
    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        subjectService.importDictData(file);
        return Result.ok();
    }

}

