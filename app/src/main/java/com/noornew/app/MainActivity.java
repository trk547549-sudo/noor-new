package com.noornew.app;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.io.*;

public class MainActivity extends Activity {

    LinearLayout root, content;
    int gold = Color.rgb(205,165,70);
    int dark = Color.rgb(18,28,25);

    String[] surahs = {
        "الفاتحة","البقرة","آل عمران","النساء","المائدة","الأنعام","الأعراف",
        "الأنفال","التوبة","يونس","هود","يوسف","الرعد","إبراهيم","الحجر",
        "النحل","الإسراء","الكهف","مريم","طه","الأنبياء","الحج","المؤمنون",
        "النور","الفرقان","الشعراء","النمل","القصص","العنكبوت","الروم",
        "لقمان","السجدة","الأحزاب","سبأ","فاطر","يس","الصافات","ص","الزمر",
        "غافر","فصلت","الشورى","الزخرف","الدخان","الجاثية","الأحقاف","محمد",
        "الفتح","الحجرات","ق","الذاريات","الطور","النجم","القمر","الرحمن",
        "الواقعة","الحديد","المجادلة","الحشر","الممتحنة","الصف","الجمعة",
        "المنافقون","التغابن","الطلاق","التحريم","الملك","القلم","الحاقة",
        "المعارج","نوح","الجن","المزمل","المدثر","القيامة","الإنسان","المرسلات",
        "النبأ","النازعات","عبس","التكوير","الانفطار","المطففين","الانشقاق",
        "البروج","الطارق","الأعلى","الغاشية","الفجر","البلد","الشمس","الليل",
        "الضحى","الشرح","التين","العلق","القدر","البينة","الزلزلة","العاديات",
        "القارعة","التكاثر","العصر","الهمزة","الفيل","قريش","الماعون","الكوثر",
        "الكافرون","النصر","المسد","الإخلاص","الفلق","الناس"
    };

    String[] adhkar = {
        "سبحان الله","الحمد لله","الله أكبر","لا إله إلا الله",
        "أستغفر الله","سبحان الله وبحمده","سبحان الله العظيم",
        "لا حول ولا قوة إلا بالله","حسبي الله ونعم الوكيل",
        "اللهم صل وسلم على نبينا محمد","رب اغفر لي","اللهم اهدني",
        "اللهم ارزقني","اللهم احفظني","رب اشرح لي صدري",
        "رب زدني علما","اللهم أعني على ذكرك وشكرك وحسن عبادتك"
    };

    String[] names = {
        "الله","الرحمن","الرحيم","الملك","القدوس","السلام","المؤمن",
        "المهيمن","العزيز","الجبار","المتكبر","الخالق","البارئ","المصور",
        "الغفار","القهار","الوهاب","الرزاق","الفتاح","العليم","السميع",
        "البصير","الحكم","العدل","اللطيف","الخبير","الحليم","العظيم",
        "الغفور","الشكور","العلي","الكبير","الحفيظ","المقيت","الحسيب"
    };

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    TextView title(String t, int size) {
        TextView v = new TextView(this);
        v.setText(t);
        v.setTextColor(Color.WHITE);
        v.setTextSize(size);
        v.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        v.setGravity(Gravity.CENTER);
        v.setPadding(15,25,15,25);
        return v;
    }

