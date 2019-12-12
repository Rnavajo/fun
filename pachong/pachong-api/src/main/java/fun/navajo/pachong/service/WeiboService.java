package fun.navajo.pachong.service;

import fun.navajo.pachong.pojo.HotSpot;

import java.util.List;

/**
 * 新浪微博业务接口
 * @author oscar
 * @date 2019/12/11 9:02
 */
public interface WeiboService {
    /**
     * 爬取新浪微博热搜Top
     * @param limit 每次爬取数量
     * @return 热搜列表
     */
    List<HotSpot> catchWeiboTop(Integer limit);
}
