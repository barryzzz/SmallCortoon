package xi.lsl.code.lib.utils;

import android.util.Log;

import org.junit.Test;

import rx.functions.Action0;
import rx.functions.Action1;
import xi.lsl.code.lib.utils.entity.BmobComment;
import xi.lsl.code.lib.utils.net.Nets;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/4/18.
 */

public class BmobUnitTest {
    @Test
    public void test_api() {
      Nets.getBmobApis().QueryBookComment("{'book_id':'1000'}");
    }
}
