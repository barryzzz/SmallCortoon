package xi.lsl.code.lib.utils.net;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.User;

/**
 * Created by lishoulin on 2017/3/5.
 */

public interface BmobApis {
    @GET("classes/book_user?where=")
    Observable<User> QueryUser(@Query("user_email") String email);

    @POST("classes/book_user")
    Observable<BmobReponse> InsertUser(@Body RequestBody body);

//    @PUT("classes/book_user")
//    Observable<ResponseBody> UpdateUser(@QueryMap Map<string>);

    @POST("classes/book_comment")
    Observable<ResponseBody> InsertComment(@QueryMap Map<String, String> map);

    @GET("classes/book_comment")
    Observable<ResponseBody> QueryComment(@Query("book_id") String bookid);

    @GET("classes/book_book")
    Observable<ResponseBody> QueryBooks();

    @POST("classes/book_book")
    Observable<BmobReponse> InsertBooks(@QueryMap Map<String, String> map);

    //调用云逻辑获取更新
    @POST("functions/getUpdate")
    Observable<ResponseBody> getUpdate();

}
