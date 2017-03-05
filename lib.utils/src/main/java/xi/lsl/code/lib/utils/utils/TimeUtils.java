package xi.lsl.code.lib.utils.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public class TimeUtils {

    public static String pareseTime(String time) {


        String str = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        str = format.format(new Date(time));
        return str;
    }

}
