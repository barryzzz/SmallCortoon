package xi.lsl.code.app.com.comment;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobComment;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * Description: 关于评论的业务
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public class CommentModel {
    private Gson mGson;


    public CommentModel() {
        mGson = new Gson();
    }

    /**
     * 获取评论
     *
     * @return
     */
    Observable<BmobComment> getComment(String bookId) {

        return Nets.getBmobApis().QueryBookComment(bookId).compose(RxSchedulers.<BmobComment>io_main());
    }

    /**
     * 发送评论
     *
     * @return
     */
    Observable<ResponseBody> sendComment(String userObjid, String bookObjid, String bookId, String msg) {

        SendCm sendCm = createSendMsg(userObjid, bookObjid, bookId, msg);

        return Nets.getBmobApis().SendComment(createBody(sendCm)).compose(RxSchedulers.<ResponseBody>io_main());
    }

    /**
     * 点赞
     *
     * @param userid
     * @return
     */
    Observable<ResponseBody> setUpThumbs(int userid) {
        return null;
    }

    /**
     * 踩
     *
     * @param userid
     * @return
     */
    Observable<ResponseBody> setDownThumbs(int userid) {
        return null;
    }


    private Map<String, String> createMap() {
        return new HashMap<>();
    }


    private RequestBody createBody(SendCm user) {
        if (mGson == null) {
            throw new RuntimeException("gson is null");
        }
        String json = mGson.toJson(user);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        return body;
    }


    private SendCm createSendMsg(String userObjid, String bookObjid, String bookId, String msg) {
        SendCm sendCm = new SendCm();
        sendCm.comment_content = msg;
        sendCm.book_id = bookId;
        UserBean userBean = new UserBean();
        userBean.objectId = userObjid;
        BookBean bookBean = new BookBean();
        bookBean.objectId = bookObjid;
        sendCm.book = bookBean;
        sendCm.user = userBean;
        return sendCm;
    }

    /**
     * 发送数据实体类
     */
    class SendCm {
        public String comment_content;
        public String book_id;
        public UserBean user;
        public BookBean book;
    }

    class UserBean {
        private String __type = "Pointer";
        private String className = "book_user";
        public String objectId;
    }

    class BookBean {
        private String __type = "Pointer";
        private String className = "book_book";
        public String objectId;
    }

}
