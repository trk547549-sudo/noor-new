package com.noornew.app;

import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

public class AdhanReceiver extends BroadcastReceiver {

    private static MediaPlayer mediaPlayer;

    private static final String CHANNEL_ID =
            "noor_adhan_channel";

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

        String prayerName =
                intent.getStringExtra("prayer_name");

        if (prayerName == null || prayerName.isEmpty()) {
            prayerName = "الصلاة";
        }

        createNotificationChannel(context);

        Intent screenIntent =
                new Intent(context, AdhanActivity.class);

        screenIntent.putExtra(
                "prayer_name",
                prayerName
        );

        screenIntent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP
        );

        PendingIntent fullScreenIntent =
                PendingIntent.getActivity(
                        context,
                        prayerName.hashCode(),
                        screenIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_IMMUTABLE
                );

        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(
                    context,
                    CHANNEL_ID
            );
        } else {
            builder = new Notification.Builder(context);
        }

        builder
                .setSmallIcon(R.drawable.ic_noor)
                .setContentTitle(
                        "🕌 حان وقت صلاة " + prayerName
                )
                .setContentText(
                        "اضغط لفتح شاشة الأذان"
                )
                .setAutoCancel(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setPriority(Notification.PRIORITY_MAX)
                .setFullScreenIntent(
                        fullScreenIntent,
                        true
                );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility(
                    Notification.VISIBILITY_PUBLIC
            );
        }

        NotificationManager manager =
                (NotificationManager)
                        context.getSystemService(
                                Context.NOTIFICATION_SERVICE
                        );

        if (manager != null) {
            manager.notify(
                    prayerName.hashCode(),
                    builder.build()
            );
        }

        // تشغيل الأذان هنا فقط.
        // AdhanActivity تعرض الشاشة ولا تشغل نسخة ثانية.
        try {

            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            mediaPlayer = MediaPlayer.create(
                    context.getApplicationContext(),
                    R.raw.adhan
            );

            if (mediaPlayer != null) {

                mediaPlayer.setOnCompletionListener(
                        mp -> {
                            mp.release();
                            mediaPlayer = null;
                        }
                );

                mediaPlayer.start();
            }

        } catch (Exception ignored) {
        }
    }

    private static void createNotificationChannel(
            Context context
    ) {

        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "أذان الصلاة",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            channel.setDescription(
                    "تنبيهات مواقيت الصلاة والأذان"
            );

            channel.setLockscreenVisibility(
                    Notification.VISIBILITY_PUBLIC
            );

            NotificationManager manager =
                    context.getSystemService(
                            NotificationManager.class
                    );

            if (manager != null) {
                manager.createNotificationChannel(
                        channel
                );
            }
        }
    }
}
