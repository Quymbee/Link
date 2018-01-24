package com.gimbal.hello_gimbal_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class WelcomeScreen extends Activity {
    ImageButton linkLogo;
    ImageButton interLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        linkLogo = findViewById(R.id.Link_Logo_Button);
        interLogo = findViewById(R.id.Intersection_Logo_Button);

        linkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, MenuStart.class);
                startActivity(intent);
            }
        });

        interLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreen.this, MenuStart.class);
                startActivity(intent);
            }
        });

    }

}
