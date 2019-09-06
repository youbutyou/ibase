package cn.ibase.hello.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arcface.activity.ChooseFunctionActivity;
import com.arcface.activity.IrRegisterAndRecognizeActivity;
import com.arcface.activity.PreviewActivity;
import com.arcface.activity.RegisterAndRecognizeActivity;
import com.arcsoft.face.FaceEngine;

import cn.ibase.hello.R;
import cn.ibase.hello.tool.ArcfaceApi;
import cn.ibase.hello.util.AppUtil;
import cn.ibase.hello.util.ConstantUtil;
import cn.ibase.hello.util.NetworkUtil;
import cn.ibase.hello.util.PermissionUtil;

public class LoginActivity extends BaseActivity  implements View.OnClickListener{

    protected Button buttonOne;
    protected Button buttonTwo;
    protected Button buttonThree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);
        buttonThree = findViewById(R.id.button_three);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
    }

    @Override
    protected void init(){
        // 判断权限
        if (!PermissionUtil.hasPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            PermissionUtil.requestPermission(this, ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        }
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_one:
                ClickButtonOne();
                break;
            case R.id.button_two:
                ClickButtonTwo();
                break;
            case R.id.button_three:
                ClickButtonThree();
                break;
            default:
                break;
        }

    }

    /**
     * 测试按钮One
     */
    protected void ClickButtonOne(){
        startActivity(new Intent(this, ChooseFunctionActivity.class));

    }
    protected void ClickButtonTwo(){
        startActivity(new Intent(this, IrRegisterAndRecognizeActivity.class));
    }
    protected void ClickButtonThree(){
        startActivity(new Intent(this, RegisterAndRecognizeActivity.class));
    }

}
