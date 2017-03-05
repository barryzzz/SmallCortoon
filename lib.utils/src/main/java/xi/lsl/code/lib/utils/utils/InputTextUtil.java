package xi.lsl.code.lib.utils.utils;

/**
 * Created by lishoulin on 2017/2/12.
 */

public class InputTextUtil {
    public static boolean isValidEmail(String mail) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return mail.matches(regex);
    }
}
