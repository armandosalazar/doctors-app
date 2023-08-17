package com.example.doctorsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctorsapp.models.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_dates);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        try {
            String[] data = getUserData();

            userName.setText(data[0]);
            userEmail.setText(data[1]);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Date> dates = getDates();
            for (Date date : dates) {
                System.out.println(date);
            }
            DatesAdapter datesAdapter = new DatesAdapter(dates);
            recyclerView.setAdapter(datesAdapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Inflate the layout for this fragment
        return view;
    }

    public List<Date> getDates() throws IOException {
        File file = new File(requireContext().getFilesDir(), "dates.txt");
        List<Date> dates = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(":");
            Date date = new Date();
            date.setNames(data[0]);
            date.setLastNames(data[1]);
            date.setPhone(data[2]);
            date.setSymptoms(data[3]);
            date.setSex(data[4]);
            dates.add(date);
        }
        bufferedReader.close();

        return dates;

    }

    public String[] getUserData() throws IOException {
        File file = new File(requireContext().getFilesDir(), "users.txt");

        StringBuilder text = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }

        bufferedReader.close();

        System.out.println("[[".concat(text.toString()).concat("]]"));

        String[] data = text.toString().split(":");

        for (String value : data) {
            System.out.println(value);
        }
        System.out.println(requireContext().getFilesDir());
        return data;
    }
}