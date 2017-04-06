package xi.lsl.code.lib.utils.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/4/6.
 */

public class Result<T> {

    public String ErrCode;
    public String ErrMsg;
    public String Costtime;
    public boolean IsError;
    public String Message;

    private Return Return;


    public Return getReturn() {
        return Return;
    }

    public void setReturn(Return aReturn) {
        Return = aReturn;
    }

    public class Return {
        private List<T> List;
        public int ListCount;
        public int PageSize;
        public int PageIndex;

        public void setT(List<T> t) {
            this.List = t;
        }

        public List<T> getT() {
            return List;
        }
    }


}
