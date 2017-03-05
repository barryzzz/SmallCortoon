package xi.lsl.code.lib.utils.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片加载封装
 * Created by lishoulin on 2017/2/14.
 */

public class Imageloader {

    public static void display(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }


    public static void display(Context context, String url, int width, int height, ImageView view) {
        Glide.with(context).load(url).override(width, height).into(view);
    }

    public static void displayThumbnail(Context context, String url, ImageView view) {
        Glide.with(context).load(url).thumbnail(0.1f).into(view);
    }


    public static void GlideClear(Context context) {
        Glide.get(context).clearMemory();
        Glide.get(context).clearDiskCache();
    }
}
