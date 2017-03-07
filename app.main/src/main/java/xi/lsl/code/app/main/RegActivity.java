package xi.lsl.code.app.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.entity.RegMsg;
import xi.lsl.code.lib.utils.entity.User;
import xi.lsl.code.lib.utils.net.Constants;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.utils.EncryptionUtils;
import xi.lsl.code.lib.utils.utils.InputTextUtil;
import xi.lsl.code.lib.utils.utils.SPUtil;

/**
 * 注册
 * Created by lishoulin on 2017/2/12.
 */

public class RegActivity extends BaseActivity {

    @InjectView(R.id.email)
    EditText mEmail;
    @InjectView(R.id.password)
    EditText mPassword;
    @InjectView(R.id.reg_check)
    CheckBox mCheckBox;

    private static final int FromType = 2;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.inject(this);

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("loading....");
    }


    @OnClick(R.id.btn_reg)
    public void regUser(View v) {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        boolean isCheck = mCheckBox.isChecked();
        reg(email, password, isCheck);
    }

    @OnClick(R.id.btn_back)
    public void back(View v) {
        finish();
    }

    private void reg(final String email, final String password, boolean ischeck) {

        if (TextUtils.isEmpty(email)) {
            toast("邮箱不能为空");
            return;
        }
        if (!InputTextUtil.isValidEmail(email)) {
            toast("请输入正确的邮箱地址");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            toast("密码不能为空");
            return;
        }
        if (!ischeck) {
            toast("不接受我方协议无法注册!");
            return;
        }
        progressDialog.show();
        mSubscription.add(Nets.getShuHuiApis().userReg(email, EncryptionUtils.createMd5(password), FromType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<RegMsg>() {
                    @Override
                    public void call(RegMsg loginMsg) {

                        if (loginMsg != null) {
                            if (loginMsg.getErrCode().equals(Constants.NETWORK_STATUS_SUCCESS)) {
                                toast("注册成功!");
//                                User user = new User(email, password);
//                                SPUtil.saveObject(Constants.KEY_LOGIN, user);
                                gotoActivity(WelcomeActivity.class, true);
                            } else {
                                toast(loginMsg.getErrMsg());
                            }
                            progressDialog.dismiss();

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("info--->", throwable.getMessage());
                        progressDialog.dismiss();

                    }
                })
        );


    }


}
