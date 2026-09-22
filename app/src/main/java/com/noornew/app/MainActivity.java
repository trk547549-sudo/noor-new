package com.noornew.app;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class MainActivity extends Activity {

    LinearLayout root, content;
    int gold = Color.rgb(205,165,70);
    int dark = Color.rgb(18,28,25);
    String currentPage = "home";

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
        b.setTextSize(16);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setTextDirection(View.TEXT_DIRECTION_RTL);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setSingleLine(false);
        b.setMaxLines(3);
        b.setMinHeight(105);
        b.setIncludeFontPadding(true);
        b.setPadding(8,10,8,10);
        b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        b.setBackground(cardBackground(Color.rgb(17,29,33),gold,22));
        return b;
    }

    GradientDrawable cardBackground(int color,int stroke,int radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        g.setStroke(2,stroke);
        return g;
    }

    void base(String head) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        GradientDrawable bg = new GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            new int[]{
                Color.rgb(7,15,18),
                Color.rgb(18,31,34),
                Color.rgb(9,18,20)
            });
        root.setBackground(bg);

        TextView header = title(head,24);
        header.setTextColor(gold);
        header.setPadding(12,16,12,16);
        root.addView(header);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(8,4,8,20);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(content);

        root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        setContentView(root);
    }

    void showHome() {
        base("نور");

        TextView logo = title("☾  نــور  ☽", 31);
        logo.setTextColor(gold);
        logo.setPadding(10,18,10,4);
        content.addView(logo);

        TextView sub = title("طريقك إلى رضا الله",17);
        sub.setTextColor(Color.LTGRAY);
        sub.setPadding(10,0,10,14);
        content.addView(sub);

        TextView welcome = title(
            "السلام عليكم\nاللهم اجعل هذا اليوم بداية خير لنا جميعًا",18);
        welcome.setTextColor(Color.WHITE);
        welcome.setPadding(20,18,20,18);
        welcome.setBackground(cardBackground(Color.rgb(15,27,31), gold, 24));
        content.addView(welcome);

        section("📖  القرآن والذكر");

        Button q = btn("📖\nالقرآن الكريم");
        q.setOnClickListener(v -> showQuran());

        Button a = btn("📿\nالأذكار");
        a.setOnClickListener(v -> showAdhkar());

        addRow(q,a);

        section("🕌  العبادات");

        Button prayer = btn("🕌\nالصلاة\nمواقيت الصلاة");
        prayer.setOnClickListener(v -> showPrayer());

        Button tasbeeh = btn("📿\nالمسبحة\nاذكر الله");
        tasbeeh.setOnClickListener(v -> showTasbeeh());

        addRow(prayer,tasbeeh);

        Button namesBtn = btn("✨\nأسماء الله الحسنى");
        namesBtn.setOnClickListener(v -> showNames());

        Button qib = btn("🕋\nالقبلة\nاتجاه القبلة");
        qib.setOnClickListener(v ->
            Toast.makeText(this,"اتجاه القبلة قيد التطوير",Toast.LENGTH_SHORT).show());

        addRow(namesBtn,qib);

        section("📚  السنة والسيرة");

        Button hadith = btn("📜\nالأحاديث النبوية");
        hadith.setOnClickListener(v -> showHadith());

        Button seerah = btn("🌟\nالسيرة النبوية");
        seerah.setOnClickListener(v ->
            Toast.makeText(this,"السيرة النبوية قيد التطوير",Toast.LENGTH_SHORT).show());

        addRow(hadith,seerah);

        Button prophets = btn("📚\nقصص الأنبياء");
        prophets.setOnClickListener(v ->
            Toast.makeText(this,"قصص الأنبياء قيد التطوير",Toast.LENGTH_SHORT).show());

        Button hijri = btn("📅\nالتاريخ الهجري");
        hijri.setOnClickListener(v ->
            Toast.makeText(this,"التاريخ الهجري قيد التطوير",Toast.LENGTH_SHORT).show());

        addRow(prophets,hijri);

        section("🌙  نور في يومك");

        Button morning = btn("☀️\nأذكار الصباح");
        morning.setOnClickListener(v -> showAdhkar());

        Button evening = btn("🌙\nأذكار المساء");
        evening.setOnClickListener(v -> showAdhkar());

        addRow(morning,evening);

        Button ramadan = btn("🌙\nرمضان والصيام");
        ramadan.setOnClickListener(v ->
            Toast.makeText(this,"قسم رمضان والصيام قيد التطوير",Toast.LENGTH_SHORT).show());

        Button favorites = btn("❤️\nالمفضلة");
        favorites.setOnClickListener(v ->
            Toast.makeText(this,"المفضلة قيد التطوير",Toast.LENGTH_SHORT).show());

        addRow(ramadan,favorites);

        section("⚙️  التطبيق");

        Button settings = btn("⚙️\nالإعدادات");
        settings.setOnClickListener(v -> showSettings());

        Button about = btn("ℹ️\nحول نور");
        about.setOnClickListener(v ->
            new AlertDialog.Builder(this)
                .setTitle("🕌 نور")
                .setMessage("موسوعة إسلامية\n\nمطور التطبيق:\nعلاء العمراني\nala alamrany")
                .setPositiveButton("حسنًا",null)
                .show());

        addRow(settings,about);
    }

    void addRow(Button left, Button right) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);
        row.setPadding(2,3,2,3);

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(0,-2,1);
        lp.setMargins(5,5,5,5);

        left.setLayoutParams(new LinearLayout.LayoutParams(lp));
        right.setLayoutParams(new LinearLayout.LayoutParams(lp));

        row.addView(left);
        row.addView(right);
        content.addView(row);
    }

    void section(String text) {
        TextView t = title(text,19);
        t.setTextColor(gold);
        t.setGravity(Gravity.RIGHT);
        t.setPadding(12,22,12,7);
        content.addView(t);
    }

    @Override
    public void onBackPressed() {
        if (currentPage.equals("surah")) showQuran();
        else if (currentPage.equals("quran")) showHome();
        else showHome();
    }

    void showQuran() {
        currentPage = "quran";
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
    }

    void showSurah(String name) {
        currentPage = "surah";
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

    }

    void showSettings() {
        currentPage = "settings";
        base("⚙️ الإعدادات");

        TextView t = title("📖 حجم خط القرآن",20);
        t.setTextColor(gold);
        content.addView(t);

        TextView preview = new TextView(this);
        preview.setText("بِسْمِ اللَّهِ الرَّحْمَـٰنِ الرَّحِيمِ");
        preview.setTextColor(Color.WHITE);
        preview.setTextSize(24);
        preview.setGravity(Gravity.CENTER);
        preview.setPadding(15,25,15,25);
        content.addView(preview);

        SeekBar size = new SeekBar(this);
        size.setMax(30);
        size.setProgress(12);
        content.addView(size);

        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar b,int p,boolean f) {
                preview.setTextSize(12 + p);
            }
            public void onStartTrackingTouch(SeekBar b) {}
            public void onStopTrackingTouch(SeekBar b) {}
        });

        Button plus = btn("＋ تكبير الخط");
        Button minus = btn("－ تصغير الخط");
        addRow(plus,minus);

        plus.setOnClickListener(v -> {
            float x=preview.getTextSize()/getResources().getDisplayMetrics().scaledDensity;
            preview.setTextSize(x+2);
        });

        minus.setOnClickListener(v -> {
            float x=preview.getTextSize()/getResources().getDisplayMetrics().scaledDensity;
            if(x>12) preview.setTextSize(x-2);
        });
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

    }

    void showPrayer() {
        currentPage = "prayer";
        base("🕌 مواقيت الصلاة - صنعاء");

        TextView loading = title("جاري تحميل مواقيت الصلاة...",18);
        loading.setTextColor(Color.WHITE);
        content.addView(loading);

        new Thread(() -> {
            try {
                java.text.SimpleDateFormat f =
                    new java.text.SimpleDateFormat("dd-MM-yyyy", java.util.Locale.US);
                String date = f.format(new java.util.Date());

                URL url = new URL(
                    "https://api.aladhan.com/v1/timingsByCity/" +
                    date + "?city=Sanaa&country=Yemen");

                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setConnectTimeout(10000);
                c.setReadTimeout(10000);

                BufferedReader r = new BufferedReader(
                    new InputStreamReader(c.getInputStream()));
                StringBuilder b = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) b.append(line);
                r.close();

                JSONObject rootJson = new JSONObject(b.toString());
                JSONObject timings =
                    rootJson.getJSONObject("data").getJSONObject("timings");

                String[] names = {
                    "الفجر","الشروق","الظهر","العصر","المغرب","العشاء"
                };
                String[] keys = {
                    "Fajr","Sunrise","Dhuhr","Asr","Maghrib","Isha"
                };

                runOnUiThread(() -> {
                    content.removeAllViews();

                    for (int i=0;i<names.length;i++) {
                        TextView t = new TextView(this);
                        try {
                            t.setText("🕌  " + names[i] + "   —   " +
                                      timings.getString(keys[i]));
                        } catch(Exception e) {
                            t.setText(names[i]);
                        }
                        t.setTextColor(Color.WHITE);
                        t.setTextSize(20);
                        t.setGravity(Gravity.CENTER);
                        t.setPadding(15,20,15,20);
                        t.setBackground(
                            cardBackground(Color.rgb(15,27,31),gold,18));
                        content.addView(t);

                        LinearLayout.LayoutParams lp =
                            new LinearLayout.LayoutParams(-1,-2);
                        lp.setMargins(5,5,5,5);
                        t.setLayoutParams(lp);
                    }

                    TextView info = title("📍 صنعاء - اليمن",16);
                    info.setTextColor(Color.LTGRAY);
                    content.addView(info);
                });

            } catch(Exception e) {
                runOnUiThread(() ->
                    loading.setText("تعذر تحميل المواقيت. تحقق من اتصال الإنترنت."));
            }
        }).start();
    }
