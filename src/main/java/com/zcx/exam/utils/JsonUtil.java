package com.zcx.exam.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 需要转换的对象
     * @return 转换后的 JSON 字符串
     * @throws Exception 如果转换过程中发生错误
     */
    public static String toJson(Object obj) throws Exception {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toString();
        } else if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toString();
        } else {
            return objectMapper.writeValueAsString(obj);
        }
    }

    /**
     * 将 JSON 字符串解析为 JSONObject
     *
     * @param jsonString 需要解析的 JSON 字符串
     * @return 解析后的 JSONObject
     * @throws Exception 如果解析失败
     */
    public static JSONObject toJsonObject(String jsonString) throws Exception {
        return new JSONObject(jsonString);
    }

    /**
     * 将 JSON 字符串解析为 JSONArray
     *
     * @param jsonString 需要解析的 JSON 字符串
     * @return 解析后的 JSONArray
     * @throws Exception 如果解析失败
     */
    public static JSONArray toJsonArray(String jsonString) throws Exception {
        return new JSONArray(jsonString);
    }

    /**
     * 检查给定的字符串是否是有效的 JSON
     *
     * @param jsonString 需要检查的字符串
     * @return 如果是有效的 JSON 返回 true，否则返回 false
     */
    public static boolean isValidJson(String jsonString) {
        try {
            new JSONObject(jsonString);
            return true;
        } catch (Exception e) {
            try {
                new JSONArray(jsonString);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    /**
     * 从 JSON 对象中获取指定键的值
     *
     * @param jsonObject 包含数据的 JSON 对象
     * @param key       需要获取的键
     * @return 键对应的值
     * @throws Exception 如果键不存在或解析失败
     */
    public static Object getValueFromJson(JSONObject jsonObject, String key) throws Exception {
        if (jsonObject.has(key)) {
            return jsonObject.get(key);
        } else {
            throw new Exception("Key not found: " + key);
        }
    }

    /**
     * 从 JSON 数组中获取指定索引的 JSON 对象
     *
     * @param jsonArray 包含数据的 JSON 数组
     * @param index     需要获取的索引
     * @return 索引对应的 JSON 对象
     * @throws Exception 如果索引超出范围或解析失败
     */
    public static JSONObject getObjectFromJsonArray(JSONArray jsonArray, int index) throws Exception {
        if (index >= 0 && index < jsonArray.length()) {
            return jsonArray.getJSONObject(index);
        } else {
            throw new Exception("Index out of bounds: " + index);
        }
    }
}
