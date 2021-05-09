package com.syt.jsw;

public class URL {
    /**
     * Api Shop平台提供接口
     */
    public static final String API_KEY = "38aqMvPbf9f86a5f2ba4ced0d958a0d01a97eab4cdd9513";
    public static final String WEATHER_URL = "https://api.apishop.net/common/weather/";

    /**
     * 根据地名查询对应ID
     */
    public static final String GETIDBYNAME = WEATHER_URL + "getAreaID";

    /**
     * 根据地区或地区ID查询未来15天的天气
     */
    public static final String GETWEATHER = WEATHER_URL + "get15DaysWeatherByArea";
}
