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
        prophets.setOnClickListener(v -> showProphets());

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

    void showSettings() {
        currentPage = "settings";
        base("⚙️ الإعدادات");

        TextView info = title("إعدادات تطبيق نور", 20);
        info.setTextColor(Color.WHITE);
        content.addView(info);

        TextView darkMode = new TextView(this);
        darkMode.setText("🌙 الوضع الليلي
المظهر الداكن مفعل");
        darkMode.setTextColor(Color.WHITE);
        darkMode.setTextSize(18);
        darkMode.setGravity(Gravity.RIGHT);
        darkMode.setPadding(20, 25, 20, 25);
        darkMode.setBackground(cardBackground(Color.rgb(15,27,31), gold, 18));
        content.addView(darkMode);

        TextView about = new TextView(this);
        about.setText("ℹ️ حول نور\n\nمطور التطبيق: علاء العمراني\nala alamrany");
        about.setTextColor(Color.WHITE);
        about.setTextSize(17);
        about.setGravity(Gravity.RIGHT);
        about.setPadding(20, 25, 20, 25);
        about.setBackground(cardBackground(Color.rgb(15,27,31), gold, 18));
        content.addView(about);
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
        currentPage = "hadith";
        base("📜 الأحاديث النبوية");

        String[][] hs = {
            {"إنما الأعمال بالنيات، وإنما لكل امرئ ما نوى",
             "صحيح البخاري 1"},
            {"من لا يرحم لا يُرحم",
             "صحيح البخاري 5997"},
            {"المسلم من سلم المسلمون من لسانه ويده",
             "صحيح البخاري 10"},
            {"لا يؤمن أحدكم حتى يحب لأخيه ما يحب لنفسه",
             "صحيح البخاري 13"},
            {"يسروا ولا تعسروا، وبشروا ولا تنفروا",
             "صحيح البخاري 69"},
            {"الدين النصيحة",
             "صحيح مسلم 55a"},
            {"خيركم من تعلم القرآن وعلمه",
             "صحيح البخاري 5027"}
        };

        for (String[] h : hs) {
            TextView card = new TextView(this);
            card.setText("📜  " + h[0] + "\n\n📚 " + h[1]);
            card.setTextColor(Color.WHITE);
            card.setTextSize(18);
            card.setGravity(Gravity.RIGHT);
            card.setPadding(20,22,20,22);
            card.setBackground(
                cardBackground(Color.rgb(15,27,31),gold,18)
            );

            LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(-1,-2);
            lp.setMargins(5,7,5,7);
            card.setLayoutParams(lp);

            content.addView(card);
        }
    }

    void showProphets() {
        currentPage = "prophets";
        base("📚 قصص الأنبياء");

        TextView intro = title(
            "قصص مختصرة مستندة إلى ما ورد في القرآن الكريم",
            18
        );
        intro.setTextColor(gold);
        intro.setGravity(Gravity.CENTER);
        content.addView(intro);

        String[][] stories = {
            {"آدم عليه السلام",
             "خلق الله آدم وأسجد له الملائكة، ثم تاب عليه بعد توبته.",
             "البقرة 30-37 • طه 115-123"},

            {"نوح عليه السلام",
             "دعا قومه إلى عبادة الله، وصنع السفينة بأمر الله، فنجاه الله ومن معه من المؤمنين.",
             "هود 25-49 • نوح 1-28"},

            {"هود عليه السلام",
             "دعا قوم عاد إلى توحيد الله وترك الشرك، فكذبوه فنجى الله هودًا والذين آمنوا معه.",
             "الأعراف 65-72 • هود 50-60"},

            {"صالح عليه السلام",
             "دعا ثمود إلى عبادة الله، وجعل الله لهم الناقة آية، فكذبوا وعقروا الناقة.",
             "الأعراف 73-79 • هود 61-68"},

            {"إبراهيم عليه السلام",
             "دعا قومه إلى التوحيد، واحتج عليهم في أمر الأصنام، وجعل الله النار عليه بردًا وسلامًا.",
             "الأنبياء 51-70"},

            {"لوط عليه السلام",
             "دعا قومه إلى ترك الفواحش والعودة إلى طاعة الله، فنجاه الله وأهلك المكذبين.",
             "هود 77-83 • العنكبوت 28-35"},

            {"إسماعيل عليه السلام",
             "وصفه الله بالصبر وصدق الوعد، وذكره مع أهل بيته في عبادتهم لله.",
             "مريم 54-55 • البقرة 125-129"},

            {"إسحاق عليه السلام",
             "بشر الله إبراهيم وسارة بإسحاق، وذكره من الصالحين المباركين.",
             "هود 71-73 • الصافات 112-113"},

            {"يعقوب عليه السلام",
             "ابتلي بفقد يوسف فصبر، وأوصى أبناءه بالتوحيد، ثم جمع الله بينه وبين يوسف.",
             "يوسف 18 • يوسف 83-101"},

            {"يوسف عليه السلام",
             "رأى رؤيا، ومر بابتلاءات متعددة، ثم مكن الله له في الأرض وجمعه بأهله.",
             "سورة يوسف 1-101"},

            {"شعيب عليه السلام",
             "دعا قومه إلى عبادة الله وإيفاء الكيل والميزان وعدم الفساد في الأرض.",
             "الأعراف 85-93 • هود 84-95"},

            {"أيوب عليه السلام",
             "ابتلاه الله فصبر، ودعا ربه، فكشف الله عنه الضر ورد عليه نعمته.",
             "الأنبياء 83-84 • ص 41-44"},

            {"موسى عليه السلام",
             "أرسله الله إلى فرعون، وأيده بآياته، ونجى به بني إسرائيل من فرعون.",
             "طه 9-79 • الشعراء 10-68"},

            {"هارون عليه السلام",
             "كان أخا موسى وسانده في دعوة فرعون، ودعا بني إسرائيل إلى طاعة الله.",
             "طه 29-36 • طه 90-94"},

            {"داود عليه السلام",
             "آتاه الله الملك والحكمة، وأنزل عليه الزبور، وذكره بالصبر والعبادة.",
             "ص 17-26 • النساء 163"},

            {"سليمان عليه السلام",
             "آتاه الله الملك والحكمة، وسخر له من خلقه، وذكر القرآن قصته مع ملكة سبأ.",
             "النمل 15-44 • ص 30-40"},

            {"إلياس عليه السلام",
             "دعا قومه إلى عبادة الله وترك عبادة بعل.",
             "الصافات 123-132"},

            {"اليسع عليه السلام",
             "ذكره الله مع عدد من الأنبياء ووصفه من الأخيار.",
             "الأنعام 86 • ص 48"},

            {"يونس عليه السلام",
             "دعا ربه وهو في شدة، فاستجاب الله له ونجاه من الغم.",
             "الأنبياء 87-88 • الصافات 139-148"},

            {"زكريا عليه السلام",
             "دعا ربه سرًا، فبشره الله بيحيى.",
             "مريم 2-15 • آل عمران 37-41"},

            {"يحيى عليه السلام",
             "آتاه الله الحكم صبيًا، ووصفه بالبر والتقوى والصلاح.",
             "مريم 12-15 • آل عمران 39"},

            {"عيسى عليه السلام",
             "ولد من مريم بمعجزة، وأيده الله بالآيات، ودعا بني إسرائيل إلى عبادة الله.",
             "آل عمران 45-55 • مريم 16-36"},

            {"محمد ﷺ",
             "خاتم النبيين، أرسله الله بالهدى ودين الحق، وبلغ الرسالة ودعا إلى عبادة الله.",
             "الأحزاب 40 • الفتح 29 • الأنبياء 107"}
        };

        for (String[] story : stories) {
            TextView card = new TextView(this);
            card.setText(
                "🌙  " + story[0] +
                "\n\n" + story[1] +
                "\n\n📖 " + story[2]
            );
            card.setTextColor(Color.WHITE);
            card.setTextSize(17);
            card.setGravity(Gravity.RIGHT);
            card.setPadding(20,22,20,22);
            card.setBackground(
                cardBackground(Color.rgb(15,27,31),gold,18)
            );

            LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(-1,-2);
            lp.setMargins(5,7,5,7);
            card.setLayoutParams(lp);

            content.addView(card);
        }
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
}
