package com.dabin.www.yuekaob.utils;

import com.dabin.www.yuekaob.bean.NewBase;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Dabin on 2017/11/23.
 */

public interface ApiService {

    @POST
    Observable<NewBase> getBase(@Url String url, @QueryMap Map<String,String> map);

}
