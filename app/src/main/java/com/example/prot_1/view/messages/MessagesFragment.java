package com.example.prot_1.view.messages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prot_1.R;
import com.example.prot_1.control.MessageManager;
import com.example.prot_1.model.data.MessageDataList;
import com.example.prot_1.view.main.MainActivity;

public class MessagesFragment extends Fragment {

    private static final String TAG = "MessagesFragment";
    private Button btnNavFragMessage;
    private Button btnNavFragNetwork;
    private Button btnNavFragMap;
    private Button btnNavActWriteMsg;
    private RecyclerView recyclerViewList;

    int[] images = {R.drawable.message, R.drawable.news, R.drawable.tools};
    MessageDataList currentDataList;
    RecyclerVAdapter recyclerVAdapter;
    MessageManager msgController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.messages_fragment_layout, container, false);
        btnNavFragMessage = view.findViewById(R.id.btnNavMessage);
        btnNavFragNetwork = view.findViewById(R.id.btnNavNetwork);
        btnNavFragMap = view.findViewById(R.id.btnNavMap);
        btnNavActWriteMsg = view.findViewById(R.id.btnNavActivityWrite);
        recyclerViewList = view.findViewById(R.id.rvList);
        msgController = MessageManager.getInstance();


        btnNavFragMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment1", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
                Log.i(TAG, "onClick: getdata an notify adapter");
                getData();
                recyclerVAdapter.notifyDataSetChanged();
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

        btnNavActWriteMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteMessageActivity.class);
                getActivity().startActivity(intent);
            }
        });

        getData();
        Log.i(TAG, "MessageFragment: first getData Success!");
        recyclerVAdapter = new RecyclerVAdapter((MainActivity)getActivity(), currentDataList, images);
        Log.i(TAG, "Adapter initialised");

        recyclerViewList.setAdapter(recyclerVAdapter);
        Log.i(TAG, "recyclerview connected with adapter");

        recyclerViewList.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
        Log.i(TAG, "new layout manager");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }



    private void getData(){
        currentDataList = msgController.getData();

    }
}
