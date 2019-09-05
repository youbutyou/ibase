package cn.ibase.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import cn.ibase.hello.activity.BaseActivity;
import cn.ibase.hello.util.NetworkUtil;

/**
 * 广播接收器
 */
public class CommonBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean netWorkState = NetworkUtil.isNetworkConnected(context);
            // 接口回调传过去状态的类型
            if (BaseActivity.netEvent != null)
                BaseActivity.netEvent.onNetChange(netWorkState);
        }
    }

    /**
     * 网络状态变化接口
     */
    public interface NetChangeListener {
        void onNetChange(boolean netWorkState);
    }
}
