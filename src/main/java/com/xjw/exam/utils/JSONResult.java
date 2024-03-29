package com.xjw.exam.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


/**
 *
 * @Title: JSONResult.java
 * @Description:   自定义响应数据结构，将JSON数据封装到一个对象中
 *                 这个类是提供给门户，ios，安卓，微信商城用的
 *                 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 *                 其他自行处理
 *                 200：表示成功
 *                 500：表示错误，错误信息在msg字段中
 *                 501：bean验证错误，不管多少个错误都以map形式返回
 *                 502：拦截器拦截到用户token出错
 *                 555：异常抛出信息
 */
public class JSONResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    // json自带ok参数
    @JsonIgnore
    private String ok;

    // 執行操作成功
    private boolean success;

    public JSONResult() {

    }

    // 构造函数
    public JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.success = (status == 200?true:false);
    }

    // 构造函数
    public JSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
        this.success = true;
    }

    public static JSONResult build(Integer status, String msg, Object data) {
        return new JSONResult(status, msg, data);
    }

    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    public static JSONResult ok() {
        return new JSONResult(null);  //data为空进行构造
    }

    public static JSONResult noAccess(Object data) {
        return new JSONResult(403, "抱歉，您没有权限访问该页面！", data);
    }

    public static JSONResult errorMsg(String msg) {
        return new JSONResult(500, msg, null); //500：表示错误，错误信息在msg字段中
    }

    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "error", data);  //501：bean验证错误
    }

    public static JSONResult errorTokenMsg(String msg) {
        return new JSONResult(502, msg, null);  //502：拦截器拦截到用户token出错
    }

    public static JSONResult unlogin() {
        return new JSONResult(505,"未登陆", null);
    }

    public static JSONResult error() {
        return new JSONResult(544,"操作失败",null);
    }

    public static JSONResult errorException(String msg) {
        return new JSONResult(555, msg, null);     //555：异常抛出信息
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     *
     * @Description: 将json结果集转化为JSONResult对象
     *                 需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     */
    public static JSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @Description: 没有object对象的转化
     * @param json
     * @return
     */
    public static JSONResult format(String json) {
        try {
            return MAPPER.readValue(json, JSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @Description: Object是集合转化
     *                 需要转换的对象是一个list
     * @param jsonData
     * @param clazz
     * @return
     *
     */
    public static JSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
