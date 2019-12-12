<template>
  <div>
    <div class="top">
      <select name="frequency-choice" v-model="frequency" @change="changeFrequency">
        <option :value="item" v-for="item in frequencyList" :key='item'>{{item}}</option>
      </select>
      每{{ frequency }}秒刷新一次，展示TOP
      <select name="limit-choice" v-model="limit" @change="changeLimit">
        <option :value="item" v-for="item in limitList" :key='item'>{{item}}</option>
      </select>
      <button v-on:click="switchRefleshStatus">
        <span v-if='reflesh'>停止刷新</span>
        <span v-else>开始刷新</span>
      </button>
    </div>
    <table width="50%">
      <thead>
        <td>排名</td>
        <td>标题</td>
        <td>热度</td>
        <td>状态</td>
      </thead>
      <tbody>
        <tr v-for='item in hotSpotList' :key='item.ranking'>
          <td>{{ item.ranking }}</td>
          <td><a :href=item.href target='_blank'>{{ item.title }}</a></td>
          <td>{{ item.heat }}</td>
          <td>{{ item.status }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import weiboApi from '../api/weiboApi'
export default {
  name: 'index',
  data () {
    return {
      // 刷新状态
      reflesh: true,
      // 刷新频率（单位：秒）
      frequency: 5,
      // 刷新频率列表
      frequencyList: [1, 5, 10, 20],
      // 爬取热搜条数
      limit: 10,
      // 条数列表
      limitList: [10, 20, 30, 40],
      // 定时器
      timer: '',
      // 热搜列表
      hotSpotList: []
    }
  },
  created () {
    this.catchInterval()
  },
  methods: {
    /**
     * 定时爬取新浪热搜
     */
    catchInterval () {
      this.catchWeiboTop()
      var interval = this.frequency * 1000
      clearInterval(this.timer) // 先清除旧的定时器
      this.timer = setInterval(this.catchWeiboTop, interval)
    },
    /**
     * 爬取微博热搜top
     */
    catchWeiboTop () {
      if (this.reflesh) {
        weiboApi.catchWeiboTop(this.limit).then(response => {
          this.hotSpotList = response.data
        })
      }
    },
    /**
     * 切换刷新状态
     */
    switchRefleshStatus () {
      this.reflesh = !this.reflesh
      if (this.reflesh) {
        console.log('停止刷新')
        this.catchInterval() // 执行刷新
      } else {
        console.log('停止刷新')
        clearInterval(this.timer) // 清除定时器
        this.timer = ''
      }
    },
    /**
     * 改变刷新频率
     */
    changeFrequency () {
      console.log('已更改为' + this.frequency + '秒刷新一次')
      if (this.reflesh) {
        this.catchInterval()
      }
    },
    /**
     * 改变每次爬取条数
     */
    changeLimit () {
      console.log('已更改为每次爬取' + this.limit + '条')
      if (this.reflesh) {
        this.catchInterval()
      }
    }
  }
}
</script>

<style type="text/css">
  .top {
    text-align:center;
  }
  table {
    border-collapse: collapse;
    margin: 10px auto;
    text-align: center;
  }
  table td, table th {
    border: 1px solid #cad9ea;
    color: #666;
    height: 30px;
  }
  table thead th {
    background-color: #CCE8EB;
    width: 100px;
  }
  table tr:nth-child(odd) {
    background: #fff;
  }
  table tr:nth-child(even) {
    background: #F5FAFA;
  }
  select, button {
    width:100px;
    height:30px;
    font-size:16px;
    font-family:Microsoft YaHei;
    border-radius: 5px;
    background-color: lightcyan
  }
</style>
