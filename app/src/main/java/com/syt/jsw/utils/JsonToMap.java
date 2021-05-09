package com.syt.jsw.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用Gson把json字符串转成Map
 */
public class JsonToMap {
    /**
     * 根据json字符串返回Map对象中的List集合
     *
     * @param json
     * @return
     */
    public static <T> List<T> toMapList(String key, String json, Class<T> mClass) {
        List<T> list = new ArrayList<>();
        Map<String, List> classMap = NewJsonUtils.fromJson(json, Map.class);
        List list2 = classMap.get(key);
        for (int i = 0; i < list2.size(); i++) {
            String userStr = NewJsonUtils.toJson(list2.get(i), false);
            T cl = NewJsonUtils.fromJson(userStr, mClass);
            list.add(cl);
        }
        return list;
    }

    /**
     * 根据json字符串返回List对象
     *
     * @param json
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> mClass) {
        List<T> list = new ArrayList<>();
        List personList = NewJsonUtils.fromJson(json, List.class);
        list.clear();
        for (int i = 0; i < personList.size(); i++) {
            String userStr = NewJsonUtils.toJson(personList.get(i), false);
            T cl = NewJsonUtils.fromJson(userStr, mClass);
            list.add(cl);
        }
        return list;
    }

    /**
     * 根据json字符串返回List对象
     *
     * @param json
     * @return
     */
    public static <T> T toOgject(String json, Class<T> mClass) {
        return NewJsonUtils.fromJson(json, mClass);
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> String listToJson(List<T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * 将对象转化为Json
     *
     * @param
     * @return String
     */
    public static String objToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }


    /**
     * 根据json字符串返回Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        return JsonToMap.toMap(JsonToMap.parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonArray)
                map.put((String) key, toList((JsonArray) value));
            else if (value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else
                map.put((String) key, value);
        }
        return map;
    }


    /**
     * 将JSONArray对象转换成List集合
     *
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < json.size(); i++) {
            Object value = json.get(i);
            if (value instanceof JsonArray) {
                list.add(toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                list.add(toMap((JsonObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }


    /**
     * 获取JsonObject
     *
     * @param json
     * @return
     */
    public static JsonObject parseJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }

}