package com.example.doctorsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView userName = view.findViewById(R.id.userName);
        TextView userEmail = view.findViewById(R.id.userEmail);
        try {
            String data[] = getData();
            userName.setText(data[0]);
            userEmail.setText(data[1]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            getDates();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Inflate the layout for this fragment
        return view;
    }

    public String[] getDates() throws IOException {
        File fileEvents = new File(getActivity().getFilesDir().toString().concat("/text/dates"));
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileEvents));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        br.close();
        System.out.println("[[".concat(text.toString()).concat("]]"));
        String data[] = text.toString().split(":");
        for (String value : data) {
            System.out.println(value);
        }
        System.out.println(getActivity().getFilesDir());
        return data;
    }

    public String[] getData() throws IOException {
        File fileEvents = new File(getActivity().getFilesDir().toString().concat("/text/sample"));
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileEvents));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        br.close();
        System.out.println("[[".concat(text.toString()).concat("]]"));
        String data[] = text.toString().split(":");
        for (String value : data) {
            System.out.println(value);
        }
        System.out.println(getActivity().getFilesDir());
        return data;
    }
}