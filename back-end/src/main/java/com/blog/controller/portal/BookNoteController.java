package com.blog.controller.portal;

import com.blog.common.Result;
import com.blog.common.annotation.LogLike;
import com.blog.common.annotation.LogView;
import com.blog.common.constants.RedisCacheNames;
import com.blog.common.util.PageUtils;
import com.blog.entity.book.BookNote;
import com.blog.service.BookNoteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * BookNoteNoteAdminController
 *
 * @author bobbi
 * @date 2018/11/20 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController("bookNotePortalController")
@CacheConfig(cacheNames = {RedisCacheNames.BOOKNOTE})
public class BookNoteController {

    @Resource
    private BookNoteService bookNoteService;


    @GetMapping("/bookNote/{bookNoteId}")
    @LogView(type = "bookNote")
    public Result getBookNote(@PathVariable Integer bookNoteId) {
        BookNote bookNote = bookNoteService.getById(bookNoteId);
        return Result.ok().put("bookNote", bookNote);
    }

    @GetMapping("/bookNotes")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page", page);
    }

    @PutMapping("/bookNote/like/{id}")
    @LogLike(type = "bookNote")
    public Result likeBookNote(@PathVariable Integer id) {
        return Result.ok();
    }


}
