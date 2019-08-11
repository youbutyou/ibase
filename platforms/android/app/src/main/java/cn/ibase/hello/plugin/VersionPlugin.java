package cn.ibase.hello.plugin;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

import cn.ibase.hello.util.ConstUtil;

/**
 * 自定义cordovaPlugin
 * 获取版本号
 */
public class VersionPlugin extends CordovaPlugin {

    protected String getVersion(){
        return ConstUtil.APP_VERSION;
    }

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        if(action != null && action.equals("getVersion")){
            String content = getVersion();
            callbackContext.success(content);//如果不调用success回调，则js中successCallback不会执行
            return true;
        }
        callbackContext.error("error func");
        return false;
    }
}
