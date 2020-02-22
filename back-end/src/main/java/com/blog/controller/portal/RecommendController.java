package com.blog.controller.portal;

import com.blog.common.Result;
import com.blog.entity.operation.vo.RecommendVO;
import com.blog.service.RecommendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Recommend
 *
 * @author bobbi
 * @date 2019/02/22 13:40
 * @email 571002217@qq.com
 * @description
 */
@RestController("recommendPortalController")
@RequestMapping("/operation")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @RequestMapping("/recommends")
    public Result listRecommend() {
        List<RecommendVO> recommendList = recommendService.listRecommendVo();
        return Result.ok().put("recommendList", recommendList);
    }

    @RequestMapping("/hotReads")
    public Result listHotRead() {
        List<RecommendVO> hotReadList = recommendService.listHotRead();
        return Result.ok().put("hotReadList", hotReadList);
    }
}
