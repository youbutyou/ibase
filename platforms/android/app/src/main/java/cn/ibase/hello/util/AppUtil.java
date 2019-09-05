package cn.ibase.hello.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * app属性工具类
 */
public class AppUtil {

    /**
     * 样式
     * @param activity
     */
    public static void titleStyle(Activity activity){

        // 隐藏标题栏
        /*activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 沉浸效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
    }

    /**
     * 获取应用版本号
     * @param context
     * @return
     */
    public static String getVersion(Context context){
        String versionName = "0";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
