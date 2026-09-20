
package com.noornew.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 40, 30, 40);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("🌙 نور");
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.rgb(20, 80, 60));

        TextView welcome = new TextView(this);
        welcome.setText("تطبيق إسلامي خفيف يعمل بدون إنترنت");
        welcome.setTextSize(18);
        welcome.setGravity(Gravity.CENTER);
        welcome.setPadding(0, 20, 0, 30);

        layout.addView(title);
        layout.addView(welcome);

        addButton("📖 القرآن الكريم", "القرآن الكريم");
        addButton("🤲 الأذكار والأدعية", "الأذكار والأدعية");
        addButton("📿 المسبحة", "المسبحة الإلكترونية");
        addButton("🕌 مواقيت الصلاة", "مواقيت الصلاة");
        addButton("🧭 القبلة", "اتجاه القبلة");
        addButton("⚙️ الإعدادات", "الإعدادات");

        TextView about = new TextView(this);
        about.setText("\n👨‍💻 مطور التطبيق: علاء العمراني\nala alamrany");
        about.setTextSize(15);
        about.setGravity(Gravity.CENTER);
        about.setTextColor(Color.DKGRAY);

        layout.addView(about);

        setContentView(layout);
    }

    private void addButton(String text, final String message) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(17);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 8, 0, 8);

        button.setLayoutParams(params);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        message + " — سيتم تطوير القسم",
                        Toast.LENGTH_SHORT).show();
            }
        });

        layout.addView(button);
    }
}
