package cn.ibase.hello.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppUtil {

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
