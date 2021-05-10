package com.syt.jsw.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.jsw.R;
import com.syt.jsw.adapter.MyRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 就是玩
 *
 * @author syt
 * created in 2021/5/6 10:18
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_recycle_view)
    RecyclerView rlRecycleView;

    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mAdapter.getItemCount() - 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rlRecycleView.setLayoutManager(mLayoutManager);
        String[] dataSource = {"天气预报", "新闻头条", "Apple", "FireFox", "Safari", "Microsoft", "Linux", "Windows", "MacOs", "Others", "FlipBoard"};
        mAdapter = new MyRecyclerViewAdapter();
        mAdapter.setDataSource(this, dataSource);
        rlRecycleView.setAdapter(mAdapter);
    }
}