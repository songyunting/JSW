package com.syt.jsw.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.syt.jsw.Interface.OnRequestResult;
import com.syt.jsw.R;
import com.syt.jsw.adapter.WeatherAdapter;
import com.syt.jsw.pojo.WeatherDto;
import com.syt.jsw.utils.JsonUtils;
import com.syt.jsw.utils.OkgoUtils;
import com.wang.avi.AVLoadingIndicatorView;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.syt.jsw.net.Url.API_KEY;
import static com.syt.jsw.net.Url.GETWEATHER;

/**
 * 天气预报
 *
 * @author syt
 * created in 2021/5/7 17:45
 */
public class WeatherActivity extends AppCompatActivity implements OnRequestResult {

    @BindView(R.id.tv_city)
    TextView tvCity;

    @BindView(R.id.avi_loading)
    AVLoadingIndicatorView aviLoading;

    @BindView(R.id.rv_weather)
    RecyclerView rvWeather;

    private List<WeatherDto> weatherDtos = new ArrayList<>();
    private WeatherAdapter weatherAdapter;
    private String cityName = "徐州", province = "江苏省", cityCode = "112211";
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
    }

    private void initDate() {
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        weatherAdapter = new WeatherAdapter(R.layout.weather_view_item, weatherDtos);
        rvWeather.setAdapter(weatherAdapter);
        Map<String, String> param = new HashMap<>();
        param.put("apiKey", API_KEY);
        param.put("area", cityName);
        OkgoUtils.getInstance().post(GETWEATHER, param, 0, WeatherActivity.this);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        /**
         * getAddrStr()   //获取详细地址信息
         * getCountry();    //获取国家
         * getProvince();    //获取省份
         * getCity();    //获取城市
         * getDistrict();    //获取区县
         * getStreet();    //获取街道信息
         * getAdCode();    //获取adcode
         * getTown();    //获取乡镇信息
         */
        @Override
        public void onReceiveLocation(BDLocation location) {
            cityName = location.getCity();
            province = location.getProvince();
            cityCode = location.getAdCode();
            tvCity.setText(cityName);
            initDate();
        }
    }

    @OnClick(R.id.left_back)
    void back() {
        finish();
    }

    @OnClick(R.id.search_city)
    void searchCity() {
        CityPicker.from(WeatherActivity.this)
                .setLocatedCity(null)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        cityName = String.format("%s", data.getName());
                        tvCity.setText(cityName);
                        initDate();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onLocate() {
                        new Handler().postDelayed(() -> CityPicker.from(WeatherActivity.this).locateComplete(new LocatedCity(cityName, province, cityCode), LocateState.SUCCESS), 2000);
                    }
                })
                .show();
    }

    @Override
    public void onRequestStart(int reqCode) {
        aviLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(int code, String json) {
        if (!StringUtils.isEmpty(json)) {
            weatherDtos.clear();
            String data = JsonUtils.getValue(json, "result");
            JSONObject jsonObject = JSONObject.parseObject(data);
            String data2 = jsonObject.getString("dayList");
            weatherDtos = JsonUtils.getList(data2, WeatherDto.class);
            if (weatherDtos != null) {
                weatherAdapter.addData(weatherDtos);
            }
        } else {
            ToastUtils.showShort("天气预报服务费用不足");
        }
    }

    @Override
    public void onFailed(int code, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onRequestFinish(int reqCode) {
        aviLoading.setVisibility(View.GONE);
    }
}