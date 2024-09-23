package com.example.luluchef.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luluchef.R;

public class MainActivity extends AppCompatActivity {

    Animation startingApp;
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startingApp = AnimationUtils.loadAnimation(this,R.anim.starting);
        appName = findViewById(R.id.appName);
        appName.startAnimation(startingApp);
        appName.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HostedActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3500);

    }
}