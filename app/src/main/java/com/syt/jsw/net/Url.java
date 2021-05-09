package com.syt.jsw.net;

/**
 * Api 接口
 *
 * @author syt
 * created in 2021/5/9 16:45
 */
public class Url {

    /**
     * 聚合数据 平台提供接口
     */
    public static final String J_API_KEY = "dda0252dbf4b36965562920696dff1c1";

    /**
     * 新闻头条
     */
    public static final String NEW_TOP = "http://v.juhe.cn/toutiao/index";

    /**
     * Api Shop平台提供接口
     */
    public static final String API_KEY = "iLcIm9qe1785f2bc891ff73b90dffab9326be98a63cbd97";
    public static final String WEATHER_URL = "https://api.apishop.net/common/weather/";

    /**
     * 根据地区或地区ID查询未来15天的天气
     */
    public static final String GETWEATHER = WEATHER_URL + "get15DaysWeatherByArea";
}
