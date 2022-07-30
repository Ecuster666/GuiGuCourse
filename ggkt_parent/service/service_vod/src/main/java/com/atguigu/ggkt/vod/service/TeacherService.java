package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.Result.Result;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-07-29
 */
public interface TeacherService extends IService<Teacher> {

    Result findPage(TeacherQueryVo teacherQueryVo, long current, long limit);
}
