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

import com.example.paketapp.Adapteri.MobilniAdapter;
import com.example.paketapp.Adapteri.NetAdapter;
import com.example.paketapp.Paketi.PaketMobilni;
import com.example.paketapp.Paketi.PaketNet;
import com.example.paketapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ArrayList<PaketNet> netPaketi=new ArrayList<>();
    private PaketNet[] nets=new PaketNet[6];
    private ScrollView mScrollView;
    private FrameLayout mWrapperFL;
    private ImageView mPhotoIV;

    private OnFragmentInteractionListener mListener;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
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

        View view=inflater.inflate(R.layout.fragment_tab2, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recNet);
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);
        mPhotoIV = (ImageView) view.findViewById(R.id.imageView5);
        mWrapperFL = (FrameLayout) view.findViewById(R.id.flWrapper);

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ScrollPositionObserver());

        napraviPakete();

        setRecycler();

        return view;
    }

    private class ScrollPositionObserver implements ViewTreeObserver.OnScrollChangedListener {

        private int mImageViewHeight;

        public ScrollPositionObserver() {
            mImageViewHeight = getResources().getDimensionPixelSize(R.dimen.contact_photo_height);
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
    PaketNet pn;

    private PaketNet[] konvertuj()
    {
        nets=new PaketNet[netPaketi.size()];
        for(int i=0;i<netPaketi.size();i++)
            nets[i]=netPaketi.get(i);

        return nets;
    }

    void napraviNetPaket(String ime,int cena,int download,int upload)
    {
        pn = new PaketNet(ime,cena,download,upload);
        netPaketi.add(pn);
    }

    void napraviPakete()
    {
        //mobilni paketi
        napraviNetPaket("20",1700,20,4);
        napraviNetPaket("50",1800,50,8);
        napraviNetPaket("100",2400,100,10);
        napraviNetPaket("200",3400,200,40);
        napraviNetPaket("400",3800,400,80);
        napraviNetPaket("1000",9000,1000,200);

    }
    //</editor-fold>

    private void setRecycler(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        NetAdapter netAdapter = new NetAdapter(getContext(),konvertuj());
        recyclerView.setAdapter(netAdapter);
    }
}
