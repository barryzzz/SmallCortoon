package xi.lsl.code.lib.utils.entity;

/**
 * Created by lishoulin on 2017/2/12.
 */

public class RegMsg {
    //    {
//        "ErrCode": "-2",
//            "ErrMsg": "邮箱已被注册",
//            "Return": null,
//            "Costtime": "5",
//            "IsError": true,
//            "Message": ""
//    }
    private String ErrCode;
    private String ErrMsg;
    private String Costtime;
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

    public String getCosttime() {
        return Costtime;
    }

    public void setCosttime(String costtime) {
        Costtime = costtime;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
