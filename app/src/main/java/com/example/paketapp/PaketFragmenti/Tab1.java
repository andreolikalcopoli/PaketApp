package com.example.paketapp.PaketFragmenti;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paketapp.Adapteri.MobilniAdapter;
import com.example.paketapp.Paketi.BoxPaket;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.example.paketapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PaketMobilni> mobilniPaketi=new ArrayList<>();
    private PaketMobilni[] paketMobilnis=new PaketMobilni[9];

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_tab1, container, false);

        napraviPakete();

        recyclerView=(RecyclerView)view.findViewById(R.id.recMobilni);

        setRecycler();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setRecycler(){
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        MobilniAdapter mobilniAdapter = new MobilniAdapter(getContext(),konvertuj());
        recyclerView.setAdapter(mobilniAdapter);
    }

    //<editor-fold desc="paketi">
    PaketMobilni pm;

    private PaketMobilni[] konvertuj()
    {
        paketMobilnis=new PaketMobilni[mobilniPaketi.size()];
        for(int i=0;i<mobilniPaketi.size();i++)
            paketMobilnis[i]=mobilniPaketi.get(i);

        return paketMobilnis;
    }

    void napraviMobilniPaket(String ime, int cena, int minuti, int minutiMreza, int sms, int internet, int gbProstora, int minutiRoming,
                             ArrayList<String> aplikacijeInternet, boolean josJedanGbZaKupovinu, boolean porukeRoming, boolean internetRoming)
    {
        pm = new PaketMobilni(ime,cena,minuti,minutiMreza,sms, internet, gbProstora, minutiRoming,
                aplikacijeInternet,josJedanGbZaKupovinu, porukeRoming, internetRoming);
        mobilniPaketi.add(pm);
    }


    void napraviPakete()
    {

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

    }
    //</editor-fold>

}
