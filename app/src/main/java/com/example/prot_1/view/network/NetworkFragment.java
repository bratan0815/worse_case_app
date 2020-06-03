package com.example.prot_1.view.network;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prot_1.R;
import com.example.prot_1.view.main.MainActivity;

public class NetworkFragment extends Fragment {

    private static final String TAG = "NetworkFragment";
    private Button btnNavFragMessage;
    private Button btnNavFragNetwork;
    private Button btnNavFragMap;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.network_fragment_layout, container, false);
        btnNavFragMessage = view.findViewById(R.id.btnNavMessage);
        btnNavFragNetwork = view.findViewById(R.id.btnNavNetwork);
        btnNavFragMap = view.findViewById(R.id.btnNavMap);


        btnNavFragMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment1", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnNavFragNetwork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnNavFragMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment3", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        return view;
    }
}
