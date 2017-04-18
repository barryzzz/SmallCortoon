package xi.lsl.code.app.com;

import org.junit.Test;

import xi.lsl.code.app.com.comment.CommentModel;
import xi.lsl.code.lib.utils.net.Nets;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Nets.getBmobApis().QueryComment();
    }
}