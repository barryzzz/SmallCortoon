package xi.lsl.code.app.main;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.BmobUser;
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

    Observable<BmobReponse> insertUser(BmobUser user) {
        return Nets.getBmobApis().InsertUser(createBody(user)).compose(RxSchedulers.<BmobReponse>io_main());
    }

    Observable<BmobUser> QueryUser(String email){
        Map<String,String> map=new HashMap<>();
        map.put("user_email",email);
        String json=mGson.toJson(map);
        return Nets.getBmobApis().QueryUser(json).compose(RxSchedulers.<BmobUser>io_main());
    }


    private RequestBody createBody(BmobUser user){
        if(mGson==null){
            throw  new RuntimeException("gson is null");
        }
        String json=mGson.toJson(user);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);

        return  body;
    }



}
