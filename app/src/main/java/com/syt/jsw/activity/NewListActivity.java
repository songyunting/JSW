package com.syt.jsw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.syt.jsw.Interface.OnRequestResult;
import com.syt.jsw.R;
import com.syt.jsw.adapter.NewAdapter;
import com.syt.jsw.pojo.NewDto;
import com.syt.jsw.utils.JsonUtils;
import com.syt.jsw.utils.OkgoUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.syt.jsw.net.Url.J_API_KEY;
import static com.syt.jsw.net.Url.NEW_TOP;

/**
 * 资讯列表
 *
 * @author syt
 * created in 2021/5/9 16:34
 */
public class NewListActivity extends AppCompatActivity implements OnRequestResult {

    @BindView(R.id.title)
    TextView tvTitle;

    @BindView(R.id.rl_new_list_view)
    RecyclerView newListView;

    @BindView(R.id.avi_loading)
    AVLoadingIndicatorView aviLoading;

    private NewAdapter newAdapter;

    private List<NewDto> dtoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * top(推荐)  guonei(国内)  guoji(国际)
     * yule(娱乐)   tiyu(体育)  junshi(军事)
     * keji(科技)  caijing(财经)  shishang(时尚)
     * youxi(游戏)  qiche(汽车)  jiankang(健康)
     */
    private void initData() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
        newListView.setLayoutManager(new LinearLayoutManager(this));
        newAdapter = new NewAdapter(R.layout.new_view_item, dtoList);
        newListView.setAdapter(newAdapter);
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("key", J_API_KEY);
        OkgoUtils.getInstance().get(NEW_TOP, param, 0, NewListActivity.this);
    }

    @OnClick(R.id.left_back)
    void back() {
        finish();
    }

    @Override
    public void onRequestStart(int reqCode) {
        aviLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(int code, String json) {
        if (!StringUtils.isEmpty(json)) {
            dtoList.clear();
            String data = JsonUtils.getValue(json, "result");
            JSONObject jsonObject = JSONObject.parseObject(data);
            String data2 = jsonObject.getString("data");
            dtoList = JsonUtils.getList(data2, NewDto.class);
            if (dtoList != null) {
                newAdapter.addData(dtoList);
            }
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