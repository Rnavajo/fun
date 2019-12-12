package fun.navajo.pachong.pojo;

/**
 * 新浪微博热搜实体类
 * @author oscar
 * @date 2019/12/11 9:14
 */
public class HotSpot {
    /**
     * 排名
     */
    private Integer ranking;
    /**
     * 标题
     */
    private String title;
    /**
     * 热度
     */
    private String heat;
    /**
     * 链接
     */
    private String href;

    /**
     * 状态（爆/沸/新/荐）
     */
    private String status;

    public HotSpot () {}

    public HotSpot(Integer ranking, String title, String heat, String href, String status) {
        this.ranking = ranking;
        this.title = title;
        this.heat = heat;
        this.href = href;
        this.status = status;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
