package com.hdl.hdltool.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.hdl.hdltool.config.MyProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author jesse
 * @since 2020/10/11
 */

@RestController
public class ApiController {

    @Autowired
    private MyProps myProps;

    @GetMapping("/app/cityPosition/getCityList")
    public String getCityList() {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("accessToken", "at.123456123456");
        String result = "";
        result = HttpUtil.post(myProps.getUrl() + "/app/cityPosition/getCityList", paramsMap);
        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
        System.out.println(jsonResult.toString());
        return jsonResult.toString();
    }

    @GetMapping(value = "/app/v2/getStoreListByCityId",params = "cityId")
    public String getStoreListByCityId(@RequestParam String cityId) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cityId", cityId);

        String paramsJson = JSON.toJSONString(paramsMap);
        String result = "";

        result = HttpRequest.post(myProps.getUrl() + "/app/v2/getStoreListByCityId")
                .body(paramsJson)
                .execute().body();
        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
        System.out.println(jsonResult.toString());
        return jsonResult.toString();
    }

    @GetMapping(value = "/app/v2/getQueueStatus",params = "storeId")
    public String getQueueStatus(@RequestParam String storeId) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("storeId", storeId);

        String paramsJson = JSON.toJSONString(paramsMap);
        String result = "";

        result = HttpRequest.post(myProps.getUrl() + "/app/v2/getQueueStatus")
                .body(paramsJson)
                .execute().body();
        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
        System.out.println(jsonResult.toString());
        return jsonResult.toString();
//        JSONArray ja = jsonResult.getJSONArray("data");
//        for(int i = 0; i < ja.size(); i++){
//            com.alibaba.fastjson.JSONObject jo = ja.getJSONObject(i);
//            System.out.println(jo.getString("deviceSerial"));
//            if(deviceSerial.equals(jo.getString("deviceSerial"))){
//                return jo.getString("rtmp");
//            }
//        }

    }

}
