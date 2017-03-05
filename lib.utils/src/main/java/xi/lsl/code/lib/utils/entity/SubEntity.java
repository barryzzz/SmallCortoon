package xi.lsl.code.lib.utils.entity;

/**
 * Description: 订阅返回消息实体类
 * Author   :lishoulin
 * Date     :2017/2/23.
 */

public class SubEntity {

    private String ErrCode;
    private String ErrMsg;
    private boolean Return;
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

    public boolean isReturn() {
        return Return;
    }

    public void setReturn(boolean aReturn) {
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
}
