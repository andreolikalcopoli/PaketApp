package com.example.paketapp.PaketFragmenti;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paketapp.Adapteri.NetAdapter;
import com.example.paketapp.Adapteri.TvAdapter;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.Paketi.PaketTV;
import com.example.paketapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ArrayList<PaketTV> tvPaketi=new ArrayList<>();
    private PaketTV[] tvs=new PaketTV[3];
    private ScrollView mScrollView;
    private FrameLayout mWrapperFL;
    private ImageView mPhotoIV;

    private OnFragmentInteractionListener mListener;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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
        View view=inflater.inflate(R.layout.fragment_tab3, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recTv);
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView2);
        mPhotoIV = (ImageView) view.findViewById(R.id.imageView7);
        mWrapperFL = (FrameLayout) view.findViewById(R.id.flWrapper2);

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new Tab3.ScrollPositionObserver());

        napraviPakete();

        setRecycler();

        return view;
    }

    private class ScrollPositionObserver implements ViewTreeObserver.OnScrollChangedListener {

        private int mImageViewHeight;

        public ScrollPositionObserver() {
            mImageViewHeight = getResources().getDimensionPixelSize(R.dimen.contact_photo_height2);
        }

        @Override
        public void onScrollChanged() {
            int scrollY = Math.min(Math.max(mScrollView.getScrollY(), 0), mImageViewHeight);

            // changing position of ImageView
            mPhotoIV.setTranslationY(scrollY / 3);

            // alpha you could set to ActionBar background
            float alpha = scrollY / (float) mImageViewHeight;
        }
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

    //<editor-fold desc="paketi">
    PaketTV pt;

    private PaketTV[] konvertuj()
    {
        tvs=new PaketTV[tvPaketi.size()];
        for(int i=0;i<tvPaketi.size();i++)
            tvs[i]=tvPaketi.get(i);

        return tvs;
    }

    void napraviTvPaket(String ime, int cena, int brojKanala, boolean snimanjeSadrzaja, int gledanjaNazad, boolean pauziranje, ArrayList<String> videoKlub, int hdkanali)
    {
        pt = new PaketTV(ime,cena,brojKanala,snimanjeSadrzaja,gledanjaNazad,pauziranje,videoKlub,hdkanali);
        tvPaketi.add(pt);
    }

    void napraviPakete()
    {
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

    }
    //</editor-fold>

    private void setRecycler(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        TvAdapter tvAdapter = new TvAdapter(getContext(),konvertuj());
        recyclerView.setAdapter(tvAdapter);
    }
}
