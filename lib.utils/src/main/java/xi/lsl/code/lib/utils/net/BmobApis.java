package xi.lsl.code.lib.utils.net;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobBook;
import xi.lsl.code.lib.utils.entity.BmobComment;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.BmobUser;

/**
 * bomb 后端逻辑
 * Created by lishoulin on 2017/3/5.
 */

public interface BmobApis {
    @GET("classes/book_user?where=")
    Observable<BmobUser> QueryUser(@Query("user_email") String email);

    @POST("classes/book_user")
    Observable<BmobReponse> InsertUser(@Body RequestBody body);

    /**
     * 插入评论
     *
     * @param body
     * @return
     */
    @POST("classes/commet")
    Observable<ResponseBody> SendComment(@Body RequestBody body);

    /**
     * 获取所有评论，实现朋友圈功能
     *
     * @return
     */
    @GET("classes/commet?include=user,book")
    Observable<BmobComment> QueryComment();

    /**
     * 获取指定书本的评论
     *
     * @param bookid
     * @return
     */
    @GET("classes/commet?where={'book_id':'{bookid}'}")
    Observable<BmobComment> QueryBookComment(@Path("bookid") String bookid);

    @GET("classes/book_book?where={'book_id':'{bookid}'}")
    Observable<BmobBook> QueryBooks(@Path("bookid") String bookid);

    @POST("classes/book_book")
    Observable<BmobReponse> InsertBooks(@Body RequestBody body);


}
