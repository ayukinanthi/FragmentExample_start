package com.example.android.fragmentexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {
    private static final String CHOICE="choice";
    private int mRadioButtonChoice=2; //default 2 karena pilihannya user belom milih. kalau 0 kan yes, kalau 1 itu no
    private OnFragmentInteractionListener mListener; //inisialisasi dari interface

    public SimpleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice); //choice itu tergantung datanya yg mau dikirim
        fragment.setArguments(args); //kirim dari activity ke fragmet
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //biar bisa ngemodif yg ada di layoutnya fragment
        final View rootView=inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup=rootView.findViewById(R.id.radio_group);
        final TextView textView=rootView.findViewById(R.id.fragment_header);

        if(getArguments().containsKey(CHOICE)){
            mRadioButtonChoice=getArguments().getInt(CHOICE);
            if(mRadioButtonChoice!=2){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId()); //cek radio buttonnya
            }
        }
        //untuk ngecek button mana yg dipilih dr radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                View radioButton=radioGroup.findViewById(i);
                //cek urutan index nya (itukan Yes tuh index ke-0, si No index ke-1)
                int index=radioGroup.indexOfChild(radioButton);
                switch(index) {
                    case 0:
                        //kalo pilih yes
                        textView.setText("ARTICLE: Like!");
                        mRadioButtonChoice = 0;
                        break;
                    //kalo pilin No
                    case 1:
                        textView.setText("ARTICLE: Thanks!");
                        mRadioButtonChoice = 1;
                        break;
                    //default saat belum memilih
                    default:
                        mRadioButtonChoice = 2;
                        break;
                }
                    mListener.onRadioButtonChoice(mRadioButtonChoice);


            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener=(OnFragmentInteractionListener) context;
        }

    }

    public interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }
}