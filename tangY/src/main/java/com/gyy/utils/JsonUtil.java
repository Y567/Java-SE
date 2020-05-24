package com.gyy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.pojo.ResultInfo;

public class JsonUtil {
    /**
     * 返回转换后的json字符串
     * @param info 传入的信息
     * @return     返回
     */
    public static String toJson(ResultInfo info) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //返回结果字符串
            return mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //一般不会走到这里
        return null;
    }
}
