package com.gimbal.hello_gimbal_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MenuStart extends Activity {
    Button buttonLLC;
    Button buttonMenuStrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_start);

        buttonLLC = findViewById(R.id.TurnOnLL);
        buttonMenuStrt = findViewById(R.id.AccessMenu);

        buttonLLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStart.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
