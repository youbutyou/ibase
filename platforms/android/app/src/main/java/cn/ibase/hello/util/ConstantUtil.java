package cn.ibase.hello.util;

import android.Manifest;

/**
 * 常量工具类
 */
public class ConstantUtil {

    // 版本信息
    public static String APP_VERSION = "";



    /**
     * 当TEXTVIEWSIZE=1的时候，会显示系统标准字体大小，
     * 这个时候即使系统修改了字体大小，也不会影响到应用程序的字体大小。
     * 如果想要字体放大，设值其值>1即可。
     * 如果想要字体缩小，设值其值<1即可
     */
    public static Integer TEXTVIEWSIZE = 1;

    // 权限code
    public final static int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1001;


    /***********************************人脸识别******************************************/
    /****************************权限申请********************************/
    public static final String[] ARCFACE_NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };
    public static final int ARCFACE_ACTION_REQUEST_PERMISSIONS = 0x001;
    public static final int MAX_DETECT_NUM = 10;


}
