package com.gyy.service.impl;

import com.gyy.dao.PoetryMapper;
import com.gyy.pojo.Echart;
import com.gyy.pojo.ResultInfo;
import com.gyy.service.PoetryService;
import com.gyy.utils.JedisUtil;
import com.gyy.utils.JsonUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoetryServiceImpl implements PoetryService {

    private PoetryMapper poetryMapper;

    public void setPoetryMapper(PoetryMapper poetryMapper) {
        this.poetryMapper = poetryMapper;
    }

    @Override
    public String findByAmount(Map<String,Integer> map) {
        //1.创建响应对象
        ResultInfo info = new ResultInfo();
        //2.调用dao层
        List<Echart> byAmount = poetryMapper.findByAmount(map);
        //3.处理一下数据
        if(byAmount==null || byAmount.size() == 0){
            //说明没有数据
            info.setFlag(false);
            info.setReason("这个区间没有数据哦！");
        }else{
            info.setData(byAmount);
        }
        //5.转换为json对象
        return JsonUtil.toJson(info);
    }

    @Override
    public String findWordsByName(String name) {
        //1.响应对象
        ResultInfo info = new ResultInfo();
        //2.去查询（数据库或者redis）
        Jedis jedis = JedisUtil.getJedis();
        //需要用到的json字符串
        String json;
        if(jedis.get(name)==null){
            //3.去数据库中查
//            System.out.println("数据库查");
            List<String> wordsByName = poetryMapper.findWordsByName(name);
            //统计单词的个数
            Map<String, Integer> map = new HashMap<>();
            for (String s : wordsByName) {
                String[] words = s.split(" ");
                for (String word : words) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
            //处理数据
            if(map.size()==0){
                //没有数据
                info.setFlag(false);
                info.setReason("非常抱歉，查不到此诗人的数据");
                json = JsonUtil.toJson(info);
            }else{
                info.setData(map);
                json = JsonUtil.toJson(info);
                //存入redis数据库
                jedis.set(name,json);
            }
        }else{
            //4.存在数据去redis中查
            json = jedis.get(name);
        }
        //5.关掉连接
        JedisUtil.close(jedis);
        return json;
    }

    @Override
    public String findPoetByName(int i) {
        //1.创建info对象
        ResultInfo info = new ResultInfo();
        //2.去查询
        Jedis jedis = JedisUtil.getJedis();
        //需要用到的json字符串
        String json;
        if(jedis.get(String.valueOf(i))==null){
            //System.out.println("mysql中查诗人");
            //3.去数据库查
            List<String> names = poetryMapper.findPoetByName(i);
            if(names == null){
                info.setFlag(false);
                info.setReason("暂无数据");
                json = JsonUtil.toJson(info);
            }else{
                info.setData(names);
                json = JsonUtil.toJson(info);
                jedis.set(String.valueOf(i),json);
            }
        }else{
            //4.去redis中查
            json = jedis.get(String.valueOf(i));
        }
        //5.关闭资源
        JedisUtil.close(jedis);
        return json;
    }
}
