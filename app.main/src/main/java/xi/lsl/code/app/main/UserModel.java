package xi.lsl.code.app.main;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.User;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public class UserModel {

    private Gson mGson;

    public UserModel() {
        mGson = new Gson();
    }

    Observable<BmobReponse> insertUser(User user) {
        return Nets.getBmobApis().InsertUser(createBody(user)).compose(RxSchedulers.<BmobReponse>io_main());
    }

    Observable<User> QueryUser(String email){
        Map<String,String> map=new HashMap<>();
        map.put("user_email",email);
        String json=mGson.toJson(map);
        return Nets.getBmobApis().QueryUser(json).compose(RxSchedulers.<User>io_main());
    }


    private RequestBody createBody(User user){
        if(mGson==null){
            throw  new RuntimeException("gson is null");
        }
        String json=mGson.toJson(user);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);

        return  body;
    }



}
