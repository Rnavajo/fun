package fun.navajo.pachong.service.impl;

import fun.navajo.pachong.pojo.HotSpot;
import fun.navajo.pachong.service.WeiboService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 新浪微博业务接口实现类
 * @author oscar
 * @date 2019/12/11 9:07
 */
@Service
public class WeiboServiceImpl implements WeiboService {

    /**
     * 爬取新浪微博热搜Top
     * @param limit 每次爬取数量
     * @return 热搜列表
     */
    @Override
    public List<HotSpot> catchWeiboTop(Integer limit) {
        // 微博热搜页面地址
        String weiboIp = "https://s.weibo.com";
        String url = "https://s.weibo.com/top/summary?cate=realtimehot";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("爬取微博热搜失败！");
            e.printStackTrace();
        }
        List<HotSpot> hotSpotList = new LinkedList<>();
        if (doc != null) {
            Elements trs = doc.getElementsByTag("tr");
            // 热搜排名
            int ranking = 0;
            for (int i=0; i<trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");
                // 热搜标题
                String title = "";
                // 热搜热度
                String heat = "";
                // 热搜链接
                String href = "";
                // 热搜状态
                String status = "";
                for (int j=0; j<tds.size() && ranking<=limit; j++) {
                    Element td = tds.get(j);
                    // <td class="td-02">为热搜内容
                    if (td.attr("class").equals("td-02")) {
                        title = td.getElementsByTag("a").text();
                        heat = td.getElementsByTag("span").text();
                        // 处理状态为"荐"的热搜的特殊链接
                        String aHref = td.getElementsByTag("a").attr("href");
                        if (aHref.equals("javascript:void(0);")) {
                            aHref = td.getElementsByTag("a").attr("href_to");
                        }
                        href = weiboIp + aHref;
                    }
                    // <td class="td-03">为热搜状态（爆/沸/新/荐）
                    if (td.attr("class").equals("td-03")) {
                        status = td.getElementsByTag("i").text();
                    }
                }
                if (!title.equals("")) {
                    HotSpot hotSpot = new HotSpot(ranking, title, heat, href, status);
                    hotSpotList.add(hotSpot);
                    ranking++;
                }
            }
        }
        if (hotSpotList.isEmpty()) {
            System.out.println("没有爬取到任何内容！");
        } else {
            String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            System.out.println(dateTime + " : 爬取到" + limit + "条微博热搜");
        }
        return hotSpotList;
    }
}
