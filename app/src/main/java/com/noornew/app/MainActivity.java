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

    String[] adhkar = {
        "سبحان الله",
        "الحمد لله",
        "الله أكبر",
        "لا إله إلا الله",
        "أستغفر الله",
        "لا حول ولا قوة إلا بالله",
        "سبحان الله وبحمده",
        "سبحان الله العظيم",
        "اللهم صل وسلم على نبينا محمد"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private ScrollView createPage() {
        ScrollView scroll = new ScrollView(this);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 30);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(Color.rgb(247, 250, 248));

        scroll.addView(layout);
        return scroll;
    }

    private TextView title(String text) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(30);
        view.setTextColor(Color.rgb(20, 105, 75));
        view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        view.setGravity(Gravity.CENTER);
        view.setPadding(10, 20, 10, 25);
        return view;
    }

    private TextView content(String text) {
        TextView view = new TextView(this);
        view.setText(text);
        view.setTextSize(19);
        view.setTextColor(Color.DKGRAY);
        view.setGravity(Gravity.RIGHT);
        view.setPadding(15, 15, 15, 15);
        return view;
    }

    private Button menuButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(18);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 6, 0, 6);
        button.setLayoutParams(params);

        return button;
    }

    private void addBack() {
        Button button = menuButton("⬅️ الرئيسية");
        button.setOnClickListener(v -> showHome());
        layout.addView(button);
    }

    private void showHome() {

        ScrollView scroll = createPage();

        layout.addView(title("🌙 نور"));

        TextView intro = content(
                "تطبيق إسلامي\n\n" +
                "📖 القرآن • 🤲 الأذكار • 📿 المسبحة\n" +
                "📜 الأحاديث • 🕌 الصلاة • ✨ أسماء الله"
        );
        intro.setGravity(Gravity.CENTER);
        layout.addView(intro);

        Button quran = menuButton("📖 القرآن الكريم");
        quran.setOnClickListener(v -> showQuran());
        layout.addView(quran);

        Button azkar = menuButton("🤲 الأذكار والأدعية");
        azkar.setOnClickListener(v -> showAzkar());
        layout.addView(azkar);

        Button tasbeeh = menuButton("📿 المسبحة");
        tasbeeh.setOnClickListener(v -> showTasbeeh());
        layout.addView(tasbeeh);

        Button hadith = menuButton("📜 الأحاديث");
        hadith.setOnClickListener(v -> showHadith());
        layout.addView(hadith);

        Button prayer = menuButton("🕌 مواقيت الصلاة");
        prayer.setOnClickListener(v -> showPrayer());
        layout.addView(prayer);

        Button names = menuButton("✨ أسماء الله الحسنى");
        names.setOnClickListener(v -> showNames());
        layout.addView(names);

        layout.addView(content(
                "\n👨‍💻 مطور التطبيق\n" +
                "علاء العمراني\n" +
                "ala alamrany"
        ));

        setContentView(scroll);
    }

    private void showQuran() {

        ScrollView scroll = createPage();

        layout.addView(title("📖 القرآن الكريم"));

        layout.addView(content(
                "سور القرآن الكريم\n\n" +
                "1 الفاتحة\n2 البقرة\n3 آل عمران\n4 النساء\n" +
                "5 المائدة\n6 الأنعام\n7 الأعراف\n8 الأنفال\n" +
                "9 التوبة\n10 يونس\n11 هود\n12 يوسف\n" +
                "13 الرعد\n14 إبراهيم\n15 الحجر\n16 النحل\n" +
                "17 الإسراء\n18 الكهف\n19 مريم\n20 طه\n" +
                "21 الأنبياء\n22 الحج\n23 المؤمنون\n24 النور\n" +
                "25 الفرقان\n26 الشعراء\n27 النمل\n28 القصص\n" +
                "29 العنكبوت\n30 الروم\n31 لقمان\n32 السجدة\n" +
                "33 الأحزاب\n34 سبأ\n35 فاطر\n36 يس\n" +
                "37 الصافات\n38 ص\n39 الزمر\n40 غافر\n" +
                "41 فصلت\n42 الشورى\n43 الزخرف\n44 الدخان\n" +
                "45 الجاثية\n46 الأحقاف\n47 محمد\n48 الفتح\n" +
                "49 الحجرات\n50 ق\n51 الذاريات\n52 الطور\n" +
                "53 النجم\n54 القمر\n55 الرحمن\n56 الواقعة\n" +
                "57 الحديد\n58 المجادلة\n59 الحشر\n60 الممتحنة\n" +
                "61 الصف\n62 الجمعة\n63 المنافقون\n64 التغابن\n" +
                "65 الطلاق\n66 التحريم\n67 الملك\n68 القلم\n" +
                "69 الحاقة\n70 المعارج\n71 نوح\n72 الجن\n" +
                "73 المزمل\n74 المدثر\n75 القيامة\n76 الإنسان\n" +
                "77 المرسلات\n78 النبأ\n79 النازعات\n80 عبس\n" +
                "81 التكوير\n82 الانفطار\n83 المطففين\n84 الانشقاق\n" +
                "85 البروج\n86 الطارق\n87 الأعلى\n88 الغاشية\n" +
                "89 الفجر\n90 البلد\n91 الشمس\n92 الليل\n93 الضحى\n" +
                "94 الشرح\n95 التين\n96 العلق\n97 القدر\n" +
                "98 البينة\n99 الزلزلة\n100 العاديات\n101 القارعة\n" +
                "102 التكاثر\n103 العصر\n104 الهمزة\n105 الفيل\n" +
                "106 قريش\n107 الماعون\n108 الكوثر\n109 الكافرون\n" +
                "110 النصر\n111 المسد\n112 الإخلاص\n113 الفلق\n114 الناس\n\n" +

                "سورة الفاتحة\n\n" +
                "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\n\n" +
                "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ\n\n" +
                "الرَّحْمَٰنِ الرَّحِيمِ\n\n" +
                "مَالِكِ يَوْمِ الدِّينِ\n\n" +
                "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ\n\n" +
                "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ"
        ));

        addBack();
        setContentView(scroll);
    }

    private void showAzkar() {

        ScrollView scroll = createPage();

        layout.addView(title("🤲 الأذكار والأدعية"));

        layout.addView(content(
                "🌅 أذكار الصباح والمساء\n\n" +
                "سبحان الله وبحمده.\n\n" +
                "أستغفر الله وأتوب إليه.\n\n" +
                "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير.\n\n" +
                "أعوذ بكلمات الله التامات من شر ما خلق.\n\n" +
                "رضيت بالله ربًا وبالإسلام دينًا وبمحمد ﷺ نبيًا.\n\n" +
                "لا حول ولا قوة إلا بالله.\n\n" +
                "سبحان الله العظيم.\n\n" +
                "اللهم صل وسلم على نبينا محمد.\n\n" +
                "🤲 أدعية\n\n" +
                "ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار.\n\n" +
                "رب اشرح لي صدري ويسر لي أمري.\n\n" +
                "رب زدني علمًا.\n\n" +
                "اللهم اغفر لي وارحمني واهدني وعافني وارزقني."
        ));

        addBack();
        setContentView(scroll);
    }

    private void showTasbeeh() {

        ScrollView scroll = createPage();

        layout.addView(title("📿 المسبحة"));

        TextView counter = content("العدد: " + count);
        counter.setTextSize(32);
        counter.setGravity(Gravity.CENTER);
        layout.addView(counter);

        for (String dhikr : adhkar) {
            Button b = menuButton("📿 " + dhikr);

            b.setOnClickListener(v -> {
                count++;
                counter.setText("العدد: " + count);
            });

            layout.addView(b);
        }

        Button reset = menuButton("🔄 تصفير");
        reset.setOnClickListener(v -> {
            count = 0;
            counter.setText("العدد: 0");
        });

        layout.addView(reset);

        addBack();
        setContentView(scroll);
    }

    private void showHadith() {

        ScrollView scroll = createPage();

        layout.addView(title("📜 الأحاديث"));

        layout.addView(content(
                "أحاديث نبوية مختارة\n\n" +
                "إنما الأعمال بالنيات — رواه البخاري ومسلم.\n\n" +
                "من لا يرحم لا يُرحم — رواه البخاري ومسلم.\n\n" +
                "المسلم من سلم المسلمون من لسانه ويده — رواه البخاري ومسلم.\n\n" +
                "خيركم من تعلم القرآن وعلمه — رواه البخاري.\n\n" +
                "الكلمة الطيبة صدقة — رواه البخاري ومسلم."
        ));

        addBack();
        setContentView(scroll);
    }

    private void showPrayer() {

        ScrollView scroll = createPage();

        layout.addView(title("🕌 مواقيت الصلاة"));

        layout.addView(content(
                "الصلوات الخمس\n\n" +
                "🌅 الفجر\n\n" +
                "☀️ الشروق\n\n" +
                "🕌 الظهر\n\n" +
                "🌤️ العصر\n\n" +
                "🌇 المغرب\n\n" +
                "🌙 العشاء\n\n" +
                "مواقيت الصلاة تختلف حسب المدينة والتاريخ، " +
                "ولذلك لا أضع أوقاتًا ثابتة قد تكون خاطئة."
        ));

        addBack();
        setContentView(scroll);
    }

    private void showNames() {

        ScrollView scroll = createPage();

        layout.addView(title("✨ أسماء الله الحسنى"));

        layout.addView(content(
                "الله\nالرحمن\nالرحيم\nالملك\nالقدوس\nالسلام\n" +
                "المؤمن\nالمهيمن\nالعزيز\nالجبار\nالمتكبر\n" +
                "الخالق\nالبارئ\nالمصور\nالغفار\nالوهاب\n" +
                "الرزاق\nالفتاح\nالعليم\nالسميع\nالبصير\n" +
                "الغفور\nالشكور\nالحليم\nالعظيم"
        ));

        addBack();
        setContentView(scroll);
    }
}
