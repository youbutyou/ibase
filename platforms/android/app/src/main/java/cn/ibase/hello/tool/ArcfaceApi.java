package cn.ibase.hello.tool;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.arcface.activity.IrRegisterAndRecognizeActivity;
import com.arcface.activity.RegisterAndRecognizeActivity;
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
public class ArcfaceApi {
    private static final String TAG = "ArcfaceApi";

    // 引擎对象
    private static FaceEngine faceEngine = new FaceEngine();
    // 返回标识
    private static int afCode = -1;


    public static FaceEngine getFaceEngine() {
        if(null == faceEngine){
            faceEngine = new FaceEngine();
        }
        return faceEngine;
    }

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
                int activeCode = getFaceEngine().activeOnline(activity, Constants.APP_ID, Constants.SDK_KEY);
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
                int res = getFaceEngine().getActiveFileInfo(activity,activeFileInfo);
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


    public static void IrRecognize(Activity activity){
        activity.startActivity(new Intent(activity, IrRegisterAndRecognizeActivity.class));
    }

    public static void Recognize(Activity activity){
        activity.startActivity(new Intent(activity, RegisterAndRecognizeActivity.class));
    }


}
