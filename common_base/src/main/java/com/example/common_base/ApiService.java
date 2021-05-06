package com.example.common_base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRawValue;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * ChuMuProjectFramework
 * <p>
 * Created by ChuMu on 2020/5/22
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 定义请求方法
 */

public interface ApiService {

    String USER_LOGIN = "login";
    String USER_REGISTER = "register";
    String FIND_PASSWORD = "findPassWord";
    String UPLOAD_IMAGE = "updateUserInfo";
    String UPDATE_PASSWORD = "/appUser/updatePassword";
    String BROWSING_HISTORY = "/appUser/getBrowsingHistory";
    String GET_V_CODE = "/appUser/getVCode";
    String HOME_TAB = "/index/positionTab";
    String APP_LIST = "/position/appList";
    String BANNER_LIST = "/ad/getBannerList";
    String STATISTICS_NUM = "appUser/statisticsNum";
    String PERFECT_INFO = "/appUser/improvePersonalInfo";
    String LOGOUT = "logout";
    String QUERY_STATISTICS_TOTAL = "queryStatisticsTotal";
    String GETCREATE_GROUP = "createGroup";
    String FINANCE_LIST = "financeList";
    String ADD_FINANCE = "addFinance";
    String QUERY_GROUP = "queryGroup";
    String LOGOUT_GROUP = "logoutGroup";
    String ADD_GROUP = "addGroup";


    @POST(UPLOAD_IMAGE)
    @FormUrlEncoded
    Observable<ResponseBody> getUpLodeImg(@Field("req") String json);

    @POST(UPDATE_PASSWORD)
    @FormUrlEncoded
    Observable<ResponseBody> getUpdatePassword(@Field("mobile") String mobile, @Field("oldPassword") String oldPassWord, @Field("newPassword") String newPassWord);

    @POST(UPDATE_PASSWORD)
    @FormUrlEncoded
    Observable<ResponseBody> getUpdateRegisterPassword(@Field("mobile") String mobile, @Field("oldPassword") String oldPassWord, @Field("newPassword") String newPassWord);

    @GET(GET_V_CODE)
    Observable<ResponseBody> getVCode(@Query("mobile") String mobile);

    @POST(USER_LOGIN)
    @FormUrlEncoded
    Observable<ResponseBody> getLoginPassword(@Field("req") String json);


    @POST(USER_REGISTER)
    @FormUrlEncoded
    Observable<ResponseBody> getLoginVerification(@Field("req") String json);

    @GET(LOGOUT)
    Observable<ResponseBody> getLogout(@Query("token") String token);

    @GET(QUERY_STATISTICS_TOTAL )
    Observable<ResponseBody> getQueryStatisticsTotal(@Query("token") String token);

    @GET(GETCREATE_GROUP)
    Observable<ResponseBody> getCreateGroup(@Query("token") String token,@Query("groupName") String  groupName);

    @GET(QUERY_GROUP)
    Observable<ResponseBody> getQueryGroup(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET(ADD_GROUP)
    Observable<ResponseBody> getAddGroup(@Query("groupId") int groupId, @Query("token") String token);

    @GET(LOGOUT_GROUP)
    Observable<ResponseBody> getLogouTGroup(@Query("groupId") int groupId, @Query("token") String token);

    @POST(FIND_PASSWORD)
    @FormUrlEncoded
    Observable<ResponseBody> getFindPassword(@Field("req") String json);

    @POST(FINANCE_LIST)
    @FormUrlEncoded
    Observable<ResponseBody> getFinanceList(@Field("financeListReq") String json);

    @POST(ADD_FINANCE)
    @FormUrlEncoded
    Observable<ResponseBody> getAddFinance(@Field("financeListReq") String json);

    @POST(USER_LOGIN)
    @FormUrlEncoded
    Observable<ResponseBody> getLoginSetPassword(@Field("mobile") String mobile, @Field("password") String password, @Field("createChannel") int channel, @Field("type") int type, @Field("vCode") String vCode);

    @GET(HOME_TAB)
    Observable<ResponseBody> getHomeTab();

    @POST(BROWSING_HISTORY)
    @FormUrlEncoded
    Observable<ResponseBody> getBrowsingHistory(@Field("positionIds") ArrayList<Integer> positionIds, @Field("type") String type, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);


    @GET(APP_LIST)
    Observable<ResponseBody> getAppList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("place") int place);

    @GET(STATISTICS_NUM)
    Observable<ResponseBody> getStatisticsNum(@Query("positionId") int positionId, @Query("type") int type, @Query("status") int status);
    @POST(PERFECT_INFO)
    @FormUrlEncoded
    Observable<ResponseBody> getPerfectInfo(@Field("nickName") String nickName, @Field("createChannel") int createChannel);




}
