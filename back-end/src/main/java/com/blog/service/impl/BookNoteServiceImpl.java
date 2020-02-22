package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.enums.ModuleEnum;
import com.blog.common.util.PageUtils;
import com.blog.common.util.Query;
import com.blog.entity.book.BookNote;
import com.blog.entity.book.dto.BookNoteDTO;
import com.blog.entity.book.vo.BookNoteVO;
import com.blog.entity.operation.Category;
import com.blog.mapper.book.BookNoteMapper;
import com.blog.service.BookNoteService;
import com.blog.service.BookService;
import com.blog.service.CategoryService;
import com.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * bookNoteAdminServiceImpl
 *
 * @author bobbi
 * @date 2018/11/21 12:48
 * @email 571002217@qq.com
 * @description
 */
@Service
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    /**
     * 分页查询笔记列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookNoteVO> page = new Query<BookNoteVO>(params).getPage();
        List<BookNoteVO> bookNoteList = baseMapper.listBookNoteVo(page, params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType, ModuleEnum.BOOK.getValue()));
        // 封装BookNoteVo
        Optional.ofNullable(bookNoteList).ifPresent((bookNoteVos ->
                bookNoteVos.forEach(bookNoteVo -> {
                    // 设置所属书本
                    bookNoteVo.setBook(bookService.getById(bookNoteVo.getBookId()));
                    // 设置类别
                    bookNoteVo.setCategoryListStr(categoryService.renderCategoryArr(bookNoteVo.getCategoryId(), categoryList));
                    // 设置标签列表
                    bookNoteVo.setTagList(tagService.listByLinkId(bookNoteVo.getId(), ModuleEnum.BOOK_NOTE.getValue()));
                })));
        page.setRecords(bookNoteList);
        return new PageUtils(page);
    }


    /**
     * 保存笔记笔记
     *
     * @param bookNote
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBookNote(BookNoteDTO bookNote) {
        baseMapper.insert(bookNote);
        tagService.saveTagAndNew(bookNote.getTagList(), bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
    }

    /**
     * 更新笔记
     *
     * @param bookNote
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBookNote(BookNoteDTO bookNote) {
        // 删除多对多所属标签
        tagService.deleteTagLink(bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
        // 更新所属标签
        tagService.saveTagAndNew(bookNote.getTagList(), bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
        // 更新笔记
        baseMapper.updateById(bookNote);
    }

    /**
     * 获取笔记对象
     *
     * @param bookNoteId
     * @return
     */
    @Override
    public BookNoteDTO getBookNote(Integer bookNoteId) {
        BookNoteDTO bookNoteDto = new BookNoteDTO();
        BookNote bookNote = this.baseMapper.selectById(bookNoteId);
        BeanUtils.copyProperties(bookNote, bookNoteDto);
        // 查询所属标签
        bookNoteDto.setTagList(tagService.listByLinkId(bookNoteId, ModuleEnum.BOOK_NOTE.getValue()));
        return bookNoteDto;
    }

    /**
     * 判断该类别下是否有笔记
     *
     * @param categoryId
     * @return
     */
    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }

    /**
     * 批量删除
     *
     * @param bookNoteIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] bookNoteIds) {
        //先删除笔记标签多对多关联
        Arrays.stream(bookNoteIds).forEach(bookNoteId -> {
            tagService.deleteTagLink(bookNoteId, ModuleEnum.BOOK_NOTE.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(bookNoteIds));
    }


    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookNoteVO> page = new Query<BookNoteVO>(params).getPage();
        List<BookNoteVO> bookNoteList = baseMapper.queryPageCondition(page, params);
        page.setRecords(bookNoteList);
        return new PageUtils(page);
    }

    /**
     * 获取简单对象
     *
     * @param bookNoteId
     * @return
     */
    @Override
    public BookNoteVO getSimpleBookNoteVo(Integer bookNoteId) {
        BookNoteVO bookNoteVo = baseMapper.getSimpleBookNoteVo(bookNoteId);
        bookNoteVo.setBook(bookService.getBookVo(bookNoteVo.getBookId()));
        return bookNoteVo;
    }

    /**
     * 获取简单List
     *
     * @param bookId
     * @return
     */
    @Override
    public List<BookNote> listSimpleBookNote(Integer bookId) {
        return baseMapper.listSimpleBookNote(bookId);
    }

}
