package xi.lsl.code.lib.utils.net;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    /**
     * @param user 参数以键值对形式，例如{"user_email":"408930131@qq.com"}
     * @return
     */
    @GET("classes/book_user")
    Observable<BmobUser> QueryUser(@Query("where") String user);

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
     * @param bookid 参数是键值形式 ,例如{'book_id':'1000'}  || ?include=user,book&where={bookid}
     * @return
     */
    @GET("classes/commet?include=user,book")
    Observable<BmobComment> QueryBookComment(@Query("where") String bookid);

    /**
     * 获取某本书的信息
     * @param bookid 参数是键值形式 ,例如{'book_id':'1000'}
     * @return
     */
    @GET("classes/book_book")
    Observable<BmobBook> QueryBooks(@Query("where") String bookid);

    /**
     * 插入书本的信息
     *
     * @param body
     * @return
     */
    @POST("classes/book_book")
    Observable<BmobReponse> InsertBooks(@Body RequestBody body);


}
