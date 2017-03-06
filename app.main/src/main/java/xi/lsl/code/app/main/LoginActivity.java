package xi.lsl.code.app.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.app.main.main.MainActivity;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.entity.LoginMsg;
import xi.lsl.code.lib.utils.entity.User;
import xi.lsl.code.lib.utils.net.Constants;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.utils.EncryptionUtils;
import xi.lsl.code.lib.utils.utils.FastOnClickUtil;
import xi.lsl.code.lib.utils.utils.SPUtil;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private static final int FromType = 2;
    @InjectView(R.id.email)
    EditText mEmail;
    @InjectView(R.id.password)
    EditText mPassword;

    private UserModel mUserModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mSubscription = new CompositeSubscription();
        mUserModel = new UserModel();
    }

    @OnClick(R.id.btn_login)
    public void login(View v) {
        if (FastOnClickUtil.fastClick800()) {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                gotoLogin(email, EncryptionUtils.createMd5(password));
            } else {
                mEmail.setError("not null");
                mEmail.setError("not null");
            }
        }

    }

    /**
     * @param s1 email
     * @param s2 password
     */
    private void gotoLogin(final String s1, final String s2) {
        mSubscription.add(Nets.getShuHuiApis().userLogin(s1, s2, FromType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<LoginMsg>() {
                    @Override
                    public void call(LoginMsg loginMsg) {
                        if (loginMsg != null) {
                            if (loginMsg.getErrCode().equals(Constants.NETWORK_STATUS_SUCCESS)) {
                                User user = new User(s1, s2);
                                SPUtil.saveObject(Constants.KEY_LOGIN, user);
                                saveBmobUser(loginMsg.getReturn());
                                App.userInfo = user;
                            } else {
                                toast(loginMsg.getErrMsg());
                            }

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("info--->", throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                }));

    }

    @OnClick(R.id.btn_reg)
    public void gotoReg(View v) {
        gotoActivity(RegActivity.class, false);
    }


    private void saveBmobUser(LoginMsg.ReturnBean bean) {
        if (bean != null) {
            Map<String, String> map = new HashMap<>();
            map.put("user_userid", bean.getId() + "");
            map.put("user_headurl", bean.getAvatar());
            map.put("user_email", bean.getEmail());
//            map.put("user_nickname", bean.getNickName());
            if (mUserModel != null) {
                mSubscription.add(mUserModel.insertUser(map)
                        .subscribe(new Action1<ResponseBody>() {
                            @Override
                            public void call(ResponseBody responseBody) {

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                gotoActivity(MainActivity.class, true); //成功跳转
                            }
                        }));
            }

        } else {
            gotoActivity(MainActivity.class, true); //成功跳转
        }
    }

}

