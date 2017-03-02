package xi.lsl.lib.update;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import net.wequick.small.Bundle;
import net.wequick.small.Small;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xi.lsl.lib.update.update.Update;
import xi.lsl.lib.update.update.UpdateInfo;

public class UpdateManger {
    private ExecutorService executorService;
    private static final String UPDATE_URL = "http://192.168.0.5:8080/lishoulin/bundles.json";

    private UpdateLinsenter updateLinsenter;
    private int UPDATE_PID = 0;  //当前任务;
    private int UPDATE_COUNT = 0; //任务总量
    private Handler mHandler;

    public UpdateManger(final UpdateLinsenter updateLinsenter) {
        executorService = Executors.newFixedThreadPool(3);
        this.updateLinsenter = updateLinsenter;
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0x1:  //进度监控
                        UPDATE_PID++;
                        if (UPDATE_PID >= UPDATE_COUNT) {
                            if (updateLinsenter != null) {
                                updateLinsenter.success("success");
                            }
                        }
                        Log.e("info-->", "UPDATE_PID:" + UPDATE_PID + " UPDATE_COUNT:" + UPDATE_COUNT);
                        break;
                    case 0x2: //抛出异常
                        if (updateLinsenter != null) {
                            updateLinsenter.faild((String) msg.obj);
                        }
                        break;
                    case 0x3:
                        if (updateLinsenter != null) {
                            updateLinsenter.check();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void checkUpdate(Map<String, Integer> version) {
        mHandler.sendEmptyMessage(0x3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = getConnection(UPDATE_URL);
                if (connection != null) {
                    StringBuilder builder = new StringBuilder();
                    InputStream inputStream = null;
                    try {
                        inputStream = connection.getInputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            builder.append(new String(buffer, 0, len));
                        }
                        Gson gson = new Gson();
                        UpdateInfo info = gson.fromJson(builder.toString(), UpdateInfo.class);

                        Log.e("info--->", info.toString() + "version:" + info.manifest.version);
                        UPDATE_COUNT = info.updates.size();
                        updateBundles(info.updates);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Message.obtain(mHandler, 0x2, e.getMessage());
                    } finally {
                        try {
                            if (inputStream != null)
                                inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

    }


    private void updateBundles(List<Update> updates) {
        if (updates != null && updates.size() > 0) {
            for (Update u : updates) {
                executorService.execute(new Dowloader(u));

            }
        }
    }


    private class Dowloader implements Runnable {

        Update update;

        public Dowloader(Update update) {
            this.update = update;
        }

        @Override
        public void run() {
            try {
                dowload(update);
            } catch (Exception e) {
                e.printStackTrace();
                Message.obtain(mHandler, 0x2, e.getMessage()).sendToTarget();
            }
        }

        private void dowload(Update u) throws Exception {
            Bundle bundle = Small.getBundle(u.pkg);
            File file = bundle.getPatchFile();

            HttpURLConnection con = getConnection(u.url);
            InputStream is = con.getInputStream();
            OutputStream os = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len;
            Log.e("info-->", "start dowload runing");
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            os.flush();
            os.close();
            is.close();

            bundle.upgrade();
            mHandler.sendEmptyMessage(0x1);
        }
    }

    private HttpURLConnection getConnection(String url) {
        HttpURLConnection connection;
        try {
            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            if (connection.getResponseCode() == 200) {
                return connection;
            } else {
                Message.obtain(mHandler, 0x2, "connect faild erro code:" + connection.getResponseCode()).sendToTarget();
            }
        } catch (IOException e) {
            Message.obtain(mHandler, 0x2, "connect faild " + e.getMessage()).sendToTarget();
            e.printStackTrace();
        }
        return null;
    }

    ;


    public interface UpdateLinsenter {
        void check();

        void success(String msg);

        void faild(String msg);

    }

    public void restartApplication(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

}