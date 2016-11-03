package com.yuansip.testlaunchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.yuansip.testlaunchmode", "com.yuansip.testlaunchmode.ThirdActivity");
                startActivity(intent);
            }
        });
        setTitle(getPackageName() + getClass().getSimpleName());
    }
}
