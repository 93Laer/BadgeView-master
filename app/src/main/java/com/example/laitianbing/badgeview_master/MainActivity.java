package com.example.laitianbing.badgeview_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BadgeView badgeView = findViewById(R.id.tag_process);
        badgeView.showBadge("最新");

        //动态添加
        TextView button = findViewById(R.id.button);

        BadgeView.build(button)
                .setBadgeAnchorPosition(BadgeView.ANCHOR_LEFT_TOP)
                .setBadgeBgcolor(getResources().getColor(android.R.color.holo_green_light))
                .setBadgeBordercolor(getResources().getColor(android.R.color.white))
                .setBorderWidth(2)
                .setMarginHorizon(8)
                .setMarginVertical(2)
                .setBadgeTextcolor(getResources().getColor(android.R.color.holo_red_light))
                .setBadgeTextSize(14)
                .setPaddingH(12)
                .setPaddingV(7)
                .showBadge("啦");
    }
}
