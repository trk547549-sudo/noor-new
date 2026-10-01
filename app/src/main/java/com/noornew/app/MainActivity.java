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
        content.addView(morning);

        section("✦ خدمات نور الهدى ✦");

        Button prayer = btn2("🕌 مواقيت الصلاة", "مع تنبيه الأذان");
        prayer.setOnClickListener(v -> showPrayer());

        Button adhan = btn2("🔔 الأذان", "صلاتك في وقتها");
        adhan.setOnClickListener(v -> showPrayer());
        addRow(prayer,adhan);

        Button qib = btn2("🕋 القبلة", "اعرف اتجاه القبلة");
        qib.setOnClickListener(v ->
            Toast.makeText(this,"اتجاه القبلة قيد التطوير",Toast.LENGTH_SHORT).show());

        addRow(qib,evening);

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

        section("✦ التطبيق ✦");

        Button settings = btn2("⚙️ الإعدادات", "تحكم في تجربتك");
        settings.setOnClickListener(v -> showSettings());

        Button about = btn2("ℹ️ حول التطبيق", "نور الهدى");
        about.setOnClickListener(v ->
            new AlertDialog.Builder(this)
                .setTitle("🌙 نور الهدى")
                .setMessage(
                    "تطبيق إسلامي شامل\n\n" +
                    "مطور التطبيق:\n" +
                    "علاء العمراني\n" +
                    "ala alamrany"
                )
                .setPositiveButton("حسنًا",null)
                .show());

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

        int surahNumber = -1;

        for (int i = 0; i < surahs.length; i++) {
            if (surahs[i].equals(name)) {
                surahNumber = i + 1;
                break;
            }
        }

        base("سورة " + name);

        TextView heading = title("✦ سورة " + name + " ✦", 25);
        heading.setTextColor(Color.rgb(235,205,120));
        heading.setGravity(Gravity.CENTER);
        heading.setTextDirection(View.TEXT_DIRECTION_RTL);
        content.addView(heading);

        TextView info = title(
            "القرآن الكريم  •  السورة رقم " + surahNumber,
            15
        );
        info.setTextColor(Color.LTGRAY);
        info.setGravity(Gravity.CENTER);
        info.setTextDirection(View.TEXT_DIRECTION_RTL);
        content.addView(info);

        Button favoriteSurah = btn("🔖 حفظ السورة في المفضلة");
        favoriteSurah.setOnClickListener(v -> {
            android.content.SharedPreferences pref =
                getSharedPreferences("noor_favorites", MODE_PRIVATE);

            pref.edit()
                .putBoolean("surah_" + name, true)
                .apply();

            android.widget.Toast.makeText(
                this,
                "⭐ تمت إضافة سورة " + name + " إلى المفضلة",
                android.widget.Toast.LENGTH_SHORT
            ).show();
        });
        content.addView(favoriteSurah);

        EditText search = new EditText(this);
        search.setHint("🔎 ابحث داخل السورة...");
        search.setTextSize(16);
        search.setSingleLine(true);
        search.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        search.setTextDirection(View.TEXT_DIRECTION_RTL);
        search.setHintTextColor(Color.GRAY);
        search.setTextColor(Color.DKGRAY);
        search.setPadding(20, 15, 20, 15);
        search.setBackground(
            cardBackground(Color.WHITE, gold, 18)
        );
        content.addView(search);

        TextView resultInfo = title("", 14);
        resultInfo.setTextColor(Color.LTGRAY);
        resultInfo.setGravity(Gravity.RIGHT);
        resultInfo.setTextDirection(View.TEXT_DIRECTION_RTL);
        content.addView(resultInfo);

        TextView quranText = new TextView(this);

        // إعداد عرض القرآن
        quranText.setTextSize(23);
        quranText.setTextColor(Color.rgb(245, 238, 220));
        quranText.setGravity(
            Gravity.RIGHT | Gravity.CENTER_VERTICAL
        );
        quranText.setTextDirection(View.TEXT_DIRECTION_RTL);
        quranText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        quranText.setLineSpacing(18, 1.25f);
        quranText.setPadding(22, 35, 22, 45);
        quranText.setIncludeFontPadding(true);

        // خلفية مريحة لقراءة القرآن
        quranText.setBackground(
            cardBackground(
                Color.rgb(28, 38, 34),
                Color.rgb(120, 105, 65),
                20
            )
        );

        LinearLayout.LayoutParams textParams =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        textParams.setMargins(5, 18, 5, 25);
        quranText.setLayoutParams(textParams);

        StringBuilder surahText = new StringBuilder();
        int totalVerses = 0;

        try {
            InputStream in =
                getAssets().open("quran-simple.txt");

            java.io.BufferedReader reader =
                new java.io.BufferedReader(
                    new java.io.InputStreamReader(
                        in,
                        "UTF-8"
                    )
                );

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.length() == 0)
                    continue;

                String[] parts =
                    line.split("\\|", 3);

                if (parts.length < 3)
                    continue;

                int fileSurah;
                int fileVerse;

                try {
                    fileSurah =
                        Integer.parseInt(
                            parts[0].trim()
                        );

                    fileVerse =
                        Integer.parseInt(
                            parts[1].trim()
                        );

                } catch (Exception ignored) {
                    continue;
                }

                String verseText =
                    parts[2].trim();

                if (fileSurah == surahNumber) {

                    if (totalVerses > 0) {
                        surahText.append("  ");
                    }

                    surahText.append(verseText);
                    surahText.append("  ﴿");
                    surahText.append(fileVerse);
                    surahText.append("﴾");

                    totalVerses++;
                }
            }

            reader.close();

        } catch (Exception e) {

            surahText.setLength(0);

            surahText.append(
                "تعذر قراءة آيات هذه السورة."
            );
        }

        final String completeSurah =
            surahText.toString();

        final int totalVerseCount =
            totalVerses;

        if (completeSurah.trim().length() == 0) {

            quranText.setText(
                "لم يتم العثور على آيات هذه السورة في ملف القرآن."
            );

        } else {

            quranText.setText(
                completeSurah
            );
        }

        resultInfo.setText(
            "عدد الآيات: " + totalVerseCount
        );

        content.addView(quranText);

        search.addTextChangedListener(
            new android.text.TextWatcher() {

                public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after
                ) {}

                public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count
                ) {

                    String query =
                        s.toString().trim();

                    if (query.length() == 0) {

                        quranText.setText(
                            completeSurah
                        );

                        resultInfo.setText(
                            "عدد الآيات: " +
                            totalVerseCount
                        );

                        return;
                    }

                    StringBuilder result =
                        new StringBuilder();

                    String[] verses =
                        completeSurah.split("﴾");

                    int found = 0;

                    for (String verse : verses) {

                        if (verse.contains(query)) {

                            result.append(
                                verse
                            );

                            result.append(
                                "﴾  "
                            );

                            found++;
                        }
                    }

                    if (found == 0) {

                        quranText.setText(
                            "لا توجد نتائج داخل هذه السورة."
                        );

                    } else {

                        quranText.setText(
                            result.toString()
                        );
                    }

                    resultInfo.setText(
                        "نتائج البحث: " + found
                    );
                }

                public void afterTextChanged(
                    android.text.Editable s
                ) {}
            }
        );
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

        String key =
            "count_" + category + "_" + dhikr;

        int[] count = {
            prefs.getInt(key, 0)
        };

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

        text.setText(
            "✦ " + dhikr
        );

        text.setTextColor(Color.WHITE);
        text.setTextSize(20);
        text.setGravity(Gravity.RIGHT);
        text.setTextDirection(View.TEXT_DIRECTION_RTL);
        text.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        text.setLineSpacing(10,1.25f);
        text.setPadding(10,10,10,15);

        TextView sourceView = new TextView(this);

        sourceView.setText(
            "📚 " + source
        );

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

                counter.setText(
                    "✅ مكتمل — " +
                    target + " / " + target
                );

                add.setText("✅ مكتمل");

            } else {

                counter.setText(
                    "التكرار: " +
                    count[0] +
                    " / " +
                    target
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
