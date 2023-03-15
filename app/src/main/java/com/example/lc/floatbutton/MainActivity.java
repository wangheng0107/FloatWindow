package com.example.lc.floatbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import hover_view.HoverView;
import hover_view.ViewState;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentView)
    RecyclerView mRv;
    @BindView(R.id.contentView1)
    RecyclerView mRv1;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRv.setAdapter(mAdapter = new MyAdapter(this, this));
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setDataList(Arrays.asList(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20"
        ));
        mRv1.setAdapter(mAdapter = new MyAdapter(this, this));
        mRv1.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setDataList(Arrays.asList(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20"
        ));
    }

}
