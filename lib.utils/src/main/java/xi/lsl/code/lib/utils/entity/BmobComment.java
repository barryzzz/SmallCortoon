package xi.lsl.code.lib.utils.entity;

import java.util.List;

/**
 * 评论内容
 * Created by lishoulin on 2017/3/7.
 */

public class BmobComment {

    public List<ResultsBean> results;


    public class ResultsBean {
        public String book_id;
        public String comment_content;
        public String createdAt;
        public String objectId;
        public BmobUser user;

        public BmobBook mBook;

    }
}
