package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.util.PageUtils;
import com.blog.entity.article.Article;
import com.blog.entity.article.dto.ArticleDTO;
import com.blog.entity.article.vo.ArticleVO;

import java.util.List;
import java.util.Map;

/**
 * ArticleService
 *
 * @author bobbi
 * @date 2018/11/21 12:47
 * @email 571002217@qq.com
 * @description
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存博文文章
     *
     * @param blogArticle
     */
    void saveArticle(ArticleDTO blogArticle);

    /**
     * 批量删除
     *
     * @param articleIds
     */
    void deleteBatch(Integer[] articleIds);

    /**
     * 更新博文
     *
     * @param blogArticle
     */
    void updateArticle(ArticleDTO blogArticle);

    /**
     * 获取文章对象
     *
     * @param articleId
     * @return
     */
    ArticleDTO getArticle(Integer articleId);


    boolean checkByCategory(Integer id);


    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取ArticleVo对象
     *
     * @param articleId
     * @return
     */
    ArticleVO getArticleVo(Integer articleId);

    /**
     * 获取简单的Article对象
     *
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVo(Integer articleId);

    List<Article> search(String keyword);
}
