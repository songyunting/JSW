package com.syt.jsw.pojo;

import java.util.List;

import lombok.Data;

/**
 * @author syt
 * created in 2021/5/6 14:26
 */
@Data
public class WeatherCityDto {
    private String area;
    private String areaCode;
    private String areaid;
    private List<WeatherDto> dayList;
}
