package cn.ibase.hello.activity;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.Toast;

import org.apache.cordova.CordovaActivity;

import cn.ibase.hello.receiver.CommonBroadcastReceiver;
import cn.ibase.hello.util.ActivityUtil;
import cn.ibase.hello.util.AppUtil;
import cn.ibase.hello.util.ConstantUtil;

public abstract class BaseActivity extends CordovaActivity implements CommonBroadcastReceiver.NetChangeListener{

    public static CommonBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件
    private long exitTime = 0;      // 记录双击点击时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 样式
        AppUtil.titleStyle(this);

        // 添加管理器
        ActivityUtil.getInstance().addActivity(this);

        // 初始化netEvent
        netEvent = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 移除管理器
        ActivityUtil.getInstance().removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监听返回按钮，关闭当前页面，返回上一层
            this.finish();
            ActivityUtil.getInstance().removeActivity(this);
        }
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 5000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 更改程序字体大小
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = ConstantUtil.TEXTVIEWSIZE;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    /*******************************************************************/
    /*********************** 权限请求 ****************************/

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE:// 读取手机信息权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限请求成功
                    Toast.makeText(this, "权限请求成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 权限请求失败
                    Toast.makeText(this, "权限请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*******************************************************************/
    /*********************** 网络监听 ****************************/
    public void onNetChange(boolean netWorkState){
        Toast.makeText(this, netWorkState ? "有网络" : "无网络", Toast.LENGTH_SHORT).show();
    }
    /*******************************************************************/
}
