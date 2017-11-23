package com.dabin.www.yuekaob.model;

import com.dabin.www.yuekaob.bean.NewBase;
import com.dabin.www.yuekaob.utils.RetroFactory;

import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dabin on 2017/11/23.
 */

public class Model implements IModel {

    private OnFinish onFinish;

    public interface OnFinish{
        void Finished(List<NewBase.SongListBean> song_list);
    }
    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getUrl(String type, String size, String offset) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("size", size);
        map.put("offset", offset);
        RetroFactory.getInetce().getBase("ting?method=baidu.ting.billboard.billList", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewBase>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewBase newBase) {
                        List<NewBase.SongListBean> song_list = newBase.getSong_list();
                        onFinish.Finished(song_list);
                    }
                });
    }
}
