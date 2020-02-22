package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.util.PageUtils;
import com.blog.common.util.Query;
import com.blog.entity.operation.Link;
import com.blog.mapper.operation.LinkMapper;
import com.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-02-14
 */
@Service
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Link> page = baseMapper.selectPage(new Query<Link>(params).getPage(),
                new QueryWrapper<Link>().lambda().like(StringUtils.isNotEmpty(title), Link::getTitle, title));
        return new PageUtils(page);
    }

    /**
     * 获取link列表
     *
     * @return
     */
    @Override
    public List<Link> listLink() {
        return baseMapper.selectList(null);
    }

}
