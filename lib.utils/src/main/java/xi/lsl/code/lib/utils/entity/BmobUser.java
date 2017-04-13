package xi.lsl.code.lib.utils.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/8.
 */

public class BmobUser {

    private String objectId;

    @SerializedName("user_userid")
    private String id;

    @SerializedName("user_email")
    private String email;

    private String pass;
    @SerializedName("user_headurl")
    private String headurl;

    public BmobUser() {

    }

    public BmobUser(String id, String email, String pass, String headurl) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.headurl = headurl;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
}
