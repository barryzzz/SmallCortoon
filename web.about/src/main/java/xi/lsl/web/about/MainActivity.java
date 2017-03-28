package xi.lsl.web.about;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Small.getUri(this);
        String type = "about";
        if (uri != null) {
            type = uri.getQueryParameter("type");
        }

        Small.preSetUp(getApplication());
        Small.setUp(this, null);

        if (type.equals("feedback")) {
            Small.openUri("file:///android_asset/feedback.html", MainActivity.this);
        } else {
            Small.openUri("file:///android_asset/about.html", MainActivity.this);
        }

        finish();
    }
}
