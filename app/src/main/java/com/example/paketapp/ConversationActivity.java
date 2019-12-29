package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paketapp.Algoritam_za_odredjivanje.Algoritam;
import com.example.paketapp.Algoritam_za_odredjivanje.AlgoritamMobilni;
import com.example.paketapp.Algoritam_za_odredjivanje.AlgoritamNet;
import com.example.paketapp.Algoritam_za_odredjivanje.AlgoritamTV;
import com.example.paketapp.Classes.Message;
import com.example.paketapp.Classes.MessageAdapter;
import com.example.paketapp.Paketi.BoxPaket;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class ConversationActivity extends AppCompatActivity implements RoomListener {

    private String channelID = "2JYnAoVFhHG0tBcn";
    private String roomName = "observable-room";
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private ConstraintLayout constraintLayout4,constraintLayout2,constHint;

    private TextView tvHint;

    private int indexOfQuestion=0;
    private Runnable r;

    private String[] split;

    //<editor-fold desc="Pitanja">
    private List<String> tvQuestions=Arrays.asList("Da li Vam je potrebna opcija gledanja unazad?",
            "Želite li da imate mogućnost snimanja sadržaja?",
            "Da li želite HBO paket?");
    private List<String> tvAnswers=Arrays.asList("Da/Ne","Da/Ne","Da/Ne");
    private List<String> tvHints=Arrays.asList("Ukoliko imate ovu opciju, možete gledati svoje omiljene programe 72h unazad!","","");

    private List<String> phoneQuestions=Arrays.asList("Koliko vremena dnevno razgovarate putem telefona?",
            "Koliko gigabajta interneta Vam je dovoljno za jedan mesec?",
            "Da li često koristite telefon u romingu?");
    private List<String> phoneAnswers=Arrays.asList("Manje od 5 minuta/10 minuta/20 minuta/Više od 20 minuta",
            "manje od 3/5/10/Više od 20","Da/Ne");
    private List<String> phoneHints=Arrays.asList("Prosečna osoba dnevno potroši oko minut i po na razgovor.","U proseku, 3 gigabajta interneta mesečno potroši se samo za instagram!",
            "");

    private List<String> internetQuestions=Arrays.asList("Koja brzina interneta vam najviše odgovara?");
    private List<String> internetAnswers=Arrays.asList("20/4 mb/s:50/8 mb/s:100/10 mb/s:200/40 mb/s");
    private List<String> internetHints=Arrays.asList("Pri brzini od 100/10, u proseku, film visokog kvaliteta mogli biste da skinete za manje od minuta!");

    private List<String> boxQuestions=new ArrayList<>();
    private List<String> boxAnswers=new ArrayList<>();
    private List<String> boxHints=new ArrayList<>();
    //</editor-fold>

    private List<String> selectedQ=null;
    private List<String> selectedA=null;
    private List<String> selectedH=null;

    private PaketMobilni paketMobilniSelected=null;
    private PaketTV paketTVSelected=null;
    private PaketNet paketNetSelected=null;
    private BoxPaket boxPaketSelected=null;

    private Button answer1, answer2, answer3, answer4,ans1,ans2;

    private ArrayList<PaketMobilni> mobilniPaketi;
    private ArrayList<PaketTV> tvPaketi;
    private ArrayList<PaketNet> netPaketi;
    private ArrayList<BoxPaket> boxPaketi;

    //Ova array lista je lista odgovora korisnika
    private ArrayList<String> odgovori=new ArrayList<>();

    private boolean sound=false,mic=false,prolazi=false;

    private ImageView imgMic, imgSound;
    private TextToSpeech mTTS;

    private CountDownTimer t = new CountDownTimer(5000, 5000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            final Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.left_to_right);

            constHint.startAnimation(animSlide);
            constHint.setVisibility(View.INVISIBLE);
        }
    };


    int fminuti(String min)
    {
        if(min.equals("Manje od 5 minuta")) return 30*5;
        else if(min.equals("10 minuta")) return 30*10;
        else if(min.equals("20 minuta")) return 30*20;
        return 30*30;
    }

    int fporuke(String por)
    {
        return 0;
    }

    int finternet(String net)
    {
        if(net.equals("manje od 3")) return 3;
        else if(net.equals("5")) return 5;
        else if(net.equals("10")) return 10;
        return 20;
    }

    boolean froming(String rom)
    {
        return rom.equals("Da");
    }

    int fnazad(String naz)
    {
       if(naz.equals("Da")) return 7;
       return 0;
    }

    boolean fsnimaj(String sni)
    {
        return sni.equals("Da");
    }

    int fbrzina(String brz)
    {
        if(brz.equals("20/4 mb/s")) return 20;
        else if(brz.equals("50/8 mb/s")) return 50;
        else if(brz.equals("100/10 mb/s")) return 100;
        return 200;
    }


    private void runAlgo()
    {
        //todo
        //treba da se ubace pitanja za to kolko je nesto bitno

       String birao = odgovori.get(0);
       if(birao.equals("Box"))
       {
           Algoritam algoritam = new Algoritam(tvPaketi,mobilniPaketi,netPaketi,fminuti(odgovori.get(1)),fporuke(odgovori.get(2)),finternet(odgovori.get(3)),froming(odgovori.get(4)),
                   1,2,3,4,5,fbrzina(odgovori.get(5)),250,fnazad(odgovori.get(6)),fsnimaj(odgovori.get(7)),true,1,2,3,4,5,6);
           int [] score = algoritam.runAlgo();
           Map<BoxPaket,Integer> paketi = new HashMap<BoxPaket,Integer>();
           for(int i=0;i<boxPaketi.size();i++)
           {
               paketi.put(boxPaketi.get(i),score[i]);
           }
           Map<BoxPaket,Integer> sortirano = new TreeMap<BoxPaket,Integer>(paketi);

       }
       else if(birao.equals("Mobilni"))
       {
           AlgoritamMobilni amob = new AlgoritamMobilni(mobilniPaketi,fminuti(odgovori.get(1)),fporuke(odgovori.get(2)),finternet(odgovori.get(3)),froming(odgovori.get(4)),1,2,3,4);
           int [] score = amob.runAlgo();
           Map<PaketMobilni,Integer> paketi = new HashMap<PaketMobilni,Integer>();
           for(int i=0;i<mobilniPaketi.size();i++)
           {
               paketi.put(mobilniPaketi.get(i),score[i]);
           }
           Map<PaketMobilni,Integer> sortirano = new TreeMap<PaketMobilni,Integer>(paketi);
       }
       else if(birao.equals("TV"))
       {
           AlgoritamTV atv = new AlgoritamTV(tvPaketi,250,fnazad(odgovori.get(1)),fsnimaj(odgovori.get(2)),true,1,2,3);
           int [] score = atv.runAlgo();
           Map<PaketTV,Integer> paketi = new HashMap<PaketTV,Integer>();
           for(int i=0;i<tvPaketi.size();i++)
           {
               paketi.put(tvPaketi.get(i),score[i]);
           }
           Map<PaketTV,Integer> sortirano = new TreeMap<PaketTV,Integer>(paketi);
       }
       else
       {
           AlgoritamNet anet = new AlgoritamNet(netPaketi,fbrzina(odgovori.get(1)),1);
           int [] score = anet.runAlgo();
           Map<PaketNet,Integer> paketi = new HashMap<PaketNet,Integer>();
           for(int i=0;i<netPaketi.size();i++)
           {
               paketi.put( netPaketi.get(i),score[i]);
           }
           Map<PaketNet,Integer> sortirano = new TreeMap<PaketNet,Integer>(paketi);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(ConversationActivity.this);
        setContentView(R.layout.activity_conversation);

        init();

        scaledroneConnection();

        sendBasicQuestion();

        //<editor-fold desc="speech init">
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(new Locale("sr-RS"));

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        imgSound.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="Buttons">
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               posaljiPoruku(internetQuestions,internetAnswers,internetHints,answer1.getText().toString().trim());
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiPoruku(tvQuestions,tvAnswers,tvHints,answer3.getText().toString().trim());
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiPoruku(phoneQuestions,phoneAnswers,phoneHints,answer2.getText().toString().trim());
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiPoruku(boxQuestions,boxAnswers,boxHints,answer4.getText().toString().trim());

            }
        });
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(ans1.getText().toString().trim());
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(ans2.getText().toString().trim());
            }
        });

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sound) {
                    imgSound.setImageResource(R.drawable.sound);
                    sound=true;
                }
                else{
                    imgSound.setImageResource(R.drawable.soundoff);
                    sound=false;
                }
            }
        });

        imgMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mic) {
                    imgMic.setImageResource(R.drawable.mic);
                    mic=true;
                }
                else{
                    imgMic.setImageResource(R.drawable.micoff);
                    mic=false;
                }
            }
        });
        //</editor-fold>
    }

    //<editor-fold desc="Messages">
    private void posaljiPoruku(List<String> listaPitanja,List<String>listaOdgovora,List<String> listaHintova,String text) {
        if (indexOfQuestion == 0) {
            selectedH = listaHintova;
            selectedQ = listaPitanja;
            selectedA = listaOdgovora;
        }

        sendMessage(text);
    }

    public void sendMessage(String message) {
        if (message.length() > 0) {
            odgovori.add(message);
            sendMyMessage(message);
            setQuestion();
        }
    }
    private void setQuestion()
    {
        if(indexOfQuestion<selectedA.size()) {
            sendComputerMessage(selectedQ.get(indexOfQuestion));
            setAnswers();
            indexOfQuestion++;
        } else
            Toast.makeText(ConversationActivity.this,"Hvala Vam na odgovorima!",Toast.LENGTH_SHORT).show();

    }
    private void setAnswers() {
        if (!selectedA.get(indexOfQuestion).equals(internetAnswers.get(0))) {
            String s = selectedA.get(indexOfQuestion);
            split = s.split("/");
        } else {
            String s = selectedA.get(indexOfQuestion);
            split = s.split(":");
        }

        if(!selectedH.get(indexOfQuestion).equals("")) {
            show();
        }

        hide();

        if (split.length == 2) {
            constraintLayout2.setVisibility(View.VISIBLE);
            constraintLayout4.setVisibility(View.INVISIBLE);

            ans1.setText(split[0]);
            ans2.setText(split[1]);
        } else {
            constraintLayout4.setVisibility(View.VISIBLE);
            constraintLayout2.setVisibility(View.INVISIBLE);

            answer1.setText(split[0]);
            answer2.setText(split[1]);
            answer3.setText(split[2]);
            answer4.setText(split[3]);
        }

    }

    private void sendBasicQuestion()
    {
        sendComputerMessage("Zdravo! Ja sam Sebastijan i pomoci cu ti da odaberes najbolje za sebe!"+
                "\n"+"\n"
        + "Koja od ovih ponuda te najvise zanima?");

        constraintLayout2.setVisibility(View.GONE);

    }
    private void sendComputerMessage(String text) {
        final MemberData data = new MemberData("Sebastijan", Color.LTGRAY);
        final Message message = new Message(text, data, false);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });
        if (sound)
            speak(text);
    }
    private void sendMyMessage(String text)
    {
        final MemberData data = new MemberData("Sebastijan",Color.LTGRAY);
        final Message message = new Message(text, data, true);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc="Animations">
    private void show()
    {
        constHint.setVisibility(View.VISIBLE);
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.right_to_left);
        tvHint.setText(selectedH.get(indexOfQuestion));
        constHint.startAnimation(animSlide);
    }

    private void hide()
    {
        t.start();
    }
    //</editor-fold>

    //<editor-fold desc="paketi">
    PaketMobilni pm;
    PaketNet pn;
    BoxPaket pb;
    PaketTV pt;
    int inf;

    void napraviMobilniPaket(String ime,int cena,int minuti,int minutiMreza,int sms,int internet,int gbProstora,int minutiRoming,
                             ArrayList<String> aplikacijeInternet,boolean josJedanGbZaKupovinu,boolean porukeRoming,boolean internetRoming)
    {
        pm = new PaketMobilni(ime,cena,minuti,minutiMreza,sms, internet, gbProstora, minutiRoming,
         aplikacijeInternet,josJedanGbZaKupovinu, porukeRoming, internetRoming);
        mobilniPaketi.add(pm);
    }

    void napraviTvPaket(String ime,int cena,int brojKanala,boolean snimanjeSadrzaja,int gledanjaNazad,boolean pauziranje,ArrayList<String> videoKlub,int hdkanali)
    {
        pt = new PaketTV(ime,cena,brojKanala,snimanjeSadrzaja,gledanjaNazad,pauziranje,videoKlub,hdkanali);
        tvPaketi.add(pt);
    }

    void napraviNetPaket(String ime,int cena,int download,int upload)
    {
        pn = new PaketNet(ime,cena,download,upload);
        netPaketi.add(pn);
    }

    void napraviBox(String ime,int cena,PaketMobilni pm,PaketTV pt,PaketNet pn)
    {
        pb = new BoxPaket(ime,cena,pn,pt,pm);
        boxPaketi.add(pb);
    }

    void napraviPakete()
    {
        netPaketi=new ArrayList<>();
        tvPaketi = new ArrayList<>();
        boxPaketi = new ArrayList<>();
        mobilniPaketi = new ArrayList<>();

        //mobilni paketi
        ArrayList<String> apps = new ArrayList<>();
        napraviMobilniPaket("Morava 1",800,100,Integer.MAX_VALUE,100,256,10,0,
                apps,false,false,false);
        napraviMobilniPaket("Morava 2",1300,300,Integer.MAX_VALUE,300,1024,10,0,apps,false,
                false,false);
        apps.add("Viber");
        napraviMobilniPaket("Omorika 1",2000,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,3072,30,100,
                apps,true,false,false);
        apps.add("Facebook");
        apps.add("Instagram");
        apps.add("WhatsApp");
        apps.add("Twitter");
        napraviMobilniPaket("Omorika 2",2700,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,10240,30,200,
                apps,true,false,false);
        napraviMobilniPaket("Omorika 3",4000,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,5*10240,30,300,
                apps,true,false,false);
        napraviMobilniPaket("Soko",6000,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,20*10240,100,Integer.MAX_VALUE,
                apps,true,true,true);

        //tv paketi
        ArrayList<String> videoklub = new ArrayList<>();
        videoklub.add("Arena sport");
        videoklub.add("Disney");
        videoklub.add("AXN Now");
        videoklub.add("Exit");
        videoklub.add("Rts");
        napraviTvPaket("Start",1200,140,false,0,true,videoklub,29);
        videoklub.add("PickBox");
        videoklub.add("Minimax plus");
        videoklub.add("Epic Drama");
        napraviTvPaket("Plus",1600,200,false,7,true,videoklub,29);
        videoklub.add("HBO");
        videoklub.add("Filmbox");
        napraviTvPaket("Max",2000,250,true,7,true,videoklub,35);

        napraviNetPaket("20",1700,20,4);
        napraviNetPaket("50",1800,50,8);
        napraviNetPaket("100",2400,100,10);
        napraviNetPaket("200",3400,200,40);
        napraviNetPaket("400",3800,400,80);
        napraviNetPaket("1000",9000,1000,200);

        napraviBox("box 1",4000,mobilniPaketi.get(0),tvPaketi.get(0),netPaketi.get(0));
        napraviBox("box 2",4800,mobilniPaketi.get(1),tvPaketi.get(1),netPaketi.get(1));
        napraviBox("box 3",6400,mobilniPaketi.get(2),tvPaketi.get(2),netPaketi.get(2));
        napraviBox("box 4",12000,mobilniPaketi.get(3),tvPaketi.get(2),netPaketi.get(5));

    }
    //</editor-fold>

    //<editor-fold desc="Neke funkcije">
    @Override
    public void onOpen(Room room) {
        System.out.println("Connected to room");
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    @Override
    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final MemberData data = mapper.treeToValue(receivedMessage.getMember().getClientData(), MemberData.class);
            boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());
            final Message message = new Message(receivedMessage.getData().asText(), data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    messagesView.setSelection(messagesView.getCount()-1);
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void scaledroneConnection()
    {
        MemberData data = new MemberData("Sebastijan", Color.LTGRAY);

        scaledrone = new Scaledrone(channelID, data);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                System.out.println("Scaledrone connection open");
                scaledrone.subscribe(roomName, ConversationActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc="init">
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.status_bar_gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    private void init()
    {
        getSupportActionBar().hide();

        answer1=(Button)findViewById(R.id.odgovor1);
        answer2=(Button)findViewById(R.id.odgovor2);
        answer3=(Button)findViewById(R.id.odgovor3);
        answer4=(Button)findViewById(R.id.odgovor4);

        ans1=(Button)findViewById(R.id.odg1);
        ans2=(Button)findViewById(R.id.odg2);

        tvHint=(TextView)findViewById(R.id.tHint);
        tvHint.bringToFront();

        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        constraintLayout4=(ConstraintLayout)findViewById(R.id.linearLayout2);
        constraintLayout2=(ConstraintLayout)findViewById(R.id.dvabuttona);
        constHint=(ConstraintLayout)findViewById(R.id.cHint);

        constHint.setVisibility(View.INVISIBLE);

        imgMic=(ImageView)findViewById(R.id.imgMic);
        imgSound=(ImageView)findViewById(R.id.imgSound);

        boxQuestions.addAll(internetQuestions);
        boxQuestions.addAll(phoneQuestions);
        boxQuestions.addAll(tvQuestions);

        boxAnswers.addAll(internetAnswers);
        boxAnswers.addAll(phoneAnswers);
        boxAnswers.addAll(tvAnswers);

        boxHints.addAll(internetHints);
        boxHints.addAll(phoneHints);
        boxHints.addAll(tvHints);

        napraviPakete();

    }
    //</editor-fold>

    //<editor-fold desc="Speech Recognition">
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "sr-RS");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (nadjiOdgovor(result.get(0)).equals("")) {
                        Toast.makeText(ConversationActivity.this, "Nismo uspeli da pronadjemo vas odgovor", Toast.LENGTH_SHORT).show();
                        getSpeechInput(ConversationActivity.this.getCurrentFocus());
                    }
                    else {
                        if (indexOfQuestion>0)
                            sendMessage(nadjiOdgovor(result.get(0)));
                        else
                        {
                            Log.d("Ovde",result.get(0).toLowerCase().trim());
                            String s=result.get(0).toLowerCase().trim();
                            if(s.equals("internet"))
                                posaljiPoruku(internetQuestions,internetAnswers,internetHints,s);
                            else if(s.equals("poruke"))
                                posaljiPoruku(tvQuestions,tvAnswers,tvHints,s);
                            else if(s.equals("minuti"))
                                posaljiPoruku(phoneQuestions,phoneAnswers,phoneHints,s);
                            else
                                posaljiPoruku(boxQuestions,boxAnswers,boxHints,s);
                        }

                    }
                }
                break;
        }
    }

    private String nadjiOdgovor(String reportedSpeech) {

        String s;
        if(indexOfQuestion>0) {
            s = selectedA.get(indexOfQuestion-1);
        }
        else s="Internet/Poruke/Mobilni/Box paketi";

        String[] split = s.split("/");

        for (int i = 0; i < split.length; i++) {
            if (reportedSpeech.toLowerCase().contains(split[i].toLowerCase()))
                return split[i];
        }

        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Text to Speech">
    private void speak(String s) {
        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);

        prolazi=false;

        if(mic) {
            final Handler h = new Handler();

            r = new Runnable() {

                public void run() {

                    if (!mTTS.isSpeaking() && !prolazi) {
                        getSpeechInput(ConversationActivity.this.getCurrentFocus());
                        prolazi=true;
                        h.removeCallbacks(r);
                    }

                    h.postDelayed(this, 1000);
                }
            };

            h.postDelayed(r, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }

    //</editor-fold>

    //<editor-fold desc="nesto za slanje paketa">
    private void showPaket() {
        if(paketMobilniSelected!=null) {
            Intent intent = new Intent(ConversationActivity.this, PaketActivity.class);
            intent.putExtra("vrsta","mobilni");
            intent.putExtra("paket", (Serializable) paketMobilniSelected);
        }
        else if(paketNetSelected!=null) {
            Intent intent = new Intent(ConversationActivity.this, PaketActivity.class);
            intent.putExtra("vrsta","net");
            intent.putExtra("paket", (Serializable) paketNetSelected);
        }
        else if(paketTVSelected!=null){
            Intent intent = new Intent(ConversationActivity.this, PaketActivity.class);
            intent.putExtra("vrsta","tv");
            intent.putExtra("paket", (Serializable) paketTVSelected);
        }
        else{
            Intent intent = new Intent(ConversationActivity.this, PaketActivity.class);
            intent.putExtra("vrsta","box");
            intent.putExtra("paket", (Serializable) boxPaketSelected);
        }
    }
    //</editor-fold>

}


