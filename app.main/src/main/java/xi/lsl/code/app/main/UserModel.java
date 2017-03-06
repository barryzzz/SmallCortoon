package xi.lsl.code.app.main;

import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public class UserModel {


    Observable<ResponseBody> insertUser(Map<String, String> map) {
        return Nets.getBmobApis().InsertUser(map).compose(RxSchedulers.<ResponseBody>io_main());
    }

}
