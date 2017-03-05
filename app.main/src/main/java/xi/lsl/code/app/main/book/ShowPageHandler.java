package xi.lsl.code.app.main.book;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

/**
 * Created by lishoulin on 2017/3/1.
 */

public class ShowPageHandler extends Handler {


    public ShowPageHandler() {

    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0x1:
                final int count = msg.arg1;
                final ViewPager viewPager = (ViewPager) msg.obj;
                int i = viewPager.getCurrentItem();
                if (i == count - 1) {
                    i--;
                } else {
                    i++;
                }
                viewPager.setCurrentItem(i, false);

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPage(viewPager, count);
                    }
                }, 6_000);
                break;
        }
        super.handleMessage(msg);
    }

    /**
     * @param viewPager
     * @param count
     */
    public void showPage(ViewPager viewPager, int count) {
        Message message = new Message();
        message.obj = viewPager;
        message.arg1 = count;
        message.what = 0x1;
        sendMessage(message);
    }
}
