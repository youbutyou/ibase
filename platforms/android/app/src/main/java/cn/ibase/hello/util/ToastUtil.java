package cn.ibase.hello.util;

import android.app.Activity;
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
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
