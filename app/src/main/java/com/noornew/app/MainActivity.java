
package com.noornew.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    LinearLayout layout;
    int count = 0;

    int green = Color.rgb(20, 100, 75);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        home();
    }

    private ScrollView page() {
        ScrollView scroll = new ScrollView(this);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(25, 25, 25, 35);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(Color.WHITE);

        scroll.addView(layout);
        return scroll;
    }

    private TextView title(String s) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(29);
        t.setTextColor(green);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setGravity(Gravity.CENTER);
        t.setPadding(10, 20, 10, 25);
        return t;
    }

    private TextView text(String s) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(19);
        t.setTextColor(Color.DKGRAY);
        t.setGravity(Gravity.RIGHT);
        t.setPadding(10, 15, 10, 15);
        return t;
    }

    private Button button(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextSize(17);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        p.setMargins(0, 6, 0, 6);
        b.setLayoutParams(p);

        return b;
    }

    private void back() {
        Button b = button("⬅️ الرئيسية");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        layout.addView(b);
    }

    private void home() {

        ScrollView scroll = page();

        layout.addView(title("🌙 نور"));

        TextView welcome = text(
                "تطبيق إسلامي يعمل بدون إنترنت\n\n" +
                "القرآن الكريم والأذكار والمسبحة ومواقيت الصلاة"
        );
        welcome.setGravity(Gravity.CENTER);
        layout.addView(welcome);

        Button quran = button("📖 القرآن الكريم");
        quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quran();
            }
        });
        layout.addView(quran);

        Button azkar = button("🤲 الأذكار والأدعية");
        azkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                azkar();
            }
        });
        layout.addView(azkar);

        Button tasbeeh = button("📿 المسبحة");
        tasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasbeeh();
            }
        });
        layout.addView(tasbeeh);

        Button prayer = button("🕌 مواقيت الصلاة");
        prayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prayer();
            }
        });
        layout.addView(prayer);

        layout.addView(text(
                "\n👨‍💻 مطور التطبيق\n" +
                "علاء العمراني\n" +
                "ala alamrany"
        ));

        setContentView(scroll);
    }

    private void quran() {

        ScrollView scroll = page();

        layout.addView(title("📖 القرآن الكريم"));

        layout.addView(text(
                "سورة الفاتحة\n\n" +
                "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\n\n" +
                "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ\n\n" +
                "الرَّحْمَٰنِ الرَّحِيمِ\n\n" +
                "مَالِكِ يَوْمِ الدِّينِ\n\n" +
                "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ\n\n" +
                "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ\n\n" +
                "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ\n\n" +
                "غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ\n\n" +
                "وَلَا الضَّالِّينَ"
        ));

        back();
        setContentView(scroll);
    }

    private void azkar() {

        ScrollView scroll = page();

        layout.addView(title("🤲 الأذكار والأدعية"));

        layout.addView(text(
                "أذكار مختارة\n\n" +

                "سبحان الله\n\n" +
                "الحمد لله\n\n" +
                "الله أكبر\n\n" +
                "لا إله إلا الله\n\n" +
                "أستغفر الله\n\n" +
                "سبحان الله وبحمده\n\n" +
                "لا حول ولا قوة إلا بالله\n\n" +

                "دعاء:\n\n" +
                "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً " +
                "وَفِي الْآخِرَةِ حَسَنَةً " +
                "وَقِنَا عَذَابَ النَّارِ."
        ));

        back();
        setContentView(scroll);
    }

    private void tasbeeh() {

        ScrollView scroll = page();

        layout.addView(title("📿 المسبحة"));

        final TextView number = text("عدد التسبيحات: " + count);
        number.setTextSize(28);
        number.setGravity(Gravity.CENTER);
        layout.addView(number);

        Button add = button("📿 تسبيحة +1");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                number.setText("عدد التسبيحات: " + count);
            }
        });

        layout.addView(add);

        Button reset = button("🔄 تصفير");

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                number.setText("عدد التسبيحات: 0");
            }
        });

        layout.addView(reset);

        layout.addView(text(
                "\nسبحان الله\n" +
                "الحمد لله\n" +
                "الله أكبر"
        ));

        back();
        setContentView(scroll);
    }

    private void prayer() {

        ScrollView scroll = page();

        layout.addView(title("🕌 مواقيت الصلاة"));

        layout.addView(text(
                "مواقيت الصلاة\n\n" +
                "🌅 الفجر\n\n" +
                "☀️ الشروق\n\n" +
                "🕌 الظهر\n\n" +
                "🌤️ العصر\n\n" +
                "🌇 المغرب\n\n" +
                "🌙 العشاء\n\n" +
                "هذه الشاشة تعمل بدون إنترنت.\n" +
                "يمكن إضافة الحساب المحلي الدقيق حسب المدينة والتاريخ."
        ));

        back();
        setContentView(scroll);
    }
}
