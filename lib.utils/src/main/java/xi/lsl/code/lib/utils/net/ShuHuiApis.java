package xi.lsl.code.lib.utils.net;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BookEntity;
import xi.lsl.code.lib.utils.entity.LoginMsg;
import xi.lsl.code.lib.utils.entity.RegMsg;
import xi.lsl.code.lib.utils.entity.Slide;
import xi.lsl.code.lib.utils.entity.SubEntity;

/**
 * Created by lishoulin on 2017/3/5.
 */

public interface ShuHuiApis {
    //
//    public static final String APP_HTTP = "http://www.ishuhui.net";
//
//    public static final String GET_ALL_BOOK = "http://www.ishuhui.net/ComicBooks/GetSubscribe"; //获取最新更新的漫画
//    public static final String GET_NEW_BOOK = "http://www.ishuhui.net/ComicBooks/GetLastChapterForBookIds?idJson=[1,2,3]";
//    public static final String URL_CATEGORY_DATA = "http://www.ishuhui.net/ComicBooks/GetAllBook"; //获取某一分类的30条记录，需要带参数
//    public static final String URL_COMIC_BOOK_DATA = "http://www.ishuhui.net/ComicBooks/GetChapterList";  //某一部漫画的集数
//    public static final String URL_IMG_CHAPTER = "http://www.ishuhui.net/ReadComicBooksToIso/";  //漫画内容
//    public static final String URL_RECOMMEND = "http://www.ishuhui.net/ComicBooks/GetAllBook"; //获取最新推荐漫画
//    public static final String URL_SEARCH_DATA = "http://www.ishuhui.net/ComicBooks/GetAllBook";  //搜索api
//    public static final String URL_SLIDE_DATA = "http://two.ishuhui.com/imgs.html";  //获取幻灯片,获取广告页
//    public static final String URL_SUBCRIBE_USER = "http://www.ishuhui.net/ComicBooks/GetAllBook";  //获取自己的订阅列表
//    public static final String URL_USER_LOGIN = "http://www.ishuhui.net/UserCenter/Login";  //用户登录
//    public static final String URL_USER_REGISTER = "http://www.ishuhui.net/UserCenter/Regedit";  //用户注册
//    public static final String URL_USER_SUBSCRIBE = "http://www.ishuhui.net/Subscribe";  //订阅漫画
//    public static final String URL_WEEK_SEVEN = "http://www.ishuhui.net/ComicBooks/GetAllBook";  //获取本周更新的漫画
//
    @FormUrlEncoded
    @POST("UserCenter/Login")
    Observable<LoginMsg> userLogin(@Field("Email") String email, @Field("Password") String password, @Field("FromType") int type);

    @FormUrlEncoded
    @POST("UserCenter/Regedit")
    Observable<RegMsg> userReg(@Field("Email") String email, @Field("Password") String password, @Field("FromType") int type);


    @GET("ComicBooks/GetAllBook")
    Observable<BookEntity> getBooks(@QueryMap Map<String, String> map);  //获取本周更新/订阅/最新推荐/搜索/获取分类记录

    @GET("Subscribe")
    Observable<SubEntity> subBook(@QueryMap Map<String, String> map);

    @GET("ComicBooks/GetSubscribe")
    Observable<BookEntity> getSubBooks();


}
