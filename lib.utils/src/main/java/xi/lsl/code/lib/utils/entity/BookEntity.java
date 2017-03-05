package xi.lsl.code.lib.utils.entity;

/**
 * Created by lishoulin on 2017/2/14.
 */

public class BookEntity {
    private String ErrCode;
    private String ErrMsg;
    private ReturnBean Return;
    private String Costtime;
    private boolean IsError;
    private String Message;


    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public ReturnBean getReturn() {
        return Return;
    }

    public void setReturn(ReturnBean aReturn) {
        Return = aReturn;
    }

    public String getCosttime() {
        return Costtime;
    }

    public void setCosttime(String costtime) {
        Costtime = costtime;
    }

    public boolean isError() {
        return IsError;
    }

    public void setError(boolean error) {
        IsError = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public class ReturnBean {

        private Object ParentItem;
        private int ListCount;
        private int PageSize;
        private int PageIndex;
        private java.util.List<Book> List;


        public Object getParentItem() {
            return ParentItem;
        }

        public void setParentItem(Object parentItem) {
            ParentItem = parentItem;
        }

        public int getListCount() {
            return ListCount;
        }

        public void setListCount(int listCount) {
            ListCount = listCount;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int pageSize) {
            PageSize = pageSize;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int pageIndex) {
            PageIndex = pageIndex;
        }

        public java.util.List<Book> getList() {
            return List;
        }

        public void setList(java.util.List<Book> list) {
            List = list;
        }
    }

}
