package cn.ibase.hello.tool;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.arcface.common.Constants;
import com.arcface.util.ConfigUtil;
import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.VersionInfo;

import cn.ibase.hello.R;
import cn.ibase.hello.util.ConstantUtil;
import cn.ibase.hello.util.PermissionUtil;
import cn.ibase.hello.util.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 人脸识别方法
 * 8hJB2aN6LCPyhvGFFYE5hY9KQBXP46yFDMtY8fuDFaB4
 * BsGENEbZuKRXnTRxRjxRMZwi2meedxMNFVRnyrwTf5uS
 */
public class arcfaceApi {
    private static final String TAG = "arcfaceApi";

    // 引擎对象
    private static FaceEngine faceEngine = new FaceEngine();

    // 返回标识
    private static int afCode = -1;

    /**
     * 视频模式检测配置
     * @param activity
     * @param asp
     *          FaceEngine.ASF_OP_0_ONLY等
     */
    public static void configEngine(Activity activity, int asp){
        ConfigUtil.setFtOrient(activity, asp);
    }

    /**
     * 激活引擎
     * @param activity
     */
    public static void activeEngine(Activity activity){
        if(!PermissionUtil.hasPermission(activity, ConstantUtil.ARCFACE_NEEDED_PERMISSIONS)){
            PermissionUtil.requestPermission(activity, ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE, ConstantUtil.ARCFACE_NEEDED_PERMISSIONS);
            return;
        }
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int activeCode = faceEngine.activeOnline(activity, Constants.APP_ID, Constants.SDK_KEY);
                emitter.onNext(activeCode);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer activeCode) {
                if (activeCode == ErrorInfo.MOK) {
                    ToastUtil.show(activity, activity.getString(R.string.active_success));
                } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
                    ToastUtil.show(activity, activity.getString(R.string.already_activated));
                } else {
                    ToastUtil.show(activity, activity.getString(R.string.active_failed, activeCode));
                }
                ActiveFileInfo activeFileInfo = new ActiveFileInfo();
                int res = faceEngine.getActiveFileInfo(activity,activeFileInfo);
                if (res == ErrorInfo.MOK) {
                    Log.i(TAG, activeFileInfo.toString());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 初始化引擎
     * @param activity
     */
    public static void initEngine(Activity activity) {
        afCode = faceEngine.init(activity, FaceEngine.ASF_DETECT_MODE_VIDEO, ConfigUtil.getFtOrient(activity),
                16, ConstantUtil.MAX_DETECT_NUM, FaceEngine.ASF_FACE_RECOGNITION | FaceEngine.ASF_FACE_DETECT | FaceEngine.ASF_LIVENESS);
        VersionInfo versionInfo = new VersionInfo();
        faceEngine.getVersion(versionInfo);
        Log.i(TAG, "initEngine:  init: " + afCode + "  version:" + versionInfo);

        if (afCode != ErrorInfo.MOK) {
            Toast.makeText(activity, activity.getString(R.string.init_failed, afCode), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 销毁引擎
     */
    public static void unInitEngine() {
        if(null == faceEngine){
            return;
        }
        afCode = faceEngine.unInit();
        Log.i(TAG, "unInitEngine: " + afCode);
    }

}
