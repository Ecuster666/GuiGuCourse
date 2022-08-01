package com.atguigu.ggkt.vod.service.impl;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vo.vod.VideoVo;
import com.atguigu.ggkt.vod.mapper.ChapterMapper;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-01
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private VideoService videoService;
    @Override
    public List<ChapterVo> getChapterList(long courseId) {
        List<ChapterVo> result=new ArrayList<>();
        //根据课程id获取章信息
        QueryWrapper<Chapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);
        //根据课程id获取课时信息
        LambdaQueryWrapper<Video> wrapperVideo=new LambdaQueryWrapper<>();
        wrapperVideo.eq(Video::getCourseId,courseId);
        List<Video> videoList = videoService.list(wrapperVideo);
        //封装章节
        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            result.add(chapterVo);

            List<VideoVo> videoList1=new ArrayList<>();
            for (Video video : videoList) {
                if (chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoList1.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList1);
        }
        return result;

    }

    @Override
    public void removeChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
