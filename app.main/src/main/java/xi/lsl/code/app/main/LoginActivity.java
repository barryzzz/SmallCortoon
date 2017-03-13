package xi.lsl.code.app.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.app.main.main.MainActivity;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.LoginMsg;
import xi.lsl.code.lib.utils.entity.User;
import xi.lsl.code.lib.utils.net.Constants;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;
import xi.lsl.code.lib.utils.utils.EncryptionUtils;
import xi.lsl.code.lib.utils.utils.FastOnClickUtil;
import xi.lsl.code.lib.utils.utils.SPUtil;
import xi.lsl.code.lib.utils.utils.Setting;

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
     * TODO 查询用户是否存在 todolist
     *
     * @param s1 email
     * @param s2 password
     */
    private void gotoLogin(final String s1, final String s2) {
        mSubscription.add(Nets.getShuHuiApis().userLogin(s1, s2, FromType)
                .compose(RxSchedulers.<LoginMsg>io_main())
                .flatMap(new Func1<LoginMsg, Observable<BmobReponse>>() {
                    @Override
                    public Observable<BmobReponse> call(LoginMsg loginMsg) {
                        LoginMsg.ReturnBean bean = loginMsg.getReturn();
                        User user = new User(bean.getId() + "", s1, s2, bean.getAvatar());
                        SPUtil.saveObject(Constants.KEY_LOGIN, user);
                        SPUtil.saveSetting(Setting.IS_LOGIN, Setting.CODE_1);
                        App.userInfo = user;
                        return mUserModel.insertUser(user);
                    }
                }).subscribe(new Action1<BmobReponse>() {
                    @Override
                    public void call(BmobReponse bmobReponse) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        gotoActivity(MainActivity.class, true); //成功跳转
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        gotoActivity(MainActivity.class, true); //成功跳转
                    }
                }));

    }

    @OnClick(R.id.btn_reg)
    public void gotoReg(View v) {
        gotoActivity(RegActivity.class, false);
    }


}

