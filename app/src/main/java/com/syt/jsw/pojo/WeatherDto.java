package com.syt.jsw.pojo;

import lombok.Data;

/**
 * 天气详情 实体类
 *
 * @author syt
 * created in 2021/5/6 11:17
 */
@Data
public class WeatherDto {

    /**
     * 地区
     */
    private String area;

    /**
     * 地区ID
     */
    private String areaid;

    /**
     * 日平均温度
     */
    private String day_air_temperature;

    /**
     * 天气状况
     */
    private String day_weather;

    /**
     * 天气状况编码
     */
    private String day_weather_code;

    /**
     * 天气状况示例图片
     */
    private String day_weather_pic;

    /**
     * 风向
     */
    private String day_wind_direction;

    /**
     * 风力
     */
    private String day_wind_power;

    /**
     * 日期
     */
    private String daytime;

    /**
     * 晚间参数
     */
    private String night_air_temperature;
    private String night_weather;
    private String night_weather_code;
    private String night_weather_pic;
    private String night_wind_direction;
}
