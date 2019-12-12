import Ajax from 'axios'
/**
 * 微博相关api
 */
const weiboApi = {
  /**
   * 爬取微博热搜
   * @param {*} limit 每次爬取条数
   */
  catchWeiboTop (limit) {
    return Ajax({
      url: `/api/weibo/top/` + limit,
      method: 'get'
    })
  }
}

export default weiboApi
