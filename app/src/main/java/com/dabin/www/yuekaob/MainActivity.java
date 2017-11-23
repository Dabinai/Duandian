package com.dabin.www.yuekaob;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dabin.www.yuekaob.adapter.HomeAdapter;
import com.dabin.www.yuekaob.bean.NewBase;
import com.dabin.www.yuekaob.presenter.Presenter;
import com.dabin.www.yuekaob.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.xrecycler)
    XRecyclerView xrecycler;
    private int type;
    private int size;
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        type = 1;
        size = 10;
        offset = 0;
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        new Presenter(this).setUrl(type + "", size + "", offset + "");
    }
    List<NewBase.SongListBean> listBeanList;

    @Override
    public void getData(final List<NewBase.SongListBean> song_list) {
        listBeanList = song_list;

        final HomeAdapter homeAdapter = new HomeAdapter(MainActivity.this, listBeanList);
        xrecycler.setAdapter(homeAdapter);
        xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                listBeanList.clear();
                type = 1;
                size = 10;
                offset = 0;
                new Presenter(MainActivity.this).setUrl(type + "", size + "", offset + "");
                xrecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                offset += 10;
                if (offset == 20) {
                    type += 1;
                    offset = 0;
                    if (type > 2) {
                        type = 1;
                    }
                }
                listBeanList.addAll(song_list);
                new Presenter(MainActivity.this).setUrl(type + "", size + "", offset + "");
                homeAdapter.notifyDataSetChanged();
                xrecycler.loadMoreComplete();
            }
        });

        homeAdapter.setOnDanji(new HomeAdapter.OnDanji() {
            @Override
            public void Danji(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("电影")
                        .setMessage("是否下载？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this,ViewActivity.class));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
