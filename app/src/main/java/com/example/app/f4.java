package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f4.
     */
    // TODO: Rename and change types and number of parameters
    public static f4 newInstance(String param1, String param2) {
        f4 fragment = new f4();
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
        View v= inflater.inflate(R.layout.fragment_f4, container, false);

        FrameLayout simpleFrameLayout = (FrameLayout) v.findViewById(R.id.simpleFrameLayout);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.simpleTabLayout);

        TabLayout.Tab t1 = tabLayout.newTab();
        t1.setText("Task");
        tabLayout.addTab(t1);

        TabLayout.Tab t2 = tabLayout.newTab();
        t2.setText("Attendance");
        tabLayout.addTab(t2);

//        TabLayout.Tab t3 = tabLayout.newTab();
//        t3.setText("Third");    t3.setIcon(R.drawable.hi);
//        tabLayout.addTab(t3);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment f1 = null;
                switch (tab.getPosition()) {
                    case 0: f1 = new f41();    break;
                    case 1: f1 = new f42();  break;
//                    case 2: f1 = new thirdFragment();   break;
                }
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.simpleFrameLayout, f1);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return v;
    }
}