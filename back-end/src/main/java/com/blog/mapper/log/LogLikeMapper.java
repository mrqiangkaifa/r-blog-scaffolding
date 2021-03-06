package com.blog.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.log.LogLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 阅读日志 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-02-15
 */
@Mapper
public interface LogLikeMapper extends BaseMapper<LogLike> {

    int getLikeNum();
}
