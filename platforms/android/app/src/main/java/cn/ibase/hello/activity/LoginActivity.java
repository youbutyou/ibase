package cn.ibase.hello.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.ibase.hello.R;
import cn.ibase.hello.util.AppUtil;

public class LoginActivity extends AppCompatActivity {

    protected Button buttonOne;
    protected Button buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonOne = (Button) findViewById(R.id.button_one);
        buttonTwo = (Button) findViewById(R.id.button_two);
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
