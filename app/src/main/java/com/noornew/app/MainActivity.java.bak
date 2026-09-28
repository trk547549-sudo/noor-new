package com.noornew.app;

import android.app.*;
import android.os.*;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.Typeface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
        b.setTextSize(18);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setTextDirection(View.TEXT_DIRECTION_RTL);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setSingleLine(false);
        b.setMaxLines(3);
        b.setMinHeight(130);
        b.setIncludeFontPadding(true);
        b.setPadding(10,14,10,14);
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

        BitmapDrawable bg = new BitmapDrawable(
            getResources(),
            BitmapFactory.decodeResource(
                getResources(),
                R.drawable.noor_background
            )
        );
        bg.setGravity(Gravity.FILL);
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

    Button btn2(String main, String sub) {
        Button b = btn(main + "\\n" + sub);

        android.text.SpannableString sp =
            new android.text.SpannableString(main + "\\n" + sub);

        int startSub = main.length() + 1;

        sp.setSpan(
            new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            0, main.length(),
            android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        sp.setSpan(
            new android.text.style.RelativeSizeSpan(0.62f),
            startSub, sp.length(),
            android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        sp.setSpan(
            new android.text.style.ForegroundColorSpan(
                android.graphics.Color.rgb(210,220,205)
            ),
            startSub, sp.length(),
            android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        b.setText(sp);
        b.setGravity(android.view.Gravity.CENTER);
        b.setLineSpacing(2, 1.0f);
        return b;
    }

    void showHome() {
        base("🌙 نور الهدى");

        TextView logo = title("☾  نــور الهدى  ☽", 31);
        logo.setTextColor(gold);
        logo.setPadding(10,18,10,4);
        content.addView(logo);

        TextView sub = title("رفيقك إلى الطمأنينة وذكر الله",17);
        sub.setTextColor(Color.LTGRAY);
        sub.setPadding(10,0,10,14);
        content.addView(sub);

        TextView welcome = title(
            "السلام عليكم ورحمة الله وبركاته\\n\\nواذكر ربك إذا نسيت\\n\\nاجعل لسانك عامرًا بذكر الله",
            18
        );
        welcome.setTextColor(Color.WHITE);
        welcome.setPadding(20,18,20,18);
        welcome.setBackground(cardBackground(Color.rgb(15,27,31), gold, 24));
        content.addView(welcome);

        Button q = btn2("📖 القرآن الكريم", "اقرأ واستمتع بالقرآن");
        q.setOnClickListener(v -> showQuran());

        Button a = btn2("📿 الأذكار والأدعية", "راحة للقلب والروح");
        a.setOnClickListener(v -> showAdhkar());
        addRow(q,a);

        Button prayer = btn2("🕌 مواقيت الصلاة", "مع تنبيه الأذان");
        prayer.setOnClickListener(v -> showPrayer());

        Button adhan = btn2("🔔 الأذان", "صلاتك في وقتها");
        adhan.setOnClickListener(v -> showPrayer());
        addRow(prayer,adhan);

        Button qib = btn2("🕋 القبلة", "اعرف اتجاه القبلة");
        qib.setOnClickListener(v ->
            Toast.makeText(this,"اتجاه القبلة قيد التطوير",Toast.LENGTH_SHORT).show());

        Button tasbeeh = btn2("📿 المسبحة", "سبح - أذكار - عدد");
        tasbeeh.setOnClickListener(v -> showTasbeeh());
        addRow(qib,tasbeeh);

        Button namesBtn = btn2("✨ أسماء الله الحسنى", "تعرف على أسماء الله");
        namesBtn.setOnClickListener(v -> showNames());

        Button hadith = btn2("📜 الأحاديث النبوية", "نور من السنة");
        hadith.setOnClickListener(v -> showHadith());
        addRow(namesBtn,hadith);

        Button prophets = btn2("📚 قصص الأنبياء", "عبر ودروس من حياتهم");
        prophets.setOnClickListener(v -> showProphets());

        Button hijri = btn2("📅 التاريخ الهجري", "اعرف تاريخك الهجري");
        hijri.setOnClickListener(v ->
            Toast.makeText(this,"التاريخ الهجري قيد التطوير",Toast.LENGTH_SHORT).show());
        addRow(prophets,hijri);

        Button morning = btn2("☀️ أذكار الصباح", "ابدأ يومك بذكر الله");
        morning.setOnClickListener(v -> showAdhkar());

        Button evening = btn2("🌙 أذكار المساء", "اختم يومك بذكر الله");
        evening.setOnClickListener(v -> showAdhkar());
        addRow(morning,evening);

        Button settings = btn2("⚙️ الإعدادات", "تحكم في تجربتك");
        settings.setOnClickListener(v -> showSettings());

        Button about = btn2("ℹ️ حول التطبيق", "نور الهدى");
        about.setOnClickListener(v ->
            new AlertDialog.Builder(this)
                .setTitle("🌙 نور الهدى")
                .setMessage(
                    "تطبيق إسلامي شامل\\n\\n" +
                    "مطور التطبيق:\\n" +
                    "علاء العمراني\\n" +
                    "ala alamrany"
                )
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
        darkMode.setText("🌙 الوضع الليلي\nالمظهر الداكن مفعل");
        darkMode.setTextColor(Color.WHITE);
        darkMode.setTextSize(18);
        darkMode.setGravity(Gravity.RIGHT);
        darkMode.setPadding(20, 25, 20, 25);
        darkMode.setBackground(cardBackground(Color.rgb(15,27,31), gold, 18));
        content.addView(darkMode);

        android.widget.Switch adhanSwitch =
            new android.widget.Switch(this);

        adhanSwitch.setText("🔔 أذان الصلاة والتنبيهات");
        adhanSwitch.setTextColor(Color.WHITE);
        adhanSwitch.setTextSize(18);
        adhanSwitch.setGravity(Gravity.RIGHT);
        adhanSwitch.setPadding(20, 25, 20, 25);
        adhanSwitch.setBackground(
            cardBackground(Color.rgb(15,27,31), gold, 18)
        );

        android.content.SharedPreferences prefs =
            getSharedPreferences("noor_settings", MODE_PRIVATE);

        adhanSwitch.setChecked(
            prefs.getBoolean("adhan_enabled", true)
        );

        adhanSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
            prefs.edit()
                .putBoolean("adhan_enabled", isChecked)
                .apply()
        );

        content.addView(adhanSwitch);

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
        row.setPadding(4,5,4,5);

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(0,-2,1);
        lp.setMargins(7,7,7,7);

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
        if (currentPage.equals("surah")) {
            showQuran();
        } else if (currentPage.equals("quran")) {
            showHome();
        } else {
            showHome();
        }
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
        base("🕌 مواقيت الصلاة");

        TextView loading = title("جاري تحديد الموقع وتحميل المواقيت...",18);
        loading.setTextColor(Color.WHITE);
        content.addView(loading);

        android.location.LocationManager lm =
            (android.location.LocationManager) getSystemService(LOCATION_SERVICE);

        boolean fine =
            checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            == android.content.pm.PackageManager.PERMISSION_GRANTED;

        boolean coarse =
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == android.content.pm.PackageManager.PERMISSION_GRANTED;

        if (!fine && !coarse) {
            requestPermissions(
                new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                },
                1001
            );

            loading.setText("📍 اسمح للتطبيق بالوصول إلى موقعك لحساب مواقيت الصلاة.");
            return;
        }

        new Thread(() -> {
            try {
                android.location.Location location = null;

                if (fine) {
                    try {
                        location = lm.getLastKnownLocation(
                            android.location.LocationManager.GPS_PROVIDER);
                    } catch (Exception ignored) {}
                }

                if (location == null) {
                    try {
                        location = lm.getLastKnownLocation(
                            android.location.LocationManager.NETWORK_PROVIDER);
                    } catch (Exception ignored) {}
                }

                String city = "Sanaa";
                String country = "Yemen";
                String locationText = "📍 صنعاء - اليمن";

                if (location != null) {
                    android.location.Geocoder geocoder =
                        new android.location.Geocoder(
                            this, java.util.Locale.getDefault());

                    try {
                        java.util.List<android.location.Address> addresses =
                            geocoder.getFromLocation(
                                location.getLatitude(),
                                location.getLongitude(),
                                1);

                        if (addresses != null && !addresses.isEmpty()) {
                            android.location.Address a = addresses.get(0);

                            if (a.getLocality() != null &&
                                !a.getLocality().isEmpty()) {
                                city = a.getLocality();
                            } else if (a.getSubAdminArea() != null) {
                                city = a.getSubAdminArea();
                            }

                            if (a.getCountryName() != null &&
                                !a.getCountryName().isEmpty()) {
                                country = a.getCountryName();
                            }

                            locationText = "📍 " + city + " - " + country;
                        }
                    } catch (Exception ignored) {}
                }

                java.text.SimpleDateFormat f =
                    new java.text.SimpleDateFormat(
                        "dd-MM-yyyy",
                        java.util.Locale.US);

                String date = f.format(new java.util.Date());

                URL url = new URL(
                    "https://api.aladhan.com/v1/timingsByCity/" +
                    date +
                    "?city=" +
                    java.net.URLEncoder.encode(city, "UTF-8") +
                    "&country=" +
                    java.net.URLEncoder.encode(country, "UTF-8"));

                HttpURLConnection c =
                    (HttpURLConnection) url.openConnection();

                c.setRequestMethod("GET");
                c.setConnectTimeout(10000);
                c.setReadTimeout(10000);

                BufferedReader r = new BufferedReader(
                    new InputStreamReader(c.getInputStream()));

                StringBuilder b = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    b.append(line);
                }

                r.close();
                c.disconnect();

                JSONObject rootJson =
                    new JSONObject(b.toString());

                JSONObject timings =
                    rootJson.getJSONObject("data")
                           .getJSONObject("timings");

                String[] names = {
                    "الفجر",
                    "الشروق",
                    "الظهر",
                    "العصر",
                    "المغرب",
                    "العشاء"
                };

                String[] keys = {
                    "Fajr",
                    "Sunrise",
                    "Dhuhr",
                    "Asr",
                    "Maghrib",
                    "Isha"
                };

                final String displayLocation = locationText;
            runOnUiThread(() -> {
                    content.removeAllViews();

                    // جدولة الأذان للصلوات الخمس
                    scheduleAdhan("الفجر", timings.optString("Fajr"));
                    scheduleAdhan("الظهر", timings.optString("Dhuhr"));
                    scheduleAdhan("العصر", timings.optString("Asr"));
                    scheduleAdhan("المغرب", timings.optString("Maghrib"));
                    scheduleAdhan("العشاء", timings.optString("Isha"));

                    for (int i = 0; i < names.length; i++) {
                        TextView t = new TextView(this);

                        try {
                            String value =
                                timings.getString(keys[i]);

                            String[] parts = value.split(":");

                            int hour =
                                Integer.parseInt(parts[0]);

                            int minute =
                                Integer.parseInt(parts[1]);

                            String period =
                                hour >= 12 ? "م" : "ص";

                            int hour12 = hour % 12;

                            if (hour12 == 0) {
                                hour12 = 12;
                            }

                            String time =
                                String.format(
                                    java.util.Locale.getDefault(),
                                    "%02d:%02d %s",
                                    hour12,
                                    minute,
                                    period);

                            t.setText(
                                "🕌  " + names[i] +
                                "   —   " + time
                            );

                        } catch (Exception e) {
                            t.setText(names[i]);
                        }

                        t.setTextColor(Color.WHITE);
                        t.setTextSize(20);
                        t.setGravity(Gravity.CENTER);
                        t.setPadding(15,20,15,20);

                        t.setBackground(
                            cardBackground(
                                Color.rgb(15,27,31),
                                gold,
                                18)
                        );

                        content.addView(t);

                        LinearLayout.LayoutParams lp =
                            new LinearLayout.LayoutParams(-1,-2);

                        lp.setMargins(5,5,5,5);
                        t.setLayoutParams(lp);
                    }

                    TextView info =
                        title(displayLocation,16);

                    info.setTextColor(Color.LTGRAY);
                    content.addView(info);
                });

            } catch (Exception e) {
                runOnUiThread(() ->
                    loading.setText(
                        "تعذر تحميل المواقيت. تحقق من اتصال الإنترنت."
                    )
                );
            }
        }).start();
    }


    void scheduleAdhan(String prayerName, String time24) {
        try {
            String[] parts = time24.split(":");

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            if (cal.getTimeInMillis() <= System.currentTimeMillis()) {
                return;
            }

            Intent intent = new Intent(this, AdhanReceiver.class);
            intent.setAction("NOOR_ADHAN");

            intent.putExtra("prayer_name", prayerName);

            int requestCode = prayerName.hashCode();

            PendingIntent pendingIntent =
                PendingIntent.getBroadcast(
                    this,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT |
                    PendingIntent.FLAG_IMMUTABLE
                );

            AlarmManager alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);

            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (alarmManager.canScheduleExactAlarms()) {
                        alarmManager.setExactAndAllowWhileIdle(
                            AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(),
                            pendingIntent
                        );
                    }
                } else {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(),
                        pendingIntent
                    );
                }
            }

        } catch (Exception ignored) {
        }
    }

}