    Button btn(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(17);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setTextDirection(View.TEXT_DIRECTION_RTL);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setSingleLine(false);
        b.setMaxLines(2);
        b.setMinHeight(64);
        b.setIncludeFontPadding(true);
        b.setPadding(16,12,16,12);
        b.setBackgroundColor(Color.rgb(35,65,55));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1,-2);
        p.setMargins(12,6,12,6);
        b.setLayoutParams(p);
        return b;
    }

    void base(String head) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(dark);

        root.addView(title(head,25));

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(10,5,10,15);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(content);
        root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));

        setContentView(root);
    }

    void showHome() {
        base("🕌 نور");

        TextView welcome = title(
            "نور\nموسوعة إسلامية", 26);
        welcome.setTextColor(Color.rgb(235,205,120));
        welcome.setPadding(10,20,10,8);
        content.addView(welcome);

        TextView sub = title(
            "كتاب الله • الذكر • العبادة • السنة", 16);
        sub.setTextColor(Color.LTGRAY);
        sub.setPadding(10,0,10,20);
        content.addView(sub);

        section("📖 القرآن والذكر");

        Button q = btn("📖 القرآن الكريم");
        q.setOnClickListener(v -> showQuran());
        content.addView(q);

        Button a = btn("🤲 الأذكار والأدعية");
        a.setOnClickListener(v -> showAdhkar());
        content.addView(a);

        Button n = btn("✨ أسماء الله الحسنى");
        n.setOnClickListener(v -> showNames());
        content.addView(n);

        Button adhMorning = btn("☀️ أذكار الصباح");
        adhMorning.setOnClickListener(v -> showAdhkar());
        content.addView(adhMorning);

        Button adhEvening = btn("🌙 أذكار المساء");
        adhEvening.setOnClickListener(v -> showAdhkar());
        content.addView(adhEvening);

        section("🕌 العبادات");

        Button s = btn("📿 المسبحة");
        s.setOnClickListener(v -> showTasbeeh());
        content.addView(s);

        Button p = btn("🕐 مواقيت الصلاة");
        p.setOnClickListener(v -> showPrayer());
        content.addView(p);

        Button qib = btn("🕋 القبلة");
        qib.setOnClickListener(v ->
            Toast.makeText(this,
                "سيتم استخدام بوصلة الهاتف لتحديد اتجاه القبلة",
                Toast.LENGTH_LONG).show());
        content.addView(qib);

        Button fasting = btn("🌙 الصيام ورمضان");
        fasting.setOnClickListener(v ->
            Toast.makeText(this,
                "قسم الصيام ورمضان قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(fasting);

        section("📚 السنة والسيرة");

        Button h = btn("📜 الأحاديث النبوية");
        h.setOnClickListener(v -> showHadith());
        content.addView(h);

        Button seerah = btn("🌟 السيرة النبوية");
        seerah.setOnClickListener(v ->
            Toast.makeText(this,
                "قسم السيرة النبوية قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(seerah);

        Button prophets = btn("📚 قصص الأنبياء");
        prophets.setOnClickListener(v ->
            Toast.makeText(this,
                "قسم قصص الأنبياء قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(prophets);

        section("🌙 يومك مع نور");

        Button hijri = btn("📅 التاريخ الهجري");
        hijri.setOnClickListener(v ->
            Toast.makeText(this,
                "قسم التاريخ الهجري قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(hijri);

        Button dua = btn("🤍 دعاء اليوم");
        dua.setOnClickListener(v -> showAdhkar());
        content.addView(dua);

        Button favorites = btn("❤️ المفضلة");
        favorites.setOnClickListener(v ->
            Toast.makeText(this,
                "المفضلة قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(favorites);

        Button search = btn("🔎 البحث");
        search.setOnClickListener(v ->
            Toast.makeText(this,
                "البحث الشامل قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(search);

        section("⚙️ التطبيق");

        Button settings = btn("⚙️ الإعدادات");
        settings.setOnClickListener(v ->
            Toast.makeText(this,
                "الإعدادات قيد التطوير",
                Toast.LENGTH_SHORT).show());
        content.addView(settings);

        Button about = btn("ℹ️ حول نور");
        about.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                .setTitle("🕌 نور")
                .setMessage(
                    "موسوعة إسلامية\n\n" +
                    "مطور التطبيق:\n" +
                    "علاء العمراني\n" +
                    "ala alamrany")
                .setPositiveButton("حسنًا", null)
                .show();
        });
        content.addView(about);
    }

    void section(String text) {
        TextView t = title(text, 19);
        t.setTextColor(Color.rgb(235,205,120));
        t.setGravity(Gravity.RIGHT);
        t.setPadding(18,24,18,8);
        content.addView(t);
    }

    void showQuran() {
        base("القرآن الكريم");
        TextView info = title("سور القرآن الكريم",20);
        info.setTextColor(Color.rgb(235,205,120));
        content.addView(info);

        for (int i=0;i<surahs.length;i++) {
            final String name=surahs[i];
            Button b=btn((i+1)+" - سورة "+name);
            b.setOnClickListener(v -> showSurah(name));
            content.addView(b);
        }
        back();
    }

    void showSurah(String name) {
        base("📖 سورة " + name);

        TextView t = new TextView(this);
        t.setTextColor(Color.WHITE);
        t.setTextSize(20);
        t.setGravity(Gravity.RIGHT);
        t.setPadding(20, 20, 20, 20);

        StringBuilder text = new StringBuilder();

        try {
            int number = Arrays.asList(surahs).indexOf(name) + 1;
            BufferedReader r = new BufferedReader(
                new InputStreamReader(
                    getAssets().open("quran-simple.txt"), "UTF-8"
                )
            );

            String line;
            while ((line = r.readLine()) != null) {
                if (line.startsWith(number + "|")) {
                    String[] parts = line.split("\\|", 3);
                    if (parts.length == 3) {
                        text.append(parts[1])
                            .append(" — ")
                            .append(parts[2])
                            .append("\n\n");
                    }
                }
            }
            r.close();
        } catch (Exception e) {
            text.append("حدث خطأ أثناء قراءة القرآن.");
        }

        t.setText(text.toString());
        content.addView(t);
        back();
    }

    void showAdhkar() {
        base("🤲 الأذكار والأدعية");

        for(String x:adhkar) {
            TextView t=new TextView(this);
            t.setText("✦ "+x);
            t.setTextColor(Color.WHITE);
            t.setTextSize(20);
            t.setPadding(15,18,15,18);
            content.addView(t);
        }

        back();
    }

    void showHadith() {
        base("الأحاديث");

        String[] hs={
            "إنما الأعمال بالنيات — رواه البخاري ومسلم",
            "من لا يرحم لا يُرحم — رواه البخاري ومسلم",
            "المسلم من سلم المسلمون من لسانه ويده — رواه البخاري ومسلم",
            "يسروا ولا تعسروا وبشروا ولا تنفروا — رواه البخاري ومسلم",
            "الدين النصيحة — رواه مسلم"
        };

        for(String x:hs) {
            TextView t=new TextView(this);
            t.setText("📜 "+x);
            t.setTextColor(Color.WHITE);
            t.setTextSize(19);
            t.setPadding(15,20,15,20);
            content.addView(t);
        }

        back();
    }

    void showTasbeeh() {
        base("المسبحة");

        final TextView count=new TextView(this);
        count.setText("0");
        count.setTextColor(Color.rgb(235,205,120));
        count.setTextSize(55);
        count.setGravity(Gravity.CENTER);
        content.addView(count);

        Button plus=btn("📿 تسبيح");
        content.addView(plus);

        Button reset=btn("إعادة العداد");
        content.addView(reset);

        final int[] n={0};

        plus.setOnClickListener(v -> {
            n[0]++;
            count.setText(String.valueOf(n[0]));
        });

        reset.setOnClickListener(v -> {
            n[0]=0;
            count.setText("0");
        });

        back();
    }

    void showNames() {
        base("أسماء الله الحسنى");

        for(String x:names) {
            TextView t=new TextView(this);
            t.setText("﴿ "+x+" ﴾");
            t.setTextColor(Color.WHITE);
            t.setTextSize(21);
            t.setGravity(Gravity.CENTER);
            t.setPadding(10,12,10,12);
            content.addView(t);
        }

        back();
    }

    void showPrayer() {
        base("مواقيت الصلاة");

        String[] p={
            "الفجر","الشروق","الظهر","العصر","المغرب","العشاء"
        };

        for(String x:p) {
            TextView t=new TextView(this);
            t.setText("🕌 "+x+"   —   يُحسب حسب المدينة والتاريخ");
            t.setTextColor(Color.WHITE);
            t.setTextSize(18);
            t.setPadding(15,18,15,18);
            content.addView(t);
        }

        back();
    }
}
