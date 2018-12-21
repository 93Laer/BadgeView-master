package com.example.laitianbing.badgeview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BadgeView badgeView = findViewById(R.id.tag_process);
        badgeView.setBadgeText("最新");
    }
}
