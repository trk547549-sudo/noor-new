package com.noornew.app;

import android.app.AlarmManager;
import android.app.BroadcastReceiver;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class AdhanReceiver extends BroadcastReceiver {

    private static MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!"NOOR_ADHAN".equals(intent.getAction())) {
            return;
        }

        android.content.SharedPreferences prefs =
            context.getSharedPreferences(
                "noor_settings",
                Context.MODE_PRIVATE
            );

        boolean enabled =
            prefs.getBoolean("adhan_enabled", true);

        if (!enabled) {
            return;
        }

        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(
                context.getApplicationContext(),
                R.raw.adhan
            );

            if (mediaPlayer != null) {

                mediaPlayer.setOnCompletionListener(mp -> {
                    mp.release();
                    mediaPlayer = null;
                });

                mediaPlayer.start();
            }

        } catch (Exception ignored) {
        }
    }
}
