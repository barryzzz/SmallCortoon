package xi.lsl.code.app.com.comment;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Description: 关于评论的业务
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public class CommentModel {
    /**
     * 获取评论
     * @return
     */
    Observable<ResponseBody> getComment(){

        return null;
    }

    /**
     * 发送评论
     * @return
     */
    Observable<ResponseBody> insertComment(){

        return null;
    }

    /**
     * 点赞
     * @param userid
     * @return
     */
    Observable<ResponseBody> setThumbs(int userid){
        return null;
    }

    /**
     * 踩
     * @param userid
     * @return
     */
    Observable<ResponseBody> setDownThumbs(int userid){
        return null;
    }

}
