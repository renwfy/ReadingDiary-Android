package com.renwfy.readingdiary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

public class ViewToImageUtil {
    private static final int defaultWebPage = 6;
    public interface UtilCallback {
        void onSuccess(Bitmap bitmap);

        void onFali();
    }

    /***
     * webview转bitmap
     * @param context
     * @param webView
     */
    public static Bitmap webViewToBitmap(final Context context, final WebView webView) {
        if (context == null || webView == null) {
            return  null;
        }
        int screenHeight = getScreenHeight(context);
        int MAXScreenshotHeight = defaultWebPage * screenHeight;
        float scale = webView.getScale();
        int webViewHeight = (int) (webView.getContentHeight() * scale + 0.5);
        if (webViewHeight > MAXScreenshotHeight) {
            webViewHeight = MAXScreenshotHeight;
        }
        Bitmap bitmap = Bitmap.createBitmap(webView.getWidth(), webViewHeight, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        webView.draw(canvas);
        return  bitmap;
    }

    public static Bitmap webViewToBitmap(final WebView webView){
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),snapShot.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        canvas.save();
        canvas.restore();
        webView.destroyDrawingCache();
        return  bmp;
    }


    private static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }


    /***
     * 布局文件直接转bitmap
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();

        view.layout(0, 0, width, height);
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bitmap);

        view.draw(c);
        return bitmap;
    }

    public static Bitmap viewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static Bitmap viewToBitmap(View comBitmap, int width, int height) {
        Bitmap bitmap = null;
        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);

            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);

            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, width, height);

            comBitmap.buildDrawingCache();
            Bitmap cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }
        return bitmap;
    }


    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }
}
