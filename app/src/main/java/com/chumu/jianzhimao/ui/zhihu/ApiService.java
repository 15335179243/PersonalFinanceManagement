package com.chumu.jianzhimao.ui.zhihu;



import com.chumu.jianzhimao.ui.zhihu.bean.BeanDetails;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSpecial;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    public static String Apizhihu = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<ResponseBody> getDataDaily();

    @GET("news/hot")
    Observable<BeanHot> getDataHot();

    @GET("sections")
    Observable<BeanSecialColumn> getDataSecialColumn();
    @GET("section/{id}")
    Observable<BeanSpecial> getDataNewSecial(@Path("id") int id);

    @GET("news/{id}")
    Observable<BeanDetails> getDataNewDetails(@Path("id") int id);

    public String WeChat = "http://api.tianapi.com/";



     String gank = "http://gank.io/api/";
    //    注：
//    category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
//    count 最大 50
    @GET("data/{tech}/{num}/{page}")
    Observable<ResponseBody> getDataGank(@Path("tech") String key, @Path("num") String num, @Path("page") String page);
    @GET("search/query/{search}/category/{category}/count/{count}/page/{page}")
    Observable<ResponseBody> getDataSearch(@Path("search") String search, @Path("category") String category, @Path("count") String count, @Path("page") String page);
    String sBaseUrl = "http://gank.io/api/search/query/我/";

}
