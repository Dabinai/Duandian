package com.dabin.www.yuekaob.view;

import com.dabin.www.yuekaob.bean.NewBase;

import java.util.List;

/**
 * Created by Dabin on 2017/11/23.
 */

public interface IView {
    void getData(List<NewBase.SongListBean> song_list);
}
