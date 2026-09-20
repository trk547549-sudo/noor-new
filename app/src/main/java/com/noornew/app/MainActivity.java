package com.noornew.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("🌙 نور");
        title.setTextSize(32);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);

        TextView developer = new TextView(this);
        developer.setText("\nتطبيق نور\n\n👨‍💻 المطور: علاء العمراني\nala alamrany");
        developer.setTextSize(18);
        developer.setGravity(Gravity.CENTER);
        developer.setTextColor(Color.DKGRAY);

        layout.addView(title);
        layout.addView(developer);

        setContentView(layout);
    }
}
