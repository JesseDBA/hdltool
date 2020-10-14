package com.hdl.hdltool.service;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdl.hdltool.config.MyProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author jesse
 * @since 2020/10/12
 */

@Service(value = "apiService")
public class ApiService {

    @Autowired
    private MyProps myProps;

    public Map<String, List<Map<Object, Object>>> getCityList() {
        Map<String, List<Map<Object, Object>>> cityMap = MapUtil.newHashMap(true);
        String result = HttpUtil.post(myProps.getUrl() + "/app/cityPosition/getCityList", "");
        JSONObject jsonResult = JSON.parseObject(result);
        JSONArray cityListJSONArray = jsonResult.getJSONObject("data").getJSONArray("cityList");
        for (Object o : cityListJSONArray) {
            JSONObject cityJSON = (JSONObject) o;
            String firstLetter = cityJSON.getString("py").substring(0, 1).toUpperCase();
            Map<Object, Object> city = MapUtil.of(new String[][]{{"cityId", cityJSON.getString("cityId")}
                    , {"cityName", cityJSON.getString("cityName")}
                    , {"py", cityJSON.getString("py")}});
            if (cityMap.containsKey(firstLetter)) {
                List cityList = cityMap.get(firstLetter);
                cityList.add(city);
                cityMap.put(firstLetter, cityList);
            } else {
                cityMap.put(firstLetter, ListUtil.toLinkedList(city));
            }
        }
        return cityMap;
    }

    public JSONArray getStoreListByCityId(String cityId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cityId", cityId);
        String paramsJson = JSON.toJSONString(paramsMap);
        String result = HttpRequest.post(myProps.getUrl() + "/app/v2/getStoreListByCityId")
                                   .body(paramsJson)
                                   .execute().body();
        return JSON.parseObject(result).getJSONObject("data").getJSONArray("storeList");
    }

    public JSONObject getStoreStatusByStoreId(String storeId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("storeId", storeId);
        String paramsJson = JSON.toJSONString(paramsMap);
        String result = HttpRequest.post(myProps.getUrl() + "/app/v2/getQueueStatus")
                .body(paramsJson)
                .execute().body();
        return JSON.parseObject(result).getJSONObject("data");
    }
}
