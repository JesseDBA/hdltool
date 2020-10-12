package com.hdl.hdltool.controller;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hdl.hdltool.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author jesse
 * @since 2020/10/11
 */

@RestController
public class ApiController {

//    @Autowired
//    private MyProps myProps;
//
//    @GetMapping("/app/cityPosition/getCityList")
//    public String getCityList() {
//
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("accessToken", "at.123456123456");
//        String result = "";
//        result = HttpUtil.post(myProps.getUrl() + "/app/cityPosition/getCityList", paramsMap);
//        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
//        System.out.println(jsonResult.toString());
//        return jsonResult.toString();
//    }
//
//    @GetMapping(value = "/app/v2/getStoreListByCityId",params = "cityId")
//    public String getStoreListByCityId(@RequestParam String cityId) {
//
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("cityId", cityId);
//
//        String paramsJson = JSON.toJSONString(paramsMap);
//        String result = "";
//
//        result = HttpRequest.post(myProps.getUrl() + "/app/v2/getStoreListByCityId")
//                .body(paramsJson)
//                .execute().body();
//        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
//        System.out.println(jsonResult.toString());
//        return jsonResult.toString();
//    }
//
//    @GetMapping(value = "/app/v2/getQueueStatus",params = "storeId")
//    public String getQueueStatus(@RequestParam String storeId) {
//
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("storeId", storeId);
//
//        String paramsJson = JSON.toJSONString(paramsMap);
//        String result = "";
//
//        result = HttpRequest.post(myProps.getUrl() + "/app/v2/getQueueStatus")
//                .body(paramsJson)
//                .execute().body();
//        com.alibaba.fastjson.JSONObject jsonResult = JSON.parseObject(result);
//        System.out.println(jsonResult.toString());
//        return jsonResult.toString();
////        JSONArray ja = jsonResult.getJSONArray("data");
////        for(int i = 0; i < ja.size(); i++){
////            com.alibaba.fastjson.JSONObject jo = ja.getJSONObject(i);
////            System.out.println(jo.getString("deviceSerial"));
////            if(deviceSerial.equals(jo.getString("deviceSerial"))){
////                return jo.getString("rtmp");
////            }
////        }
//
//    }

    @Autowired
    ApiService apiService;

    @RequestMapping("/getStore")
    public ModelAndView getStore() {
        return new ModelAndView("getStore.html");
    }

    @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
    public void getCityList(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, List<Map<Object, Object>>> cityList = apiService.getCityList();
        httpServletResponse.setHeader("Content-Encoding", "UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().print(JSON.toJSONString(MapUtil.sort(cityList)));
    }

    @PostMapping(value = "/getStoreListByCityId", produces = "application/json;charset=utf-8")
    public String getStoreListByCityId(@RequestBody JSONObject JSONcityId) {
        return apiService.getStoreListByCityId(JSONcityId.getString("cityId")).toString();
    }
}
