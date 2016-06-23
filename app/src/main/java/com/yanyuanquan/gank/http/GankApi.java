package com.yanyuanquan.gank.http;


import com.yanyuanquan.gank.entity.Constants;
import com.yanyuanquan.gank.entity.GirlBean;
import com.yanyuanquan.gank.entity.ResultBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


public interface GankApi {

    @GET("data/{type}/" + Constants.PAZE_SIZE + "/{page}")
    Observable<ResultBean<List<GirlBean>>> getData(
            @Path("type") String type,
            @Path("page") int page);

    @GET("random/data/福利/{count}")
    Observable<ResultBean<List<GirlBean>>> getRandomImage(
            @Path("count") int count);

    @FormUrlEncoded
    @POST("add2gank")
    Observable<ResultBean> submit(
            @Field("url") String url,
            @Field("desc") String desc,
            @Field("who") String who,
            @Field("type") String type,
            @Field("debug") boolean debug);
}
