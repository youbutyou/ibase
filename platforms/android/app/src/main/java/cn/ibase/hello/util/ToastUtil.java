package cn.ibase.hello.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * 弹框工具类
 */
public class ToastUtil {

    /**
     * 弹框
     * @param message
     */
    public static void show(Activity activity,String message){
        Log.i("ToastUtil", message);
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
