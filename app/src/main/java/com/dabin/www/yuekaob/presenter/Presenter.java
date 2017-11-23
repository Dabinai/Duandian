package com.dabin.www.yuekaob.presenter;

import com.dabin.www.yuekaob.bean.NewBase;
import com.dabin.www.yuekaob.model.Model;
import com.dabin.www.yuekaob.view.IView;

import java.util.List;

/**
 * Created by Dabin on 2017/11/23.
 */

public class Presenter implements Model.OnFinish{
    private final IView iView;
    private final Model model;

    public Presenter(IView iView) {
        this.iView = iView;
        model = new Model();
        model.setOnFinish(this);
    }

    public void setUrl(String type, String size, String offset){
        model.getUrl(type, size, offset);
    }

    @Override
    public void Finished(List<NewBase.SongListBean> song_list) {
        iView.getData(song_list);
    }
}
