package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.util.PageUtils;
import com.blog.entity.operation.Tag;
import com.blog.entity.operation.vo.TagVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-21
 */
public interface TagService extends IService<Tag> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @param type
     * @return
     */
    List<Tag> listByLinkId(Integer linkId, Integer type);

    /**
     * 添加所属标签，包含新增标签
     *
     * @param tagList
     * @param linkId
     * @param type
     */
    void saveTagAndNew(List<Tag> tagList, Integer linkId, Integer type);

    /**
     * 删除tagLink关联
     *
     * @param linkId
     * @param type
     */
    void deleteTagLink(Integer linkId, Integer type);

    /**
     * 获取tagVoList
     *
     * @return
     */
    List<TagVO> listTagVo();

}
