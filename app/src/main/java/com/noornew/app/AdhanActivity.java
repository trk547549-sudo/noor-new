package com.noornew.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class AdhanActivity extends Activity {

    private static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        String prayerName =
            getIntent().getStringExtra("prayer_name");

        if (prayerName == null || prayerName.isEmpty()) {
            prayerName = "الصلاة";
        }

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(30, 40, 30, 40);
        root.setBackgroundColor(Color.rgb(10, 25, 23));

        TextView title = new TextView(this);
        title.setText("🕌 نور الهدى");
        title.setTextColor(Color.WHITE);
        title.setTextSize(30);
        title.setGravity(Gravity.CENTER);

        TextView prayer = new TextView(this);
        prayer.setText(
            "حان الآن وقت صلاة\n" + prayerName
        );
        prayer.setTextColor(Color.WHITE);
        prayer.setTextSize(28);
        prayer.setGravity(Gravity.CENTER);
        prayer.setPadding(10, 40, 10, 40);

        TextView subtitle = new TextView(this);
        subtitle.setText("🔔 الأذان");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(22);
        subtitle.setGravity(Gravity.CENTER);

        Button stop = new Button(this);
        stop.setText("⏹️ إيقاف الأذان");
        stop.setTextSize(20);
        stop.setAllCaps(false);

        stop.setOnClickListener(v -> {
            stopAdhan();
            finish();
        });

        root.addView(title);
        root.addView(prayer);
        root.addView(subtitle);

        LinearLayout.LayoutParams buttonParams =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        buttonParams.setMargins(20, 40, 20, 20);

        root.addView(stop, buttonParams);

        setContentView(root);

        // الصوت يتم تشغيله من AdhanReceiver فقط.
    }

    private void startAdhan() {
        try {
            stopAdhan();

            mediaPlayer = MediaPlayer.create(
                getApplicationContext(),
                R.raw.adhan
            );

            if (mediaPlayer != null) {
                mediaPlayer.setOnCompletionListener(mp -> {
                    mp.release();
                    mediaPlayer = null;
                    finish();
                });

                mediaPlayer.start();
            }

        } catch (Exception ignored) {
        }
    }

    private void stopAdhan() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
