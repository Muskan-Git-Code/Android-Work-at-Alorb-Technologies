package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f1 extends Fragment {

    TextView tv1, tv2, tv3, tv4;
    static ImageButton ib1;
    static Integer c=0;
    ToggleButton tb1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f1.
     */
    // TODO: Rename and change types and number of parameters
    public static f1 newInstance(String param1, String param2) {
        f1 fragment = new f1();
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
        View v= inflater.inflate(R.layout.fragment_f1, container, false);

        tv1= (TextView)v.findViewById(R.id.textView9);
        tv2= (TextView)v.findViewById(R.id.textView10);
        tv3= (TextView)v.findViewById(R.id.textView12);
        tv4= (TextView)v.findViewById(R.id.textView14);
        tb1= (ToggleButton)v.findViewById(R.id.toggleButton);
        tb1.setText("Start Timer");
        tb1.setTextOn("Pause Timer");
        tb1.setTextOff("Start Timer");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a \n EEE, MMM d");
        String currentDateandTime = sdf.format(new Date());
        tv1.setText(currentDateandTime);

        ib1= (ImageButton)v.findViewById(R.id.imageButton);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                String c3 = sdf1.format(new Date());
                c++;
                if(c==1){
                    tv2.setText(c3);
                    ib1.setImageResource(R.drawable.cout);
                    ib1.setImageAlpha(R.drawable.cout);
                    c++;
                }
                else{
                    tv4.setText(c3);
                }

            }
        });


        return v;
    }
}