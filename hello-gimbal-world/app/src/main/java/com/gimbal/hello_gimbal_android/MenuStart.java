package com.gimbal.hello_gimbal_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuStart extends Activity {
    Button buttonLLC;
    Button buttonMenuStrt;
    ImageButton back;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_start);

        buttonLLC = findViewById(R.id.TurnOnLL);
        buttonMenuStrt = findViewById(R.id.AccessMenu);
        back = findViewById(R.id.Back_Button);
        home = findViewById(R.id.Home_Button);


        buttonLLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStart.this, MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStart.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStart.this, MenuStart.class);
                startActivity(intent);
            }
        });
    }

}
