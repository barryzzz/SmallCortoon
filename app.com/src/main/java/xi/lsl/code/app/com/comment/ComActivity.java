package xi.lsl.code.app.com.comment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.com.R;
import xi.lsl.code.lib.utils.base.BaseActivity;

public class ComActivity extends BaseActivity {

    @InjectView(R.id.com_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.com_fab)
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        ButterKnife.inject(this);
    }
}
