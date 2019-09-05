package cn.ibase.hello.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.ibase.hello.R;
import cn.ibase.hello.util.AppUtil;

public class LoginActivity extends BaseActivity {

    protected Button buttonOne;
    protected Button buttonTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButtonOne();
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButtonTwo();
            }
        });
    }

    /**
     * 测试按钮One
     */
    protected void ClickButtonOne(){
        String version = AppUtil.getVersion(this.getApplicationContext());
        Toast.makeText(this, "version=" + version, Toast.LENGTH_SHORT).show();

    }
    protected void ClickButtonTwo(){
        Toast.makeText(this, "点击测试two", Toast.LENGTH_SHORT).show();
    }

}
