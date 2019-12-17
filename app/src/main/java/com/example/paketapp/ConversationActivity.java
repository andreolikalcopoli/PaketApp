package com.example.paketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paketapp.Classes.Message;
import com.example.paketapp.Classes.MessageAdapter;
import com.example.paketapp.Paketi.BoxPaket;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dots.animation.textview.TextAndAnimationView;

public class ConversationActivity extends AppCompatActivity implements RoomListener {

    private String channelID = "2JYnAoVFhHG0tBcn";
    private String roomName = "observable-room";
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private ConstraintLayout constraintLayout4,constraintLayout2;

    private int indexOfQuestion=0;

    //<editor-fold desc="Pitanja">
    /*private List<String> questions = Arrays.asList("Da li koristite telefon za drustvene mreze ili razgovor i poruke?",
            "Koliko vremena dnevno razgovarate putem telefona?",
            "Koje drustvene mreze najvise koristite?",
            "Koliko gb interneta vam je dovoljno za jedan dan?",
            "Da li se dopisujete preko poruka?",
            "Da li cesto putujete u inostranstvo?",
            "Da li Vam je interet znacajan na poslu?",
            "Da li koristite internet i u romingu?",
            "Da li uglavnom razgovarate telefonom sa ljudima iz Vase mreze?",
                    "Da li ste umrezeni sa porodicom?",
                    "Da li koristite cloud servise za cuvanje svojih dokumenata?",
                    "Izaberite koliko memorije bi Vam bilo dovoljno?",
                    "Da li zelite paket samo za telefon ili i box?",
                    "Oznacite vrste kanala koje gledate - (politika,sport,dokumentarni)",
    "Koliko cesto propustite uzivo program onoga sto zelite da vidite?",
    "Da li sa trenutnim paketom uspevate da to kasnije pogledate?",
    "Koliko cesto gledate filmove?");*/
    private List<String> messagesQuestions=Arrays.asList("Da li se dopisujete preko poruka?",
            "Da li cesto saljete MMS poruke?",
            "Koliko poruka posaljete u toku dana?");
    private List<String> messageAnswers=Arrays.asList("Da/Ne","Da/Ne","Manje od 3/Od 3 do 10/Vise od 10/Vise od 20");
    private List<String> callsQuestions=Arrays.asList("Koliko vremena dnevno razgovarate putem telefona?",
            "Da li uglavnom razgovarate telefonom sa ljudima iz Vase mreze?",
            "Da li ste umrezeni sa porodicom?",
            "Da li vas posao zahteva da stalno budete na usluzi?");
    private List<String> callsAnswers=Arrays.asList("Manje od 5 minuta/5 do 10 minuta/10 do 20 minuta/vise od 20 minuta",
            "Da/Ne","Da/Ne","Da/Ne");
    private List<String> internetQuestions=Arrays.asList("Da li Vam je interet znacajan za posao?"
            ,"Koje drustvene mreze najvise koristite?",
            "Koliko gb interneta vam je dovoljno za jedan mesex?",
            "Da li koristite internet i u romingu?",
            "Da li koristite cloud servise za cuvanje svojih dokumenata?");
    private List<String> internetAnswers=Arrays.asList("Da/Ne","Instagram/Facebook/Tweeter/Nesto drugo","1gb/5gb/10gb/Vise od 10gb",
            "Da/Ne","Da/Ne");
    private List<String> boxQuestions=Arrays.asList("Da li zelite paket samo za telefon ili i box?",
            "Oznacite vrste kanala koje gledate:",
            "Koliko cesto propustite uzivo program onoga sto zelite da vidite?",
            "Da li sa trenutnim paketom uspevate da to kasnije pogledate?",
            "Koliko cesto gledate filmove?");
    private List<String> boxAnswers=Arrays.asList("Da/Ne","Politika/Sport/Dokumentarci/Nesto drugo",
            "Jednom dnevno/Jednom nedeljno/Jednom mesecno/Ne propustam","Da/Ne","Svakog dana/Svake nedelje/Jednom mesecno/Ne gledam filmove");
    //</editor-fold>

    private List<String> selectedQ=null;
    private List<String> selectedA=null;

    private Button answer1, answer2, answer3, answer4,ans1,ans2;

    ArrayList<PaketMobilni> mobilniPaketi;
    ArrayList<PaketTV> tvPaketi;
    ArrayList<PaketNet> netPaketi;
    ArrayList<BoxPaket> boxPaketi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(ConversationActivity.this);
        setContentView(R.layout.activity_conversation);

        init();

        scaledroneConnection();

        sendBasicQuestion();

        //<editor-fold desc="Buttons">
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexOfQuestion==0) {
                    selectedQ = internetQuestions;
                    selectedA = internetAnswers;
                }
                sendMessage(v, answer1.getText().toString().trim());
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexOfQuestion==0) {
                    selectedQ = messagesQuestions;
                    selectedA = messageAnswers;
                }
                sendMessage(v,answer2.getText().toString().trim());
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexOfQuestion==0) {
                    selectedQ = callsQuestions;
                    selectedA = callsAnswers;
                }
                sendMessage(v,answer3.getText().toString().trim());
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexOfQuestion==0) {
                    selectedQ = boxQuestions;
                    selectedA = boxAnswers;
                }
                sendMessage(v,answer4.getText().toString().trim());

            }
        });
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v,ans1.getText().toString().trim());
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v,ans2.getText().toString().trim());
            }
        });
        //</editor-fold>
    }

    //<editor-fold desc="Messages">
    public void sendMessage(View view, String message) {
        if (message.length() > 0) {
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
    private void setAnswers()
    {
            String s = selectedA.get(indexOfQuestion);
            String[] split = s.split("/");

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
        sendComputerMessage("Zdravo! Ja sam Sebastijan i pomocicu ti da odaberes najbolje za sebe!"+
                "\n"+"\n"
        + "Koja od ovih ponuda te najvise zanima?");

        constraintLayout2.setVisibility(View.GONE);

    }
    private void sendComputerMessage(String text)
    {
        final MemberData data = new MemberData("Sebastijan",Color.LTGRAY);
        final Message message = new Message(text, data, false);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });
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

    private void init()
    {
        getSupportActionBar().hide();

        answer1=(Button)findViewById(R.id.odgovor1);
        answer2=(Button)findViewById(R.id.odgovor2);
        answer3=(Button)findViewById(R.id.odgovor3);
        answer4=(Button)findViewById(R.id.odgovor4);

        ans1=(Button)findViewById(R.id.odg1);
        ans2=(Button)findViewById(R.id.odg2);

        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        constraintLayout4=(ConstraintLayout)findViewById(R.id.linearLayout2);
        constraintLayout2=(ConstraintLayout)findViewById(R.id.dvabuttona);

        napraviPakete();

    }

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

    void napraviTvPaket(String ime,int cena,int brojKanala,boolean snimanjeSadrzaja,int gledanjaNazad,boolean pauziranje,ArrayList<String> videoKlub)
    {
        pt = new PaketTV(ime,cena,brojKanala,snimanjeSadrzaja,gledanjaNazad,pauziranje,videoKlub);
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
        napraviTvPaket("Start",1200,140,false,0,true,videoklub);
        videoklub.add("PickBox");
        videoklub.add("Minimax plus");
        videoklub.add("Epic Drama");
        napraviTvPaket("Plus",1600,200,false,7,true,videoklub);
        videoklub.add("HBO");
        videoklub.add("Filmbox");
        napraviTvPaket("Max",2000,250,true,7,true,videoklub);

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
}

