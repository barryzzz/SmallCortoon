package xi.lsl.code.app.main.read;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.PopupWindowCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import net.wequick.small.Small;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.base.widget.ReadWebView;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.Chapter;
import xi.lsl.code.lib.utils.entity.Result;
import xi.lsl.code.lib.utils.net.CommonApis;
import xi.lsl.code.lib.utils.net.Constants;
import xi.lsl.code.lib.utils.utils.ScreenUtil;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public class WebReadActivity extends BaseActivity {
    public static final String URL = "url";
    public static final String TITLE = "title";
    @InjectView(R.id.read_webview)
    ReadWebView mWebView;
    @InjectView(R.id.read_bottom)
    LinearLayout mBottomLinearLayout;

    private String url;
    private String CHAPTER_ID; //章节id
    private String CHAPTER_TITLE;
    private String BOOK_ID;//书本id
    private String BOOK_NAME; //书本名称
    private CompositeSubscription mSubscription;
    private ReadModel mReadModel;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_book_read);
        ButterKnife.inject(this);

        CHAPTER_ID = getIntent().getStringExtra(Constants.WEB_CHAPTER_ID);
        CHAPTER_TITLE = getIntent().getStringExtra(Constants.WEB_CHAPTER_TITLE);
        BOOK_ID = getIntent().getStringExtra(Constants.WEB_BOOK_ID);
        BOOK_NAME = getIntent().getStringExtra(Constants.WEB_BOOK_NAME);
        url = CommonApis.URL_IMG_CHAPTER + CHAPTER_ID;

        mSubscription = new CompositeSubscription();
        mReadModel = new ReadModel();
        if (CHAPTER_ID != null && !"".equals(CHAPTER_ID))
            saveBook(BOOK_ID, CHAPTER_ID, BOOK_NAME, CHAPTER_TITLE);

        iniWebView();
        iniPopuWindow();


    }

    /**
     * 读取章节数据
     */
    private void loadChapter() {
        if (BOOK_ID != null) {
            mSubscription.add(mReadModel.queryChapterLists(BOOK_ID, "20").subscribe(new Action1<Result<Chapter>>() {
                @Override
                public void call(Result<Chapter> bookList) {

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {

                }
            }, new Action0() {
                @Override
                public void call() {

                }
            }));
        }
    }


    private void saveBook(String bookId, String ChapterId, String BookName, String ChapterName) {
        if (ChapterId != null && BookName != null) {
            mSubscription.add(mReadModel.insertBook(bookId, ChapterId, BookName, ChapterName).subscribe(new Action1<BmobReponse>() {
                @Override
                public void call(BmobReponse bmobReponse) {
                    Log.e("info--》", "code" + bmobReponse.code);
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {

                }
            }, new Action0() {
                @Override
                public void call() {

                }
            }));
        }
    }

    private void iniPopuWindow() {

        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ScreenUtil.dip2px(200f));
        mPopupWindow.setContentView(LayoutInflater.from(mContext).inflate(R.layout.popu_layout, null));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setClippingEnabled(true);

//        mPopupWindow.setAnimationStyle(xi.lsl.code.smallcortoon.R.style.popu_anim_style);


        View view = mPopupWindow.getContentView();
        ButterKnife.inject(view);

    }

    private void iniWebView() {

        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.setOnCenterClickLisenter(new ReadWebView.onCenterClickLisenter() {
            @Override
            public void onClick(View v) {
                swithchBar();
            }
        });
//        mWebView.setOnScrollLisenter(new ReadWebView.onScrollLisenter() {
//            @Override
//            public void onScroll(View v) {
//                if (mBottomLinearLayout.getVisibility() == View.VISIBLE) {
//                    hideBar();
//                }
//            }
//        });
        if (url != null) {
            mWebView.loadUrl(url);
        }

    }

    @OnClick({R.id.read_bottom_comment, R.id.read_bottom_changesee, R.id.read_bottom_catalog, R.id.read_bottom_down})
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.read_bottom_comment:
//                gotoActivity(BookCommentActivity.class, false);
                if (Small.openUri("com?bookid=" + CHAPTER_ID, mContext)) {

                } else {
                    toast("进行下载操作!");
                }
                break;
            case R.id.read_bottom_changesee:
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    hideBar();
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            case R.id.read_bottom_catalog:
                if (mPopupWindow != null) {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    } else {
                        PopupWindowCompat.showAsDropDown(mPopupWindow, mBottomLinearLayout, Gravity.TOP, 0, 0);
                    }
                }
                break;
            case R.id.read_bottom_down:
                break;
            default:
                break;
        }
    }


    private synchronized void showBar() {
        mBottomLinearLayout.setVisibility(View.VISIBLE);
        switchStatusBar(false);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private synchronized void hideBar() {
        mBottomLinearLayout.setVisibility(View.GONE);
        switchStatusBar(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }

    public void swithchBar() {
        if (mBottomLinearLayout.getVisibility() == View.GONE) {
            showBar();
        } else {
            hideBar();
        }
    }


    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            mProgressbar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    private class MyWebClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ScreenUtil.getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if (mBottomLinearLayout.getVisibility() == View.VISIBLE) {
                hideBar();
            } else if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(URL, url);
        outState.putString(TITLE, CHAPTER_TITLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        url = savedInstanceState.getString(URL);
        CHAPTER_TITLE = savedInstanceState.getString(TITLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null)
            mWebView.onPause();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null)
            mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null)
            mWebView.destroy();
    }
}
