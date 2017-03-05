package xi.lsl.code.lib.utils.entity;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/1.
 */

public class Slide {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * Id : 1
         * title : 鼠绘官方淘宝
         * Img : http://img02.ishuhui.com/sbs/APPguanggao1.jpg
         * Link : http://ishuhui.taobao.com
         */

        private int Id;
        private String title;
        private String Img;
        private String Link;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }
    }
}
