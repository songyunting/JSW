package com.syt.jsw.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syt.jsw.R;
import com.syt.jsw.pojo.WeatherDto;

import java.util.List;

/**
 * 天气预报 适配器
 *
 * @author syt
 * created in 2021/5/7 10:05
 */
public class WeatherAdapter extends BaseQuickAdapter<WeatherDto, BaseViewHolder> {

    public WeatherAdapter(int layoutResId, @Nullable List<WeatherDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherDto dto) {
        TextView city, date, dayTemperature, dayWeather, dayWindDirection, nightTemperature, nightWeather, nightWindDirection;
        ImageView dayWeatherPic, nightWeatherPic;
        city = helper.getView(R.id.tv_city);
        date = helper.getView(R.id.tv_date);
        dayWeatherPic = helper.getView(R.id.img_day_weather_pic);
        dayTemperature = helper.getView(R.id.tv_day_temperature);
        dayWeather = helper.getView(R.id.tv_day_weather);
        dayWindDirection = helper.getView(R.id.tv_day_wind_direction);
        nightWeatherPic = helper.getView(R.id.img_night_weather_pic);
        nightTemperature = helper.getView(R.id.tv_night_temperature);
        nightWeather = helper.getView(R.id.tv_night_weather);
        nightWindDirection = helper.getView(R.id.tv_night_wind_direction);

        city.setText(dto.getArea());
        date.setText(dto.getDaytime());
        Glide.with(mContext)
                .load(dto.getDay_weather_pic())
                .asBitmap()
                .error(R.drawable.ic_weather)
                .fallback(R.drawable.ic_weather)
                .into(dayWeatherPic);
        dayTemperature.setText(dto.getDay_air_temperature());
        dayWeather.setText(dto.getDay_weather());
        dayWindDirection.setText(dto.getDay_wind_direction());
        Glide.with(mContext)
                .load(dto.getNight_weather_pic())
                .asBitmap()
                .error(R.drawable.ic_night_weather)
                .fallback(R.drawable.ic_night_weather)
                .into(nightWeatherPic);
        nightTemperature.setText(dto.getNight_air_temperature());
        nightWeather.setText(dto.getNight_weather());
        nightWindDirection.setText(dto.getNight_wind_direction());
    }
}
