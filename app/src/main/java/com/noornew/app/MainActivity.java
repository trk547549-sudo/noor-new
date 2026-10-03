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
    private long lastBackPressTime = 0;

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
        v.setTextColor(currentPage.equals("home") ? Color.WHITE : Color.BLACK);
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
        boolean home = currentPage.equals("home");
        b.setTextColor(home ? Color.WHITE : Color.BLACK);
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
        if (currentPage.equals("home")) {
            b.setBackground(cardBackground(Color.rgb(17,29,33),gold,22));
        } else {
            b.setBackground(cardBackground(Color.WHITE,gold,22));
        }
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

        if (currentPage.equals("home")) {
            root.setBackground(bg);
        } else {
            root.setBackgroundColor(Color.WHITE);
        }

        TextView header = title(head,24);
        header.setTextColor(gold);
        header.setPadding(12,16,12,16);
        root.addView(header);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        if (!currentPage.equals("home")) {
            content.setBackgroundColor(Color.WHITE);
        }
        content.setPadding(8,4,8,20);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(content);

        root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        setContentView(root);
    }

    Button btn2(String main, String sub) {
        Button b = btn(main + "\n" + sub);

        android.text.SpannableString sp =
            new android.text.SpannableString(main + "\n" + sub);

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
        currentPage = "home";
        base("🌙 نور الهدى");

        TextView logo = title("☾  نــور الهدى  ☽", 31);
        logo.setTextColor(Color.WHITE);
        logo.setPadding(10,18,10,4);
        content.addView(logo);

        TextView sub = title("رفيقك إلى الطمأنينة وذكر الله",17);
        sub.setTextColor(Color.WHITE);
        sub.setPadding(10,0,10,14);
        content.addView(sub);

        TextView welcome = title(
            "السلام عليكم ورحمة الله وبركاته\n\n" +
            "﴿ وَاذْكُر رَّبَّكَ إِذَا نَسِيتَ ﴾\n\n" +
            "اجعل لسانك عامرًا بذكر الله",
            18
        );
        welcome.setTextColor(Color.WHITE);
        welcome.setPadding(20,18,20,18);
        welcome.setBackground(cardBackground(Color.rgb(15,27,31), gold, 24));
        content.addView(welcome);

        section("✦ الوصول السريع ✦");

        Button globalSearch = btn2(
            "🔎 البحث العام",
            "ابحث في محتوى نور الهدى"
        );
        globalSearch.setOnClickListener(v -> showGlobalSearch());
        content.addView(globalSearch);

        Button q = btn2("📖 القرآن الكريم", "اقرأ واستمتع بالقرآن");
        q.setOnClickListener(v -> showQuran());

        Button islamic = btn2(
            "📚 إسلاميات",
            "تعلم أمور دينك بطريقة سهلة"
        );
        islamic.setOnClickListener(v -> showIslamicTopics());

        Button a = btn2("📿 الأذكار والأدعية", "راحة للقلب والروح");
        a.setOnClickListener(v -> showAdhkar());
        addRow(q,islamic);

        Button tasbeeh = btn2("📿 المسبحة", "سبح - أذكار - عدد");
        tasbeeh.setOnClickListener(v -> showTasbeeh());

        addRow(a,tasbeeh);

        section("✦ خدمات نور الهدى ✦");

        Button prayer = btn2("🕌 مواقيت الصلاة", "مع تنبيه الأذان");
        prayer.setOnClickListener(v -> showPrayer());

        Button adhan = btn2("🔔 الأذان", "صلاتك في وقتها");
        adhan.setOnClickListener(v -> showPrayer());
        addRow(prayer,adhan);

        Button qib = btn2("🕋 القبلة", "اعرف اتجاه القبلة");
        qib.setOnClickListener(v ->
            Toast.makeText(this,"اتجاه القبلة قيد التطوير",Toast.LENGTH_SHORT).show());


        Button namesBtn = btn2("✨ أسماء الله الحسنى", "تعرف على أسماء الله");
        namesBtn.setOnClickListener(v -> showNames());

        Button hadith = btn2("📜 الأحاديث النبوية", "نور من السنة");
        hadith.setOnClickListener(v -> showHadith());
        addRow(namesBtn,hadith);

        Button prophets = btn2("📚 قصص الأنبياء", "عبر ودروس من حياتهم");
        prophets.setOnClickListener(v -> showProphets());

        Button hijri = btn2("📅 التاريخ الهجري", "اعرف تاريخك الهجري");
        hijri.setOnClickListener(v -> showHijriCalendar());
        addRow(prophets,hijri);

        Button dailyDua = btn2("\uD83E\uDD32 الدعاء اليومي", "دعاء جديد كل يوم");
        dailyDua.setOnClickListener(v -> showDailyDua());
        content.addView(dailyDua);

        section("✦ التطبيق ✦");

        Button settings = btn2("⚙️ الإعدادات", "تحكم في تجربتك");
        settings.setOnClickListener(v -> showSettings());

        Button about = btn2("ℹ️ حول التطبيق", "نور الهدى");
        about.setOnClickListener(v -> {
            TextView info = new TextView(this);
            info.setText(
                "تطبيق إسلامي شامل\\n\\n" +
                "مطور التطبيق:\\n" +
                "علاء العمراني\\n" +
                "ala alamrany\\n\\n" +
                "📧 hamdalmrany833@gmail.com"
            );
            info.setTextSize(18);
            info.setGravity(Gravity.CENTER);
            info.setPadding(40, 20, 40, 20);
            info.setAutoLinkMask(android.text.util.Linkify.EMAIL_ADDRESSES);
            info.setLinksClickable(true);
            info.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

            new AlertDialog.Builder(this)
                .setTitle("🌙 نور الهدى")
                .setView(info)
                .setPositiveButton("حسنًا", null)
                .show();
        });

        addRow(settings,about);
    }

    void showIslamicTopics() {
        currentPage = "islamic";
        base("📚 إسلاميات");

        TextView intro = title(
            "تعلم أمور دينك بسهولة",
            21
        );
        intro.setTextColor(Color.WHITE);
        intro.setGravity(Gravity.CENTER);
        intro.setTextDirection(View.TEXT_DIRECTION_RTL);
        intro.setPadding(10,10,10,20);
        content.addView(intro);

        addIslamicButton("🕌 أركان الإسلام",
            "أركان الإسلام خمسة...",
            "أركان الإسلام أساس العبادة والطاعة.");

        addIslamicButton("💎 أركان الإيمان",
            "الإيمان بالله وملائكته وكتبه ورسله واليوم الآخر والقدر.",
            "الإيمان أصل عظيم في حياة المسلم.");

        addIslamicButton("❤️ بر الوالدين",
            "الإحسان إلى الوالدين واحترامهما والكلام الطيب معهما ومساعدتهما.",
            "البر يكون بالرحمة والأدب والصبر.");

        addIslamicButton("⚠️ عقوق الوالدين",
            "الإساءة إلى الوالدين أو إيذاؤهما أو رفع الصوت عليهما من الأمور التي يجب على المسلم تجنبها.",
            "احرص دائمًا على الكلام الطيب وحسن التعامل.");

        addIslamicButton("🌙 فضل صيام رمضان",
            "رمضان شهر عظيم يكثر فيه المسلم من الصيام والصلاة والقرآن والذكر والصدقة.",
            "الصيام عبادة وتربية على التقوى والصبر.");

        addIslamicButton("📖 فضل القرآن",
            "القرآن الكريم كتاب الله، وقراءته وتدبره والعمل به من أبواب الخير.",
            "اجعل لك وردًا يوميًا من القرآن.");

        addIslamicButton("🤲 التوبة والاستغفار",
            "باب التوبة مفتوح، ومن أخطأ فليستغفر الله وليترك الذنب وليعزم على عدم العودة إليه.",
            "لا تيأس من رحمة الله وابدأ بخطوة صادقة نحو الخير.");

        addIslamicButton("🕌 الصلاة وأهميتها",
            "الصلاة عبادة عظيمة وهي من أهم أعمال المسلم اليومية.",
            "حافظ على صلاتك في أوقاتها واجعلها سببًا للطمأنينة.");

        addIslamicButton("🧼 الطهارة والوضوء",
            "الطهارة والوضوء من الأعمال المهمة التي يستعد بها المسلم للصلاة.",
            "تعلم الوضوء الصحيح وحافظ على النظافة والطهارة.");

        addIslamicButton("🌿 الأخلاق الإسلامية",
            "من الأخلاق الإسلامية الرحمة والصدق والتواضع والصبر وحسن التعامل مع الناس.",
            "اجعل أخلاقك الحسنة دليلًا على إيمانك.");

        addIslamicButton("🤝 الصدق والأمانة",
            "الصدق والأمانة من الأخلاق المهمة التي تجعل الإنسان موثوقًا ومحترمًا.",
            "كن صادقًا في كلامك وأمينًا في معاملاتك.");

        addIslamicButton("🏠 حقوق الجار",
            "من حسن التعامل مع الجار احترامه ومساعدته وعدم إيذائه والمحافظة على حقوقه.",
            "أحسن إلى جارك وتعاون معه في الخير.");
    }

    void addIslamicButton(
        String heading,
        String explanation,
        String tip
    ) {
        Button b = new Button(this);

        b.setText(heading);
        b.setTextSize(20);
        b.setTextColor(Color.WHITE);
        b.setGravity(Gravity.CENTER);
        b.setAllCaps(false);
        b.setTextDirection(View.TEXT_DIRECTION_RTL);
        b.setMinHeight(90);
        b.setPadding(18,18,18,18);

        b.setBackground(
            cardBackground(
                Color.rgb(15,27,31),
                gold,
                20
            )
        );

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        lp.setMargins(5,8,5,8);
        b.setLayoutParams(lp);

        b.setOnClickListener(v ->
            showIslamicTopic(
                heading,
                explanation,
                tip
            )
        );

        content.addView(b);
    }

    String getIslamicContent(String heading) {

        if (heading.contains("أركان الإسلام")) {
            return
                "📚 مقدمة\n\n" +
                "أركان الإسلام هي الأسس العظيمة التي يقوم عليها دين المسلم، وقد جعلها الإسلام عبادات وأعمالًا تربط العبد بربه وتربيه على الطاعة والانضباط والإحسان. وهي خمسة أركان: الشهادتان، وإقامة الصلاة، وإيتاء الزكاة، وصوم رمضان، وحج البيت لمن استطاع إليه سبيلًا.\n\n" +

                "1️⃣ الشهادتان\n\n" +
                "الشهادتان هما شهادة أن لا إله إلا الله وأن محمدًا رسول الله. ومعناهما توحيد الله تعالى وإفراده بالعبادة، والإيمان برسالة النبي محمد ﷺ واتباع ما جاء به.\n\n" +

                "2️⃣ الصلاة\n\n" +
                "الصلاة من أعظم عبادات الإسلام، وهي صلة يومية بين المسلم وربه. يحافظ المسلم عليها في أوقاتها ويؤديها بخشوع ويحاول أن يجعل أثرها ظاهرًا في أخلاقه وتصرفاته.\n\n" +

                "3️⃣ الزكاة\n\n" +
                "الزكاة عبادة مالية تطهر المال والنفس، وتساعد المحتاجين وتربي المسلم على البذل والشكر وعدم التعلق بالمال وحده.\n\n" +

                "4️⃣ صيام رمضان\n\n" +
                "الصيام عبادة عظيمة يتعلم فيها المسلم الصبر وضبط النفس وتقوى الله، ولا يقتصر الصيام على ترك الطعام والشراب، بل يشمل أيضًا حفظ اللسان والجوارح عن الخطأ.\n\n" +

                "5️⃣ الحج\n\n" +
                "الحج إلى بيت الله الحرام عبادة عظيمة تجتمع فيها أعمال كثيرة، وهو واجب على المسلم المستطيع مرة في العمر.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿وَأَقِيمُوا الصَّلَاةَ وَآتُوا الزَّكَاةَ وَارْكَعُوا مَعَ الرَّاكِعِينَ﴾\n" +
                "سورة البقرة: 43\n\n" +

                "💡 ماذا نتعلم؟\n\n" +
                "تعلم أركان الإسلام لا يكون بحفظ أسمائها فقط، بل بفهم معناها والعمل بها تدريجيًا. اجعل لكل عبادة مكانًا ثابتًا في يومك، وحافظ على الاستمرار ولو كان العمل قليلًا.";

        } else if (heading.contains("أركان الإيمان")) {
            return
                "📚 معنى الإيمان\n\n" +
                "الإيمان أساس عظيم في حياة المسلم، وهو اعتقاد القلب بالله وما أخبر به، وما يترتب على ذلك من أعمال صالحة وطاعة لله. ومن أصول الإيمان الإيمان بالله وملائكته وكتبه ورسله واليوم الآخر والقدر.\n\n" +

                "1️⃣ الإيمان بالله\n\n" +
                "يؤمن المسلم بأن الله واحد لا شريك له، وأنه الخالق والمالك والمدبر، ويعبده وحده ولا يجعل معه شريكًا.\n\n" +

                "2️⃣ الإيمان بالملائكة\n\n" +
                "الملائكة خلق من خلق الله، والإيمان بهم يكون بتصديق ما جاء في القرآن والسنة عنهم دون تجاوز ما ورد في النصوص.\n\n" +

                "3️⃣ الإيمان بالكتب والرسل\n\n" +
                "يؤمن المسلم بأن الله أرسل رسلًا إلى الناس وأنزل كتبًا لهداية البشر، ويؤمن بالرسل جميعًا ولا يفرق بينهم من حيث أصل الرسالة.\n\n" +

                "4️⃣ الإيمان باليوم الآخر\n\n" +
                "الإيمان بالآخرة يجعل المسلم يتذكر الحساب والجزاء، فيحرص على الخير ويتجنب الظلم والمعصية.\n\n" +

                "5️⃣ الإيمان بالقدر\n\n" +
                "يؤمن المسلم بأن الله يعلم كل شيء، ومع ذلك فهو مأمور بالعمل والأخذ بالأسباب وتحمل مسؤولية اختياراته.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿آمَنَ الرَّسُولُ بِمَا أُنزِلَ إِلَيْهِ مِن رَّبِّهِ وَالْمُؤْمِنُونَ﴾\n" +
                "سورة البقرة: 285\n\n" +

                "💡 فائدة\n\n" +
                "الإيمان الحقيقي يظهر أثره في حياة المسلم: صدقًا وأمانة ورحمة وصبرًا وخوفًا من ظلم الناس ورغبة في الخير.";

        } else if (heading.contains("بر الوالدين")) {
            return
                "❤️ مكانة الوالدين\n\n" +
                "بر الوالدين من الأخلاق والعبادات العظيمة. ويكون البر بالكلام الطيب والاحترام والمساعدة والصبر والإحسان، مع المحافظة على الأدب حتى عند الاختلاف.\n\n" +

                "👨‍👩‍👦 صور من البر\n\n" +
                "من البر أن يستمع المسلم إلى والديه، ويساعدهما فيما يستطيع، ويدعو لهما، ويحافظ على مشاعرهما، ولا يرفع صوته عليهما، ويشكرهما على ما قدماه له.\n\n" +

                "🌿 البر في الحياة اليومية\n\n" +
                "يمكن أن يكون البر بأعمال بسيطة جدًا: سؤال الوالدين عن حالهما، مساعدتهما في المنزل، تقديم شيء يحتاجانه، أو مجرد كلمة طيبة تدخل السرور إلى قلبيهما.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿وَبِالْوَالِدَيْنِ إِحْسَانًا﴾\n" +
                "سورة الإسراء: 23\n\n" +

                "💡 تذكر\n\n" +
                "الإحسان إلى الوالدين لا يحتاج إلى مال كثير؛ كثير من البر يكون بالأدب والرحمة والكلمة الطيبة والوفاء.";

        } else if (heading.contains("عقوق الوالدين")) {
            return
                "⚠️ ما هو العقوق؟\n\n" +
                "عقوق الوالدين هو الإساءة إليهما أو إيذاؤهما أو معاملتهما بفظاظة أو رفع الصوت عليهما أو تجاهل حقوقهما. ويجب على المسلم أن يحذر من كل تصرف يسبب لهما الأذى بغير حق.\n\n" +

                "🗣️ الكلام مع الوالدين\n\n" +
                "من أهم صور الاحترام اختيار الكلمات المناسبة، وعدم السخرية أو الإهانة أو الصراخ. وإذا غضب الإنسان فعليه أن يهدأ قبل أن يتكلم.\n\n" +

                "🤲 إذا أخطأت\n\n" +
                "إذا أخطأ المسلم في حق والديه فالأفضل أن يبادر بالاعتذار والإصلاح، وأن يتعلم من الخطأ ولا يكرر الإساءة.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿فَلَا تَقُل لَّهُمَا أُفٍّ وَلَا تَنْهَرْهُمَا وَقُل لَّهُمَا قَوْلًا كَرِيمًا﴾\n" +
                "سورة الإسراء: 23\n\n" +

                "💡 نصيحة\n\n" +
                "قبل أن ترد على والديك، فكر في نبرة صوتك وكلماتك. الاحترام لا يعني أن توافق على كل شيء، لكنه يعني أن تتعامل بأدب ورحمة.";

        } else if (heading.contains("رمضان")) {
            return
                "🌙 شهر رمضان\n\n" +
                "رمضان شهر عظيم يجتمع فيه الصيام والصلاة والقرآن والذكر والدعاء والصدقة. وهو فرصة لتربية النفس على الصبر وتقوى الله ومراجعة العادات اليومية.\n\n" +

                "🍽️ معنى الصيام\n\n" +
                "الصيام ليس مجرد الامتناع عن الطعام والشراب، بل هو تدريب للنفس على ضبط الشهوات وحفظ اللسان والعين والجوارح عن الخطأ.\n\n" +

                "📖 رمضان والقرآن\n\n" +
                "من أجمل الأعمال في رمضان تخصيص وقت ثابت لقراءة القرآن وتدبره، ويمكن للمسلم أن يضع لنفسه وردًا يوميًا يناسب قدرته.\n\n" +

                "🤲 الدعاء والصدقة\n\n" +
                "رمضان فرصة للإكثار من الدعاء ومساعدة المحتاجين وإدخال السرور على الآخرين، حتى بالأعمال البسيطة.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿كُتِبَ عَلَيْكُمُ الصِّيَامُ كَمَا كُتِبَ عَلَى الَّذِينَ مِن قَبْلِكُمْ لَعَلَّكُمْ تَتَّقُونَ﴾\n" +
                "سورة البقرة: 183\n\n" +

                "💡 فائدة\n\n" +
                "الهدف من الصيام ليس الجوع فقط، بل تربية النفس على التقوى والصبر وحسن الخلق.";

        } else if (heading.contains("فضل القرآن")) {
            return
                "📖 القرآن الكريم\n\n" +
                "القرآن الكريم كتاب الله الذي أنزله هداية للناس. وقراءة القرآن عبادة عظيمة، لكن الانتفاع به يزداد عندما يقرأ المسلم بتدبر ويحاول فهم المعاني والعمل بما يتعلمه.\n\n" +

                "🌿 كيف نعيش مع القرآن؟\n\n" +
                "يمكنك تخصيص وقت يومي للقراءة، والاستماع إلى التلاوة، ومراجعة معنى الآيات، واختيار آية واحدة للتفكر في أثرها على حياتك.\n\n" +

                "🧠 التدبر\n\n" +
                "التدبر يعني أن يتوقف المسلم عند الآيات ويتأمل ما فيها من توجيه وهداية، دون أن يتكلم في تفسير القرآن بغير علم.\n\n" +

                "📚 القرآن والأخلاق\n\n" +
                "من ثمار القرآن أن يتحسن سلوك الإنسان، فيصبح أكثر صدقًا ورحمة وصبرًا وأمانة وحسنًا في التعامل.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿إِنَّ هَٰذَا الْقُرْآنَ يَهْدِي لِلَّتِي هِيَ أَقْوَمُ﴾\n" +
                "سورة الإسراء: 9\n\n" +

                "💡 نصيحة\n\n" +
                "لا تجعل هدفك عدد الصفحات فقط؛ اجعل لك هدفًا في الفهم والعمل أيضًا.";

        } else if (heading.contains("التوبة")) {
            return
                "🤲 باب التوبة\n\n" +
                "الإنسان قد يخطئ، ولكن من رحمة الله أن جعل باب التوبة مفتوحًا. والتوبة الصادقة تكون بالندم على الذنب وتركه والعزم على عدم العودة إليه، ورد الحقوق إلى أصحابها إذا تعلق الأمر بحقوق الناس.\n\n" +

                "🌿 لا تيأس\n\n" +
                "لا ينبغي للإنسان أن يعتقد أن أخطاءه تمنعه من العودة إلى الله. المطلوب هو أن يبدأ بالإصلاح ويبتعد عن أسباب الخطأ ويستعين بالله.\n\n" +

                "🕊️ الاستغفار\n\n" +
                "الاستغفار عبادة عظيمة، ويمكن للمسلم أن يكثر منه في يومه، مع الحرص على إصلاح العمل وليس مجرد ترديد الكلمات.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿لَا تَقْنَطُوا مِن رَّحْمَةِ اللَّهِ﴾\n" +
                "سورة الزمر: 53\n\n" +

                "💡 خطوة عملية\n\n" +
                "إذا أخطأت، لا تؤجل الإصلاح: اعترف بخطئك، اتركه، أصلح ما تستطيع، وابدأ من جديد.";

        } else if (heading.contains("الصلاة")) {
            return
                "🕌 مكانة الصلاة\n\n" +
                "الصلاة من أعظم عبادات المسلم، وهي عبادة تتكرر في اليوم والليلة وتربي المسلم على النظام والخشوع ومراقبة الله.\n\n" +

                "⏰ المحافظة على الوقت\n\n" +
                "من المهم أن يتعلم المسلم أوقات الصلاة وأن يجعل لها مكانًا ثابتًا في يومه، وألا يجعل الأعمال الأخرى سببًا دائمًا لتأخيرها.\n\n" +

                "❤️ الخشوع\n\n" +
                "الخشوع يحتاج إلى تدريب. ومن أسبابه الاستعداد للصلاة، وفهم ما يقرأه المسلم، وتقليل المشتتات، وتذكر الوقوف بين يدي الله.\n\n" +

                "🌿 أثر الصلاة\n\n" +
                "الصلاة ليست حركة تؤدى ثم تنتهي، بل ينبغي أن يظهر أثرها في أخلاق المسلم وابتعاده عن الفحشاء والمنكر.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿إِنَّ الصَّلَاةَ كَانَتْ عَلَى الْمُؤْمِنِينَ كِتَابًا مَّوْقُوتًا﴾\n" +
                "سورة النساء: 103\n\n" +

                "💡 فائدة\n\n" +
                "اجعل الصلاة نقطة تنظيم ليومك، وليس شيئًا تحاول وضعه في آخر الوقت.";

        } else if (heading.contains("الطهارة")) {
            return
                "🧼 معنى الطهارة\n\n" +
                "الطهارة والنظافة من الأمور المهمة في حياة المسلم، والوضوء عبادة واستعداد للصلاة. ويتعلم المسلم أحكام الطهارة من مصادر العلم الموثوقة ويطبقها كما شرع الله.\n\n" +

                "💧 الوضوء\n\n" +
                "الوضوء عبادة معروفة قبل الصلاة عند الحاجة إليه، وفيه غسل ومسح لأعضاء معينة. ومن المهم تعلم صفته الصحيحة وعدم الإسراف في الماء.\n\n" +

                "🚿 النظافة\n\n" +
                "النظافة الشخصية والمحافظة على المكان والملابس من السلوكيات الحسنة، وينبغي للمسلم أن يهتم بنظافته دون إسراف أو تكلف.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿إِنَّ اللَّهَ يُحِبُّ التَّوَّابِينَ وَيُحِبُّ الْمُتَطَهِّرِينَ﴾\n" +
                "سورة البقرة: 222\n\n" +

                "💡 فائدة\n\n" +
                "اجعل النظافة عادة يومية، وتعلم أحكام الوضوء من مصدر موثوق إذا كنت غير متأكد من التفاصيل.";

        } else if (heading.contains("الأخلاق")) {
            return
                "🌿 الأخلاق الإسلامية\n\n" +
                "الأخلاق الحسنة تظهر في تعامل الإنسان مع أسرته وأصدقائه وجيرانه وكل من حوله. ومن الأخلاق المهمة الرحمة والصدق والتواضع والصبر والعفو واحترام الآخرين.\n\n" +

                "❤️ الرحمة\n\n" +
                "الرحمة تعني أن يتعامل الإنسان بلطف، وأن يراعي مشاعر الآخرين، ويساعد من يحتاج إلى المساعدة دون تكبر.\n\n" +

                "🗣️ الكلام الطيب\n\n" +
                "الكلمة قد ترفع معنويات إنسان أو تؤذيه، ولذلك ينبغي التفكير قبل الكلام، والابتعاد عن السخرية والتنمر والإهانة.\n\n" +

                "🧘 الصبر\n\n" +
                "الصبر لا يعني الاستسلام، بل يعني ضبط النفس والتصرف بحكمة عند الغضب والمشكلات.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿وَإِنَّكَ لَعَلَىٰ خُلُقٍ عَظِيمٍ﴾\n" +
                "سورة القلم: 4\n\n" +

                "💡 تطبيق يومي\n\n" +
                "اختر خلقًا واحدًا كل أسبوع وحاول تدريبه في حياتك، مثل الصدق أو الصبر أو مساعدة الآخرين.";

        } else if (heading.contains("الصدق")) {
            return
                "🤝 الصدق والأمانة\n\n" +
                "الصدق أن يقول الإنسان الحق ولا يتعمد خداع الآخرين، والأمانة أن يحافظ على الحقوق والمسؤوليات التي اؤتمن عليها. وهذان الخلقان من أسباب الثقة بين الناس.\n\n" +

                "🗣️ الصدق في الكلام\n\n" +
                "يشمل الصدق الحديث عن النفس والآخرين وعدم نشر الأخبار غير الموثوقة. وإذا لم يعرف الإنسان شيئًا فقول: لا أعلم، أفضل من اختلاق جواب.\n\n" +

                "📱 الصدق في العالم الرقمي\n\n" +
                "الصدق لا يتغير بسبب الهاتف أو الإنترنت. فلا ينبغي نشر معلومة قبل التأكد منها، ولا انتحال شخصية الآخرين، ولا أخذ شيء ليس للإنسان.\n\n" +

                "🔐 الأمانة\n\n" +
                "الأمانة تشمل المال والأشياء والوعود والمهام والمعلومات التي يأتمنك عليها الآخرون.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿يَا أَيُّهَا الَّذِينَ آمَنُوا اتَّقُوا اللَّهَ وَكُونُوا مَعَ الصَّادِقِينَ﴾\n" +
                "سورة التوبة: 119\n\n" +

                "💡 فائدة\n\n" +
                "إذا بنيت سمعتك على الصدق، أصبحت ثقة الناس بك نتيجة طبيعية مع مرور الوقت.";

        } else if (heading.contains("حقوق الجار")) {
            return
                "🏠 مكانة الجار\n\n" +
                "الجار له حق في حسن التعامل وعدم الإيذاء، ومن صور الإحسان إليه احترامه ومساعدته في حدود الاستطاعة ومراعاة راحته وعدم الاعتداء على حقوقه.\n\n" +

                "🤝 التعامل مع الجيران\n\n" +
                "يمكن أن يكون الإحسان بكلمة طيبة أو مساعدة بسيطة أو مشاركة مناسبة أو سؤال عن الجار عند الحاجة. كما ينبغي تجنب رفع الصوت وإزعاج الآخرين والتعدي على ممتلكاتهم.\n\n" +

                "🚫 عدم الأذى\n\n" +
                "من المهم ألا يؤذي الإنسان جاره بالكلام أو التصرفات أو نشر الخصوصيات أو التدخل في شؤونه دون حق.\n\n" +

                "🌿 التعاون\n\n" +
                "المجتمع الذي يحترم فيه الناس بعضهم بعضًا يصبح أكثر أمانًا وراحة، ويشعر فيه الصغير والكبير أن لهم مكانًا محفوظًا.\n\n" +

                "📖 آية مرتبطة\n\n" +
                "﴿وَالْجَارِ ذِي الْقُرْبَىٰ وَالْجَارِ الْجُنُبِ﴾\n" +
                "سورة النساء: 36\n\n" +

                "💡 فائدة\n\n" +
                "ابدأ بالتصرفات الصغيرة: لا تؤذِ جارك، احترم خصوصيته، وساعده عندما تستطيع.";

        }

        return
            "📚 معلومات إسلامية\n\n" +
            "هذا القسم يقدم معلومات تعليمية مبسطة تساعد المسلم على فهم الموضوع والعمل بما يتعلمه.\n\n" +
            "📖 القرآن الكريم مصدر الهداية، وتعلم الدين يحتاج إلى الرجوع إلى المصادر الموثوقة عند المسائل التفصيلية.\n\n" +
            "💡 اجعل التعلم خطوة مستمرة، وطبق ما تتعلمه في حياتك اليومية.";
    }


    void showIslamicTopic(
        String heading,
        String explanation,
        String tip
    ) {
        currentPage = "islamic_topic";
        base(heading);

        TextView text = title(
            getIslamicContent(heading),
            20
        );

        text.setTextColor(Color.WHITE);
        text.setGravity(Gravity.RIGHT);
        text.setTextDirection(View.TEXT_DIRECTION_RTL);
        text.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        text.setLineSpacing(14,1.35f);
        text.setPadding(20,24,20,24);

        text.setBackground(
            cardBackground(
                Color.rgb(15,27,31),
                gold,
                20
            )
        );

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        lp.setMargins(8,12,8,12);
        text.setLayoutParams(lp);

        content.addView(text);
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

        // داخل موضوع إسلامي: العودة إلى قائمة إسلاميات
        if ("islamic_topic".equals(currentPage)) {
            showIslamicTopics();
            return;
        }

        // داخل السورة: العودة إلى قائمة القرآن
        if ("surah".equals(currentPage)) {
            showQuran();
            return;
        }

        // داخل القرآن: العودة إلى الرئيسية
        if ("quran".equals(currentPage)) {
            showHome();
            return;
        }

        // أي صفحة داخلية أخرى: العودة إلى الرئيسية
        if (!"home".equals(currentPage)) {
            showHome();
            return;
        }

        // في الرئيسية: ضغطتان للخروج
        long now = System.currentTimeMillis();

        if (now - lastBackPressTime < 2000) {
            finish();
            return;
        }

        lastBackPressTime = now;

        android.widget.Toast.makeText(
            this,
            "اضغط مرة أخرى للخروج",
            android.widget.Toast.LENGTH_SHORT
        ).show();
    }


    void showGlobalSearch() {
        currentPage = "search";
        base("البحث العام");

        TextView heading = title("🔎 البحث في نور الهدى", 22);
        heading.setTextColor(Color.rgb(235,205,120));
        content.addView(heading);

        EditText search = new EditText(this);
        search.setHint("ابحث عن سورة أو ذكر أو اسم من أسماء الله...");
        search.setTextSize(16);
        search.setSingleLine(true);
        search.setGravity(Gravity.RIGHT);
        search.setTextDirection(View.TEXT_DIRECTION_RTL);
        search.setPadding(20,15,20,15);
        search.setBackground(cardBackground(Color.WHITE, gold, 18));
        content.addView(search);

        TextView info = title("اكتب كلمة للبحث", 15);
        info.setTextColor(Color.LTGRAY);
        content.addView(info);

        LinearLayout results = new LinearLayout(this);
        results.setOrientation(LinearLayout.VERTICAL);
        content.addView(results);

        search.addTextChangedListener(
            new android.text.TextWatcher() {

                public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {}

                public void onTextChanged(
                    CharSequence s, int start, int before, int count) {

                    String query = s.toString().trim();

                    results.removeAllViews();

                    if (query.length() == 0) {
                        info.setText("اكتب كلمة للبحث");
                        return;
                    }

                    int found = 0;

                    // البحث في السور
                    for (int i = 0; i < surahs.length; i++) {
                        if (surahs[i].contains(query) ||
                            String.valueOf(i + 1).equals(query)) {

                            final String name = surahs[i];

                            Button b = btn(
                                "📖 سورة " + name +
                                "  (" + (i + 1) + ")"
                            );

                            b.setOnClickListener(
                                v -> showSurah(name)
                            );

                            results.addView(b);
                            found++;
                        }
                    }

                    // البحث في الأذكار
                    for (String dhikr : adhkar) {
                        if (dhikr.contains(query)) {
                            TextView item = title(
                                "📿 " + dhikr,
                                17
                            );

                            item.setTextColor(Color.WHITE);
                            item.setPadding(18,18,18,18);

                            results.addView(item);
                            found++;
                        }
                    }

                    // البحث في أسماء الله الحسنى
                    for (String name : names) {
                        if (name.contains(query)) {
                            TextView item = title(
                                "✨ " + name,
                                18
                            );

                            item.setTextColor(
                                Color.rgb(235,205,120)
                            );

                            item.setPadding(18,18,18,18);

                            results.addView(item);
                            found++;
                        }
                    }

                    info.setText(
                        found == 0
                            ? "لم يتم العثور على نتائج"
                            : "عدد النتائج: " + found
                    );
                }

                public void afterTextChanged(
                    android.text.Editable s) {}
            }
        );
    }

    void showQuran() {
        currentPage = "quran";
        base("القرآن الكريم");

        TextView info = title("✦  سُوَرُ القُرآنِ الكَرِيم  ✦",20);
        info.setTextColor(Color.rgb(235,205,120));
        content.addView(info);

        Button globalSearch = btn("🔎 البحث العام");
        globalSearch.setOnClickListener(v -> showGlobalSearch());
        content.addView(globalSearch);

        Button favorites = btn("⭐ المفضلة");
        favorites.setOnClickListener(v -> showFavorites());
        content.addView(favorites);

        EditText search = new EditText(this);
        search.setHint("🔎 ابحث عن سورة...");
        search.setTextSize(17);
        search.setSingleLine(true);
        search.setGravity(Gravity.RIGHT);
        search.setTextDirection(View.TEXT_DIRECTION_RTL);
        search.setPadding(20,15,20,15);
        search.setBackground(
            cardBackground(Color.WHITE, gold, 18)
        );
        content.addView(search);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        content.addView(list);

        for (int i=0; i<surahs.length; i++) {
            final String name = surahs[i];
            final int number = i + 1;

            Button b = btn(number + " - سورة " + name);
            b.setOnClickListener(v -> showSurah(name));
            list.addView(b);
        }

        search.addTextChangedListener(
            new android.text.TextWatcher() {
                public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {}

                public void onTextChanged(
                    CharSequence s, int start, int before, int count) {

                    String query = s.toString().trim();

                    for (int i=0; i<list.getChildCount(); i++) {
                        View child = list.getChildAt(i);

                        if (child instanceof Button) {
                            String name = surahs[i];

                            boolean visible =
                                query.length() == 0 ||
                                name.contains(query) ||
                                false;

                            child.setVisibility(
                                visible ? View.VISIBLE : View.GONE
                            );
                        }
                    }
                }

                public void afterTextChanged(
                    android.text.Editable s) {}
            }
        );
    }

    void showFavorites() {
        currentPage = "favorites";
        base("المفضلة");

        Button back = btn("↩ العودة إلى القرآن");
        back.setOnClickListener(v -> showQuran());
        content.addView(back);

        TextView heading = title("⭐ السور المفضلة", 21);
        heading.setTextColor(Color.rgb(235,205,120));
        content.addView(heading);

        android.content.SharedPreferences pref =
            getSharedPreferences("noor_favorites", MODE_PRIVATE);

        boolean found = false;

        for (int i = 0; i < surahs.length; i++) {
            final String name = surahs[i];

            if (pref.getBoolean("surah_" + name, false)) {
                found = true;

                Button b = btn((i + 1) + " - سورة " + name);
                b.setOnClickListener(v -> showSurah(name));
                content.addView(b);
            }
        }

        if (!found) {
            TextView empty = title(
                "لا توجد سور محفوظة في المفضلة بعد ⭐",
                17
            );
            empty.setTextColor(Color.LTGRAY);
            content.addView(empty);
        }
    }

    void showSurah(String name) {
        currentPage = "surah";
        base("");

        content.removeAllViews();
        content.setPadding(0, 0, 0, 0);
        content.setBackgroundColor(android.graphics.Color.rgb(250, 248, 238));

        int surahNumber = -1;

        for (String s : surahs) {
            if (s[0].equals(name)) {
                surahNumber = Integer.parseInt(s[1]);
                break;
            }
        }

        final int selectedSurahNumber = surahNumber;

        final java.util.ArrayList<String> verses =
                new java.util.ArrayList<>();

        final java.util.ArrayList<Integer> verseNumbers =
                new java.util.ArrayList<>();

        try {
            java.io.InputStream is =
                    getAssets().open("quran-simple.txt");

            java.io.BufferedReader br =
                    new java.io.BufferedReader(
                            new java.io.InputStreamReader(
                                    is,
                                    java.nio.charset.StandardCharsets.UTF_8
                            )
                    );

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\|", 3);

                if (parts.length == 3 &&
                        Integer.parseInt(parts[0]) == selectedSurahNumber) {

                    verseNumbers.add(Integer.parseInt(parts[1]));
                    verses.add(parts[2].trim());
                }
            }

            br.close();

        } catch (Exception e) {

            android.widget.Toast.makeText(
                    this,
                    "تعذر قراءة القرآن: " + e.getMessage(),
                    android.widget.Toast.LENGTH_LONG
            ).show();

            return;
        }

        if (verses.isEmpty()) {

            android.widget.Toast.makeText(
                    this,
                    "لم يتم العثور على آيات السورة",
                    android.widget.Toast.LENGTH_LONG
            ).show();

            return;
        }

        /*
         * تقسيم القرآن إلى صفحات عرض داخل الهاتف.
         * هذا تقسيم بصري تلقائي، وليس ترقيم صفحات المصحف الورقي.
         */

        final java.util.ArrayList<String> pages =
                new java.util.ArrayList<>();

        final int charsPerPage = 690;

        StringBuilder currentText = new StringBuilder();

        int currentChars = 0;

        for (int i = 0; i < verses.size(); i++) {

            String verse =
                    verses.get(i)
                            + " ﴿"
                            + verseNumbers.get(i)
                            + "﴾ ";

            if (currentChars > 0 &&
                    currentChars + verse.length() > charsPerPage) {

                pages.add(currentText.toString().trim());

                currentText.setLength(0);
                currentChars = 0;
            }

            currentText.append(verse);
            currentChars += verse.length();
        }

        if (currentText.length() > 0) {
            pages.add(currentText.toString().trim());
        }

        final float density =
                getResources().getDisplayMetrics().density;

        final int d8  = (int)(8  * density);
        final int d12 = (int)(12 * density);
        final int d18 = (int)(18 * density);
        final int d22 = (int)(22 * density);

        /*
         * الصفحة الرئيسية
         */

        final android.widget.LinearLayout mushaf =
                new android.widget.LinearLayout(this);

        mushaf.setOrientation(
                android.widget.LinearLayout.VERTICAL
        );

        mushaf.setGravity(
                android.view.Gravity.CENTER_HORIZONTAL
        );

        mushaf.setBackgroundColor(
                android.graphics.Color.rgb(250, 248, 238)
        );

        /*
         * رأس السورة المزخرف
         */

        final android.widget.TextView surahHeader =
                new android.widget.TextView(this);

        surahHeader.setText(
                "۞   ﴿ " + name + " ﴾   ۞"
        );

        surahHeader.setTextSize(24);

        surahHeader.setTextColor(
                android.graphics.Color.rgb(35, 35, 25)
        );

        surahHeader.setGravity(
                android.view.Gravity.CENTER
        );

        surahHeader.setTypeface(
                android.graphics.Typeface.create(
                        "serif",
                        android.graphics.Typeface.BOLD
                )
        );

        surahHeader.setPadding(
                d12,
                d8,
                d12,
                d8
        );

        android.graphics.drawable.GradientDrawable header =
                new android.graphics.drawable.GradientDrawable();

        header.setColor(
                android.graphics.Color.rgb(248, 244, 222)
        );

        header.setStroke(
                (int)(2 * density),
                android.graphics.Color.rgb(91, 97, 48)
        );

        header.setCornerRadius(
                16 * density
        );

        surahHeader.setBackground(header);

        mushaf.addView(
                surahHeader,
                new android.widget.LinearLayout.LayoutParams(
                        -1,
                        (int)(68 * density)
                )
        );

        /*
         * البسملة مستقلة عن أول آية
         */

        final android.widget.TextView basmala =
                new android.widget.TextView(this);

        if (selectedSurahNumber != 9) {

            basmala.setText(
                    "۞  بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ  ۞"
            );

        } else {

            basmala.setText("");
        }

        basmala.setTextSize(25);

        basmala.setTextColor(
                android.graphics.Color.rgb(25, 25, 25)
        );

        basmala.setGravity(
                android.view.Gravity.CENTER
        );

        basmala.setTypeface(
                android.graphics.Typeface.create(
                        "serif",
                        android.graphics.Typeface.NORMAL
                )
        );

        basmala.setPadding(
                d8,
                d12,
                d8,
                d12
        );

        mushaf.addView(
                basmala,
                new android.widget.LinearLayout.LayoutParams(
                        -1,
                        selectedSurahNumber == 9
                                ? d8
                                : (int)(72 * density)
                )
        );

        /*
         * نص القرآن
         */

        final android.widget.TextView page =
                new android.widget.TextView(this);

        page.setTextSize(22);

        page.setTextColor(
                android.graphics.Color.rgb(30, 30, 28)
        );

        page.setGravity(
                android.view.Gravity.RIGHT |
                android.view.Gravity.TOP
        );

        page.setTextDirection(
                android.view.View.TEXT_DIRECTION_RTL
        );

        page.setTextAlignment(
                android.view.View.TEXT_ALIGNMENT_VIEW_START
        );

        page.setTypeface(
                android.graphics.Typeface.create(
                        "serif",
                        android.graphics.Typeface.NORMAL
                )
        );

        page.setIncludeFontPadding(true);

        page.setLineSpacing(
                5 * density,
                1.12f
        );

        page.setPadding(
                d22,
                d8,
                d22,
                d8
        );

        /*
         * جعل النص مبررًا مثل صفحات المصحف
         * Android 8.1 يدعم ذلك.
         */

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            page.setJustificationMode(
                    android.graphics.text.LineBreaker
                            .JUSTIFICATION_MODE_INTER_WORD
            );
        }

        android.widget.LinearLayout.LayoutParams
                pageParams =
                new android.widget.LinearLayout.LayoutParams(
                        -1,
                        0,
                        1f
                );

        mushaf.addView(page, pageParams);

        /*
         * رقم الصفحة
         */

        final android.widget.TextView pageNumber =
                new android.widget.TextView(this);

        pageNumber.setTextSize(12);

        pageNumber.setTextColor(
                android.graphics.Color.rgb(90, 90, 75)
        );

        pageNumber.setGravity(
                android.view.Gravity.CENTER
        );

        mushaf.addView(
                pageNumber,
                new android.widget.LinearLayout.LayoutParams(
                        -1,
                        (int)(28 * density)
                )
        );

        content.addView(
                mushaf,
                new android.widget.LinearLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        /*
         * التنقل بين الصفحات بالسحب
         */

        final int[] currentPageIndex = {0};

        final float[] downX = {0};

        final float[] downY = {0};

        final Runnable showPage = new Runnable() {

            @Override
            public void run() {

                int index = currentPageIndex[0];

                page.setText(
                        pages.get(index)
                );

                pageNumber.setText(
                        "سورة "
                                + name
                                + "   •   "
                                + (index + 1)
                                + " / "
                                + pages.size()
                );
            }
        };

        page.setOnTouchListener(
                (v, event) -> {

                    switch (event.getActionMasked()) {

                        case android.view.MotionEvent.ACTION_DOWN:

                            downX[0] = event.getX();
                            downY[0] = event.getY();

                            return true;

                        case android.view.MotionEvent.ACTION_UP:

                            float dx =
                                    event.getX() - downX[0];

                            float dy =
                                    event.getY() - downY[0];

                            if (Math.abs(dx) > 80 &&
                                    Math.abs(dx) > Math.abs(dy)) {

                                /*
                                 * سحب لليسار = الصفحة التالية
                                 */

                                if (dx < 0 &&
                                        currentPageIndex[0]
                                                < pages.size() - 1) {

                                    currentPageIndex[0]++;

                                /*
                                 * سحب لليمين = الصفحة السابقة
                                 */

                                } else if (dx > 0 &&
                                        currentPageIndex[0] > 0) {

                                    currentPageIndex[0]--;
                                }

                                showPage.run();
                            }

                            return true;
                    }

                    return true;
                }
        );

        showPage.run();
    }

    void showDailyDua() {
        currentPage = "daily_dua";
        base("🤲 الدعاء اليومي");

        TextView intro = title("🌙 دعاء اليوم", 24);
        intro.setGravity(Gravity.CENTER);
        content.addView(intro);

        final String[][] duas = {
            {"اللهم إنك عفو تحب العفو فاعف عني.", "دعاء جامع للعفو والمغفرة"},
            {"ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار.", "سورة البقرة: 201"},
            {"رب اشرح لي صدري ويسر لي أمري.", "سورة طه: 25-26"},
            {"رب زدني علمًا.", "سورة طه: 114"},
            {"رب اغفر لي ولوالدي وللمؤمنين يوم يقوم الحساب.", "سورة إبراهيم: 41"},
            {"حسبي الله لا إله إلا هو عليه توكلت وهو رب العرش العظيم.", "سورة التوبة: 129"},
            {"لا إله إلا أنت سبحانك إني كنت من الظالمين.", "دعاء يونس عليه السلام"},
            {"ربنا لا تزغ قلوبنا بعد إذ هديتنا وهب لنا من لدنك رحمة إنك أنت الوهاب.", "سورة آل عمران: 8"},
            {"ربنا ظلمنا أنفسنا وإن لم تغفر لنا وترحمنا لنكونن من الخاسرين.", "سورة الأعراف: 23"},
            {"اللهم أعني على ذكرك وشكرك وحسن عبادتك.", "دعاء مأثور"},
            {"اللهم إني أسألك الهدى والتقى والعفاف والغنى.", "دعاء مأثور"},
            {"رب إني لما أنزلت إلي من خير فقير.", "سورة القصص: 24"},
            {"رب هب لي من لدنك ذرية طيبة إنك سميع الدعاء.", "سورة آل عمران: 38"},
            {"اللهم اغفر لي وارحمني واهدني وعافني وارزقني.", "دعاء مأثور"},
            {"اللهم إني أسألك علمًا نافعًا ورزقًا طيبًا وعملًا متقبلًا.", "دعاء مأثور"},
            {"ربنا تقبل منا إنك أنت السميع العليم.", "سورة البقرة: 127"},
            {"رب اجعلني مقيم الصلاة ومن ذريتي ربنا وتقبل دعاء.", "سورة إبراهيم: 40"},
            {"اللهم إني أسألك العفو والعافية في الدنيا والآخرة.", "دعاء مأثور"},
            {"اللهم أصلح لي شأني كله ولا تكلني إلى نفسي طرفة عين.", "دعاء مأثور"},
            {"يا مقلب القلوب ثبت قلبي على دينك.", "دعاء مأثور"},
            {"اللهم إني أعوذ بك من الهم والحزن والعجز والكسل.", "دعاء مأثور"},
            {"ربنا هب لنا من أزواجنا وذرياتنا قرة أعين واجعلنا للمتقين إمامًا.", "سورة الفرقان: 74"},
            {"رب اغفر وارحم وأنت خير الراحمين.", "سورة المؤمنون: 118"},
            {"اللهم بارك لنا فيما رزقتنا وقنا عذاب النار.", "دعاء مأثور"},
            {"اللهم اهدني وسددني.", "دعاء مأثور"},
            {"اللهم ارزقني قلبًا مطمئنًا ولسانًا ذاكرًا وعملًا صالحًا.", "دعاء مأثور"}
        };

        Calendar cal = Calendar.getInstance();
        int index = (cal.get(Calendar.DAY_OF_YEAR) - 1) % duas.length;

        TextView day = title(
                "📅 دعاء اليوم — " + cal.get(Calendar.DAY_OF_YEAR),
                18
        );
        day.setGravity(Gravity.CENTER);
        content.addView(day);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 28, 28, 28);

        TextView dua = title("🤲 " + duas[index][0], 23);
        dua.setGravity(Gravity.CENTER);
        dua.setTextIsSelectable(true);
        card.addView(dua);

        TextView source = title("📖 " + duas[index][1], 16);
        source.setGravity(Gravity.CENTER);
        card.addView(source);

        content.addView(card);

        Button another = btn2("🔄 دعاء آخر", "عرض دعاء مختلف");
        another.setOnClickListener(v -> {
            int next = (index + 1) % duas.length;
            showDailyDuaByIndex(duas, next);
        });
        content.addView(another);

        Button copy = btn2("📋 نسخ الدعاء", "انسخ دعاء اليوم");
        copy.setOnClickListener(v -> {
            android.content.ClipboardManager clipboard =
                    (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(
                    android.content.ClipData.newPlainText("الدعاء اليومي", duas[index][0])
            );
            Toast.makeText(this, "تم نسخ الدعاء ✅", Toast.LENGTH_SHORT).show();
        });
        content.addView(copy);
    }

    void showDailyDuaByIndex(String[][] duas, int index) {
        currentPage = "daily_dua";
        base("🤲 الدعاء اليومي");

        TextView intro = title("🌙 دعاء مختار", 24);
        intro.setGravity(Gravity.CENTER);
        content.addView(intro);

        TextView dua = title("🤲 " + duas[index][0], 23);
        dua.setGravity(Gravity.CENTER);
        dua.setTextIsSelectable(true);
        content.addView(dua);

        TextView source = title("📖 " + duas[index][1], 16);
        source.setGravity(Gravity.CENTER);
        content.addView(source);

        Button another = btn2("🔄 دعاء آخر", "عرض دعاء مختلف");
        another.setOnClickListener(v ->
                showDailyDuaByIndex(duas, (index + 1) % duas.length)
        );
        content.addView(another);
    }

    void showAdhkar() {
        currentPage = "adhkar";
        base("🤲 الأذكار والأدعية");

        TextView intro = title(
            "اختر قسم الأذكار الذي تريد قراءته",
            20
        );
        intro.setTextColor(gold);
        intro.setGravity(Gravity.CENTER);
        intro.setTextDirection(View.TEXT_DIRECTION_RTL);
        content.addView(intro);

        addDhikrSection(
            "🌅 أذكار الصباح",
            "أذكار تبدأ بها يومك بذكر الله وطلب الحفظ والطمأنينة."
        );

        addDhikrSection(
            "🌙 أذكار المساء",
            "أذكار المساء والتحصين وذكر الله في نهاية اليوم."
        );

        addDhikrSection(
            "🕌 أذكار بعد الصلاة",
            "أذكار وأدعية تقال بعد أداء الصلاة."
        );

        addDhikrSection(
            "😴 أذكار النوم",
            "أذكار وأدعية قبل النوم تساعد على ختم اليوم بالذكر."
        );

        addDhikrSection(
            "🏠 أذكار المنزل",
            "أذكار مرتبطة بدخول المنزل والخروج منه وحياة المسلم اليومية."
        );

        addDhikrSection(
            "🚗 أذكار السفر",
            "أدعية وأذكار يحتاجها المسلم عند السفر والتنقل."
        );

        addDhikrSection(
            "🍽️ أذكار الطعام",
            "أذكار وآداب الطعام والشراب."
        );

        addDhikrSection(
            "🤲 أدعية وأذكار متنوعة",
            "مجموعة من الأدعية والأذكار العامة للاستغفار والتسبيح والطلب من الله."
        );
    }

    void addDhikrSection(String heading, String description) {
        Button b = new Button(this);

        b.setText(
            heading + "\n\n" +
            description
        );

        b.setTextSize(19);
        b.setTextColor(Color.WHITE);
        b.setGravity(Gravity.CENTER);
        b.setAllCaps(false);
        b.setTextDirection(View.TEXT_DIRECTION_RTL);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setSingleLine(false);
        b.setMinHeight(125);
        b.setPadding(20,20,20,20);

        b.setBackground(
            cardBackground(
                Color.rgb(15,27,31),
                gold,
                20
            )
        );

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        lp.setMargins(6,8,6,8);
        b.setLayoutParams(lp);

        b.setOnClickListener(v ->
            showDhikrCategory(heading)
        );

        content.addView(b);
    }

    void showDhikrCategory(String category) {
        currentPage = "dhikr_category";
        base(category);

        TextView intro = title(
            "🤲 أذكار مرتبة حسب القسم",
            20
        );

        intro.setTextColor(gold);
        intro.setGravity(Gravity.CENTER);
        intro.setTextDirection(View.TEXT_DIRECTION_RTL);
        content.addView(intro);

        String[][] list = getDhikrList(category);

        for (String[] item : list) {
            addDhikrCard(
                item[0],
                Integer.parseInt(item[1]),
                item[2],
                category
            );
        }
    }

    String[][] getDhikrList(String category) {

        if (category.contains("الصباح")) {
            return new String[][] {
                {
                    "اللهم أنت ربي لا إله إلا أنت، خلقتني وأنا عبدك، وأنا على عهدك ووعدك ما استطعت، أعوذ بك من شر ما صنعت، أبوء لك بنعمتك علي وأبوء بذنبي فاغفر لي، فإنه لا يغفر الذنوب إلا أنت",
                    "1",
                    "صحيح البخاري 6306"
                },
                {
                    "رضيت بالله ربًا، وبالإسلام دينًا، وبمحمد صلى الله عليه وسلم نبيًا",
                    "3",
                    "رواه أبو داود والترمذي"
                },
                {
                    "بسم الله الذي لا يضر مع اسمه شيء في الأرض ولا في السماء وهو السميع العليم",
                    "3",
                    "رواه أبو داود والترمذي"
                },
                {
                    "حسبي الله لا إله إلا هو، عليه توكلت وهو رب العرش العظيم",
                    "7",
                    "ورد عن أبي الدرداء رضي الله عنه"
                },
                {
                    "سبحان الله وبحمده",
                    "100",
                    "رواه مسلم"
                },
                {
                    "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                    "10",
                    "ورد في أذكار الصباح"
                },
                {
                    "سورة الإخلاص:\nقُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ ۝ لَمْ يَلِدْ وَلَمْ يُولَدْ ۝ وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ\n\nسورة الفلق:\nقُلْ أَعُوذُ بِرَبِّ الْفَلَقِ ۝ مِن شَرِّ مَا خَلَقَ ۝ وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ ۝ وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ ۝ وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ\n\nسورة الناس:\nقُلْ أَعُوذُ بِرَبِّ النَّاسِ ۝ مَلِكِ النَّاسِ ۝ إِلَٰهِ النَّاسِ ۝ مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ ۝ الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ ۝ مِنَ الْجِنَّةِ وَالنَّاسِ",
                    "3",
                    "سور الإخلاص والفلق والناس 112-114"
                }
            };
        }

        if (category.contains("المساء")) {
            return new String[][] {
                {
                    "اللهم أنت ربي لا إله إلا أنت، خلقتني وأنا عبدك، وأنا على عهدك ووعدك ما استطعت، أعوذ بك من شر ما صنعت، أبوء لك بنعمتك علي وأبوء بذنبي فاغفر لي، فإنه لا يغفر الذنوب إلا أنت",
                    "1",
                    "صحيح البخاري 6306"
                },
                {
                    "رضيت بالله ربًا، وبالإسلام دينًا، وبمحمد صلى الله عليه وسلم نبيًا",
                    "3",
                    "رواه أبو داود والترمذي"
                },
                {
                    "بسم الله الذي لا يضر مع اسمه شيء في الأرض ولا في السماء وهو السميع العليم",
                    "3",
                    "رواه أبو داود والترمذي"
                },
                {
                    "حسبي الله لا إله إلا هو، عليه توكلت وهو رب العرش العظيم",
                    "7",
                    "ورد عن أبي الدرداء رضي الله عنه"
                },
                {
                    "سبحان الله وبحمده",
                    "100",
                    "رواه مسلم"
                },
                {
                    "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                    "10",
                    "ورد في أذكار الصباح والمساء"
                },
                {
                    "سورة الإخلاص:\nقُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ ۝ لَمْ يَلِدْ وَلَمْ يُولَدْ ۝ وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ\n\nسورة الفلق:\nقُلْ أَعُوذُ بِرَبِّ الْفَلَقِ ۝ مِن شَرِّ مَا خَلَقَ ۝ وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ ۝ وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ ۝ وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ\n\nسورة الناس:\nقُلْ أَعُوذُ بِرَبِّ النَّاسِ ۝ مَلِكِ النَّاسِ ۝ إِلَٰهِ النَّاسِ ۝ مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ ۝ الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ ۝ مِنَ الْجِنَّةِ وَالنَّاسِ",
                    "3",
                    "سور الإخلاص والفلق والناس 112-114"
                }
            };
        }

        if (category.contains("بعد الصلاة")) {
            return new String[][] {
                {
                    "أستغفر الله",
                    "3",
                    "رواه مسلم"
                },
                {
                    "اللهم أنت السلام ومنك السلام تباركت يا ذا الجلال والإكرام",
                    "1",
                    "رواه مسلم"
                },
                {
                    "سبحان الله",
                    "33",
                    "رواه مسلم"
                },
                {
                    "الحمد لله",
                    "33",
                    "رواه مسلم"
                },
                {
                    "الله أكبر",
                    "34",
                    "رواه مسلم"
                },
                {
                    "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                    "1",
                    "رواه مسلم"
                }
            };
        }

        if (category.contains("النوم")) {
            return new String[][] {
                {
                    "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ",
                    "1",
                    "رواه البخاري في قصة أبي هريرة"
                },
                {
                    "آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ ۝ لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا ۚ لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ ۗ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَسِينَا أَوْ أَخْطَأْنَا ۚ رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا ۚ رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ ۖ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا ۚ أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ",
                    "1",
                    "رواه البخاري ومسلم"
                },
                {
                    "سورة الإخلاص:\nقُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ ۝ لَمْ يَلِدْ وَلَمْ يُولَدْ ۝ وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ\n\nسورة الفلق:\nقُلْ أَعُوذُ بِرَبِّ الْفَلَقِ ۝ مِن شَرِّ مَا خَلَقَ ۝ وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ ۝ وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ ۝ وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ\n\nسورة الناس:\nقُلْ أَعُوذُ بِرَبِّ النَّاسِ ۝ مَلِكِ النَّاسِ ۝ إِلَٰهِ النَّاسِ ۝ مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ ۝ الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ ۝ مِنَ الْجِنَّةِ وَالنَّاسِ",
                    "3",
                    "سور الإخلاص والفلق والناس 112-114"
                },
                {
                    "سبحان الله",
                    "33",
                    "رواه البخاري ومسلم"
                },
                {
                    "الحمد لله",
                    "33",
                    "رواه البخاري ومسلم"
                },
                {
                    "الله أكبر",
                    "34",
                    "رواه البخاري ومسلم"
                },
                {
                    "باسمك اللهم أموت وأحيا",
                    "1",
                    "رواه البخاري"
                }
            };
        }

        if (category.contains("المنزل")) {
            return new String[][] {
                {
                    "بسم الله",
                    "1",
                    "يقال عند دخول المنزل والطعام"
                },
                {
                    "السلام عليكم ورحمة الله",
                    "1",
                    "من هدي السلام بين المسلمين"
                },
                {
                    "اللهم إني أسألك خير المولج وخير المخرج، بسم الله ولجنا وبسم الله خرجنا وعلى الله ربنا توكلنا",
                    "1",
                    "ورد في سنن أبي داود"
                },
                {
                    "أعوذ بكلمات الله التامات من شر ما خلق",
                    "1",
                    "رواه مسلم"
                }
            };
        }

        if (category.contains("السفر")) {
            return new String[][] {
                {
                    "سبحان الذي سخر لنا هذا وما كنا له مقرنين وإنا إلى ربنا لمنقلبون",
                    "1",
                    "سورة الزخرف 13-14"
                },
                {
                    "اللهم إنا نسألك في سفرنا هذا البر والتقوى ومن العمل ما ترضى",
                    "1",
                    "رواه مسلم"
                },
                {
                    "اللهم هون علينا سفرنا هذا واطو عنا بعده",
                    "1",
                    "رواه مسلم"
                },
                {
                    "اللهم أنت الصاحب في السفر والخليفة في الأهل",
                    "1",
                    "رواه مسلم"
                }
            };
        }

        if (category.contains("الطعام")) {
            return new String[][] {
                {
                    "بسم الله",
                    "1",
                    "رواه أبو داود والترمذي"
                },
                {
                    "بسم الله أوله وآخره",
                    "1",
                    "رواه أبو داود والترمذي"
                },
                {
                    "الحمد لله الذي أطعمني هذا ورزقنيه من غير حول مني ولا قوة",
                    "1",
                    "رواه أبو داود والترمذي"
                },
                {
                    "الحمد لله",
                    "1",
                    "من حمد الله بعد الطعام"
                }
            };
        }

        return new String[][] {
            {
                "سبحان الله",
                "1",
                "ذكر عام"
            },
            {
                "الحمد لله",
                "1",
                "ذكر عام"
            },
            {
                "الله أكبر",
                "1",
                "ذكر عام"
            },
            {
                "لا إله إلا الله",
                "1",
                "ذكر عام"
            },
            {
                "أستغفر الله",
                "1",
                "ذكر عام"
            },
            {
                "سبحان الله وبحمده",
                "1",
                "رواه مسلم"
            },
            {
                "لا حول ولا قوة إلا بالله",
                "1",
                "رواه البخاري ومسلم"
            },
            {
                "رب اغفر لي",
                "1",
                "دعاء"
            },
            {
                "رب زدني علمًا",
                "1",
                "سورة طه 114"
            }
        };
    }

    void addDhikrCard(
        String dhikr,
        int target,
        String source,
        String category
    ) {

        android.content.SharedPreferences prefs =
            getSharedPreferences(
                "noor_dhikr_progress",
                MODE_PRIVATE
            );

        String key = "count_" + category + "_" + dhikr;

        int[] count = { prefs.getInt(key, 0) };

        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.setPadding(18,18,18,18);

        box.setBackground(
            cardBackground(
                Color.rgb(15,27,31),
                gold,
                18
            )
        );

        TextView text = new TextView(this);
        text.setText("✦ " + dhikr);
        text.setTextColor(Color.WHITE);
        text.setTextSize(20);
        text.setGravity(Gravity.RIGHT);
        text.setTextDirection(View.TEXT_DIRECTION_RTL);
        text.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        text.setLineSpacing(10,1.25f);
        text.setPadding(10,10,10,15);

        TextView sourceView = new TextView(this);
        sourceView.setText("📚 " + source);
        sourceView.setTextColor(gold);
        sourceView.setTextSize(14);
        sourceView.setGravity(Gravity.RIGHT);
        sourceView.setTextDirection(View.TEXT_DIRECTION_RTL);
        sourceView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        sourceView.setPadding(10,5,10,10);

        TextView counter = new TextView(this);
        counter.setTextSize(18);
        counter.setTextColor(gold);
        counter.setGravity(Gravity.CENTER);
        counter.setTextDirection(View.TEXT_DIRECTION_RTL);

        TextView remaining = new TextView(this);
        remaining.setTextSize(15);
        remaining.setTextColor(Color.LTGRAY);
        remaining.setGravity(Gravity.CENTER);
        remaining.setTextDirection(View.TEXT_DIRECTION_RTL);
        remaining.setPadding(5,3,5,8);

        Button add = new Button(this);
        add.setText("🔢 ذكرته");
        add.setTextSize(18);
        add.setTextColor(Color.WHITE);
        add.setAllCaps(false);
        add.setMinHeight(60);

        Button reset = new Button(this);
        reset.setText("↩️ إعادة");
        reset.setTextSize(16);
        reset.setTextColor(Color.WHITE);
        reset.setAllCaps(false);

        Runnable updateCounter = () -> {

            if (count[0] >= target) {

                count[0] = target;

                counter.setText(
                    "✅ مكتمل — " +
                    target + " / " + target
                );

                remaining.setText(
                    "🌟 أحسنت، أتممت هذا الذكر"
                );

                add.setText("✅ مكتمل");

            } else {

                int left = target - count[0];

                counter.setText(
                    "التكرار: " +
                    count[0] +
                    " / " +
                    target
                );

                remaining.setText(
                    "باقي: " + left
                );

                add.setText("🔢 ذكرته");
            }
        };

        updateCounter.run();

        add.setOnClickListener(v -> {

            if (count[0] < target) {

                count[0]++;

                prefs.edit()
                    .putInt(key,count[0])
                    .apply();

                updateCounter.run();
            }
        });

        reset.setOnClickListener(v -> {

            count[0] = 0;

            prefs.edit()
                .putInt(key,0)
                .apply();

            updateCounter.run();
        });

        box.addView(text);
        box.addView(sourceView);
        box.addView(counter);
        box.addView(remaining);
        box.addView(add);
        box.addView(reset);

        LinearLayout.LayoutParams lp =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        lp.setMargins(6,7,6,7);
        box.setLayoutParams(lp);

        content.addView(box);
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

    void showHijriCalendar() {
        currentPage = "hijri";
        base("📅 التقويم الهجري");

        Calendar now = Calendar.getInstance();

        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) + 1;
        int year = now.get(Calendar.YEAR);

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        String[] gregorianMonths = {
            "يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو",
            "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"
        };

        String gregorianDate =
            day + " " + gregorianMonths[month - 1] + " " + year;

        int[] hijri = gregorianToHijri(day, month, year);

        String[] hijriMonths = {
            "محرم", "صفر", "ربيع الأول", "ربيع الآخر",
            "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان",
            "رمضان", "شوال", "ذو القعدة", "ذو الحجة"
        };

        String hijriDate =
            hijri[0] + " " + hijriMonths[hijri[1] - 1] + " " + hijri[2] + " هـ";

        TextView titleView = title("🌙 التاريخ الهجري", 26);
        titleView.setGravity(Gravity.CENTER);
        content.addView(titleView);

        TextView phoneInfo = title(
            "📱 التاريخ حسب الهاتف\\n" +
            "📅 " + gregorianDate + "\\n" +
            String.format(Locale.getDefault(), "⏰ %02d:%02d", hour, minute),
            20
        );
        phoneInfo.setGravity(Gravity.CENTER);
        content.addView(phoneInfo);

        TextView hijriView = title(
            "🕌 " + hijriDate,
            25
        );
        hijriView.setGravity(Gravity.CENTER);
        content.addView(hijriView);

        TextView note = title(
            "🔄 يتم أخذ التاريخ والوقت من جهازك مباشرة عند فتح هذه الصفحة.",
            16
        );
        note.setGravity(Gravity.CENTER);
        content.addView(note);
    }

    int[] gregorianToHijri(int day, int month, int year) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;

        int jd = day + (153 * m + 2) / 5 + 365 * y
                + y / 4 - y / 100 + y / 400 - 32045;

        int l = jd - 1948440 + 10632;
        int n = (l - 1) / 10631;
        l = l - 10631 * n + 354;

        int j = ((10985 - l) / 5316) * ((50 * l) / 17719)
                + (l / 5670) * ((43 * l) / 15238);

        l = l - ((30 - j) / 15) * ((17719 * j) / 50)
                - (j / 16) * ((15238 * j) / 43) + 29;

        int hMonth = (24 * l) / 709;
        int hDay = l - (709 * hMonth) / 24;
        int hYear = 30 * n + j - 30;

        return new int[]{hDay, hMonth, hYear};
    }

    JSONArray loadProphetStories() {
        try {
            java.io.InputStream input = getAssets().open("prophets_stories.json");
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(input, java.nio.charset.StandardCharsets.UTF_8)
            );

            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            reader.close();
            input.close();

            return new JSONArray(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
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

        JSONArray storiesJson = loadProphetStories();

        if (storiesJson.length() == 0) {
            TextView error = title(
                    "⚠️ تعذر تحميل قصص الأنبياء",
                    18
            );
            error.setTextColor(Color.rgb(180, 40, 40));
            error.setGravity(Gravity.CENTER);
            content.addView(error);
            return;
        }

        String[][] stories = new String[storiesJson.length()][3];

        try {
            for (int i = 0; i < storiesJson.length(); i++) {
                JSONObject obj = storiesJson.getJSONObject(i);

                stories[i][0] = obj.optString("name", "نبي");
                stories[i][1] = obj.optString("story", "");
                stories[i][2] = obj.optString("sources", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText search = new EditText(this);
        search.setHint("🔎 ابحث عن نبي");
        search.setTextSize(17);
        search.setSingleLine(true);
        content.addView(search);

        Button favorites = btn("⭐ المفضلة");
        content.addView(favorites);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        content.addView(list);

        for (String[] story : stories) {
            TextView card = new TextView(this);

            card.setText(
                "🌙 " + story[0] +
                "\n\n" + story[1] +
                "\n\n📖 " + story[2] +
                "\n\nاضغط لفتح القصة"
            );

            card.setTextColor(Color.WHITE);
            card.setTextSize(17);
            card.setGravity(Gravity.RIGHT);
            card.setPadding(20,22,20,22);
            card.setBackground(
                cardBackground(
                    Color.rgb(15,27,31),
                    gold,
                    18
                )
            );

            LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(-1,-2);

            lp.setMargins(5,7,5,7);
            card.setLayoutParams(lp);

            card.setOnClickListener(v ->
                showProphetStory(
                    story[0],
                    story[1],
                    story[2]
                )
            );

            list.addView(card);
        }

        search.addTextChangedListener(
            new android.text.TextWatcher() {

                public void beforeTextChanged(
                    CharSequence s, int start,
                    int count, int after) {}

                public void onTextChanged(
                    CharSequence s, int start,
                    int before, int count) {

                    String q = s.toString().trim();

                    for (int i = 0; i < list.getChildCount(); i++) {
                        View item = list.getChildAt(i);

                        String name = stories[i][0];

                        item.setVisibility(
                            q.isEmpty() || name.contains(q)
                            ? View.VISIBLE
                            : View.GONE
                        );
                    }
                }

                public void afterTextChanged(
                    android.text.Editable e) {}
            }
        );

        favorites.setOnClickListener(v -> {

            StringBuilder text = new StringBuilder();

            android.content.SharedPreferences pref =
                getSharedPreferences(
                    "noor_prophet_favorites",
                    MODE_PRIVATE
                );

            for (String[] story : stories) {
                if (pref.getBoolean(story[0], false)) {
                    text.append("⭐ ")
                        .append(story[0])
                        .append("\n\n");
                }
            }

            if (text.length() == 0) {
                text.append("لا توجد قصص مفضلة بعد.");
            }

            new AlertDialog.Builder(this)
                .setTitle("⭐ قصصك المفضلة")
                .setMessage(text.toString())
                .setPositiveButton("حسنًا", null)
                .show();
        });
    }

    void showProphetStory(
        String name,
        String story,
        String source
    ) {
        currentPage = "prophet_story";
        base("📖 " + name);

        TextView heading = title(
            "🌙 " + name,
            26
        );
        heading.setTextColor(gold);
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(10, 15, 10, 20);
        content.addView(heading);

        TextView text = new TextView(this);

        text.setText(
            story +
            "\n\n━━━━━━━━━━━━━━━━━━━━\n\n" +
            "📚 المصادر:\n" +
            source
        );

        text.setTextColor(Color.rgb(35, 35, 35));
        text.setTextSize(20);
        text.setGravity(Gravity.RIGHT);
        text.setPadding(25, 25, 25, 25);
        text.setLineSpacing(10, 1.18f);

        content.addView(text);

        android.content.SharedPreferences pref =
            getSharedPreferences(
                "noor_prophet_favorites",
                MODE_PRIVATE
            );

        boolean saved = pref.getBoolean(name, false);

        Button fav = btn(
            saved
                ? "⭐ إزالة من المفضلة"
                : "☆ إضافة للمفضلة"
        );

        content.addView(fav);

        fav.setOnClickListener(v -> {
            boolean now =
                !pref.getBoolean(name, false);

            pref.edit()
                .putBoolean(name, now)
                .apply();

            fav.setText(
                now
                    ? "⭐ إزالة من المفضلة"
                    : "☆ إضافة للمفضلة"
            );
        });

        Button copy = btn("📋 نسخ القصة");
        content.addView(copy);

        copy.setOnClickListener(v -> {
            android.content.ClipboardManager clipboard =
                (android.content.ClipboardManager)
                getSystemService(CLIPBOARD_SERVICE);

            clipboard.setPrimaryClip(
                android.content.ClipData.newPlainText(
                    name,
                    text.getText().toString()
                )
            );

            Toast.makeText(
                this,
                "تم نسخ القصة والمصادر",
                Toast.LENGTH_SHORT
            ).show();
        });

        Button share = btn("📤 مشاركة القصة");
        content.addView(share);

        share.setOnClickListener(v -> {
            Intent intent =
                new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");

            intent.putExtra(
                Intent.EXTRA_TEXT,
                text.getText().toString()
            );

            startActivity(
                Intent.createChooser(
                    intent,
                    "مشاركة قصة " + name
                )
            );
        });
    }

void showTasbeeh() {
        currentPage = "tasbeeh";
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
        currentPage = "names";
        base("أسماء الله الحسنى");

        for(String x:names) {
            TextView t=new TextView(this);
            t.setText("﴿ "+x+" ﴾");
            t.setTextColor(Color.BLACK);
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

                // بيانات الصلاة القادمة
                final String[] nextNames = {
                    "الفجر", "الظهر", "العصر", "المغرب", "العشاء"
                };

                final String[] nextKeys = {
                    "Fajr", "Dhuhr", "Asr", "Maghrib", "Isha"
                };
            runOnUiThread(() -> {
                    content.removeAllViews();

                    // الصلاة القادمة والعد التنازلي
                    TextView nextPrayerView = title("⏳ الصلاة القادمة: حساب...", 20);
                    nextPrayerView.setTextColor(Color.WHITE);
                    nextPrayerView.setGravity(Gravity.CENTER);
                    nextPrayerView.setPadding(15, 20, 15, 20);
                    nextPrayerView.setBackground(
                        cardBackground(Color.rgb(15,27,31), gold, 18)
                    );
                    content.addView(nextPrayerView);

                    final android.os.Handler countdownHandler =
                        new android.os.Handler(android.os.Looper.getMainLooper());

                    final Runnable countdownRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Calendar now = Calendar.getInstance();
                                Calendar target = null;
                                String nextName = null;

                                for (int i = 0; i < nextKeys.length; i++) {
                                    String value = timings.optString(nextKeys[i]);
                                    String[] parts = value.split(":");

                                    Calendar candidate = Calendar.getInstance();
                                    candidate.set(Calendar.HOUR_OF_DAY,
                                        Integer.parseInt(parts[0]));
                                    candidate.set(Calendar.MINUTE,
                                        Integer.parseInt(parts[1]));
                                    candidate.set(Calendar.SECOND, 0);
                                    candidate.set(Calendar.MILLISECOND, 0);

                                    if (candidate.after(now)) {
                                        target = candidate;
                                        nextName = nextNames[i];
                                        break;
                                    }
                                }

                                if (target == null) {
                                    String value = timings.optString("Fajr");
                                    String[] parts = value.split(":");

                                    target = Calendar.getInstance();
                                    target.set(Calendar.HOUR_OF_DAY,
                                        Integer.parseInt(parts[0]));
                                    target.set(Calendar.MINUTE,
                                        Integer.parseInt(parts[1]));
                                    target.set(Calendar.SECOND, 0);
                                    target.set(Calendar.MILLISECOND, 0);
                                    target.add(Calendar.DAY_OF_YEAR, 1);

                                    nextName = "الفجر";
                                }

                                long diff =
                                    target.getTimeInMillis()
                                    - System.currentTimeMillis();

                                long hours = diff / (1000 * 60 * 60);
                                long minutes = (diff / (1000 * 60)) % 60;
                                long seconds = (diff / 1000) % 60;

                                nextPrayerView.setText(
                                    "⏳ الصلاة القادمة: " + nextName +
                                    "\nمتبقي: " +
                                    String.format(
                                        java.util.Locale.getDefault(),
                                        "%02d:%02d:%02d",
                                        hours, minutes, seconds
                                    )
                                );

                                countdownHandler.postDelayed(this, 1000);

                            } catch (Exception e) {
                                nextPrayerView.setText(
                                    "⏳ تعذر حساب الصلاة القادمة"
                                );
                            }
                        }
                    };

                    countdownHandler.post(countdownRunnable);

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
                        title(
                            displayLocation +
                            "\n🕰️ المواقيت حسب توقيت جهازك",
                            16
                        );

                    info.setTextColor(Color.LTGRAY);
                    info.setGravity(Gravity.CENTER);
                    info.setPadding(10,18,10,10);
                    content.addView(info);

                    TextView note =
                        title(
                            "ℹ️ الشروق وقت مهم، لكنه ليس وقت أذان.",
                            14
                        );

                    note.setTextColor(Color.GRAY);
                    note.setGravity(Gravity.CENTER);
                    note.setPadding(10,5,10,15);
                    content.addView(note);
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
