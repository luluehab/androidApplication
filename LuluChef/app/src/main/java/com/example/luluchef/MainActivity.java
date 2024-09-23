package com.example.luluchef;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                finish();
            }
        }, 5000);

    }
}