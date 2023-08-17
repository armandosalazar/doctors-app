package com.example.doctorsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.doctorsapp.models.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatesFragment extends Fragment {
    private EditText names, lastnames, phone, symptoms;
    private Switch urgency;
    private RadioButton man, woman;
    private Button buttonSave;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatesFragment newInstance(String param1, String param2) {
        DatesFragment fragment = new DatesFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dates, container, false);
        names = view.findViewById(R.id.names);
        lastnames = view.findViewById(R.id.lastnames);
        phone = view.findViewById(R.id.phone);
        symptoms = view.findViewById(R.id.symptoms);
        urgency = view.findViewById(R.id.urgency);
        man = view.findViewById(R.id.man);
        woman = view.findViewById(R.id.woman);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> save());
        return view;
    }

    private void save() {
        String sex = "";
        if (man.isChecked()) {
            sex = "Hombre";
        } else {
            sex = "Mujer";
        }
        if (names.getText().toString().isEmpty() |
                lastnames.getText().toString().isEmpty() |
                phone.getText().toString().isEmpty() |
                symptoms.toString().isEmpty()) {
            Toast.makeText(getActivity(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();

        } else {
            Date date = new Date(names.getText().toString(), lastnames.getText().toString(), phone.getText().toString(), symptoms.getText().toString(), urgency.isChecked(), sex);
            try {

                File file = new File(requireContext().getFilesDir(), "dates.txt");
                FileWriter writer = new FileWriter(file, true);
                writer.append(date.getNames().concat(":").concat(date.getLastNames()).concat(":").concat(date.getPhone().concat(":").concat(date.getSymptoms().concat(":").concat(date.getSex()).concat("\n"))));
                writer.flush();
                writer.close();
                getData();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        names.setText("");
        lastnames.setText("");
        phone.setText("");
        symptoms.setText("");
    }

    public String[] getData() throws IOException {
        File fileEvents = new File(requireContext().getFilesDir(), "dates.txt");
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileEvents));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        br.close();
        System.out.println("[[".concat(text.toString()).concat("]]"));
        String data[] = text.toString().split("\n");
        for (String value : data) {
            System.out.println(value);
        }
        System.out.println(getActivity().getFilesDir());
        return data;
    }
}