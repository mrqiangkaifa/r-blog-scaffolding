package com.blog.service;

import com.blog.entity.timeline.Timeline;

import java.util.List;

/**
 * TimeLineService
 *
 * @author bobbi
 * @date 2019/02/24 20:47
 * @email 571002217@qq.com
 * @description
 */
public interface TimelineService {

    /**
     * 获取timeLine数据
     *
     * @return
     */
    List<Timeline> listTimeLine();
}
