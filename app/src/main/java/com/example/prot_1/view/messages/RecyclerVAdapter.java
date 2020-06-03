package com.example.prot_1.view.messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prot_1.R;
import com.example.prot_1.model.data.MessageDataList;

public class RecyclerVAdapter extends RecyclerView.Adapter<RecyclerVAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    int[] dataImagesArray;
    MessageDataList messageDataList;
    Context context;


    public RecyclerVAdapter(Context ct, MessageDataList msgDataList, int[] imageArray){
        context = ct;
        dataImagesArray = imageArray;
        messageDataList = msgDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvCardViewTitle.setText(messageDataList.getElementAt(position).getHeader().getTitle());
        holder.tvCardViewDescription.setText(messageDataList.getElementAt(position).getDescription());
        holder.ivCardViewLogo.setImageResource(dataImagesArray[messageDataList.getElementAt(position).getIconNumber()]);


        holder.clMainLayoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewMessageActivity.class);
                intent.putExtra("dataTitle", messageDataList.getElementAt(position).getHeader().getTitle());
                intent.putExtra("dataAuthor", messageDataList.getElementAt(position).getHeader().getAuthor());
                intent.putExtra("dataDescription", messageDataList.getElementAt(position).getDescription());
                intent.putExtra("dataText", messageDataList.getElementAt(position).getText());
                intent.putExtra("dataImage", dataImagesArray[messageDataList.getElementAt(position).getIconNumber()]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Log.i(TAG, "getItemCount: call");
        return messageDataList.getMessageListCount();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView tvCardViewTitle, tvCardViewDescription;
        ImageView ivCardViewLogo;
        ConstraintLayout clMainLayoutCardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardViewTitle = itemView.findViewById(R.id.tvTxt);
            tvCardViewDescription = itemView.findViewById(R.id.tvDescription);
            ivCardViewLogo = itemView.findViewById(R.id.ivLogo);
            clMainLayoutCardView = itemView.findViewById(R.id.mainLayoutCardView);
        }
    }
}
