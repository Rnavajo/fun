package fun.navajo.pachong.controller;

import fun.navajo.pachong.pojo.HotSpot;
import fun.navajo.pachong.service.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 微博controller
 * @author oscar
 * @date 2019/12/11 10:14
 */
@RestController
@RequestMapping("/weibo")
public class WeiboController {

    @Autowired
    private WeiboService weiboService;

    /**
     * 爬取微博热搜
     * @param limit 每次爬取数量
     * @return
     */
    @RequestMapping("/top/{limit}")
    public List<HotSpot> catchWeiboTop10 (@PathVariable Integer limit) {
        return weiboService.catchWeiboTop(limit);
    }
}
