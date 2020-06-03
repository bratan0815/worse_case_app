package com.example.prot_1.view.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prot_1.R;
import com.example.prot_1.control.MessageManager;
import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageHeader;

import java.sql.Timestamp;

public class ViewMessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";
    private ImageView imMainImageView;
    private TextView tvTitle, tvAuthor, tvDescription, tvText;
    private String dataTitle, dataAuthor, dataDescription, dataText;
    private Button btnMessageDelete;
    int myImage;
    private boolean deleting = true;
    MessageManager messageManager;

    //Baustelle
    public void onClickBtnMessageDelete(View view){
        Timestamp tmstmp = new Timestamp(System.currentTimeMillis());
        MessageHeader msgHeader = new MessageHeader(dataTitle, dataAuthor, tmstmp, tmstmp);
        MessageData msg = new MessageData(msgHeader, dataDescription, dataText, myImage);
        if(deleting){
            messageManager.getViewMessageList().removeAlikeElement(msg);
            messageManager.deleteFromDB(msg);
            //btnMessageDelete.setText("UNDO");
            deleting = false;
        }/*
        else if(!deleting) {
            MessageController.getViewMessageList().addElement(msg);
            MessageController.writeMessageToDB(msg);
            btnMessageDelete.setText("DELETE");
            deleting = true;
        }
        */
        //finish();
    }

    public void onClickBtnToSend(View view){
        Timestamp tmstmp = new Timestamp(System.currentTimeMillis());
        MessageHeader msgHeader = new MessageHeader(dataTitle, dataAuthor, tmstmp, tmstmp);
        MessageData msg = new MessageData(msgHeader, dataDescription, dataText, myImage);
        messageManager.getSendMessageList().addElement(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        imMainImageView = findViewById(R.id.ivActivityMessageIcon);
        tvTitle = findViewById(R.id.tvActivityMessageTitle);
        tvAuthor = findViewById(R.id.tvActivityMessageAuthor);
        tvDescription = findViewById(R.id.tvActivityMessageDescription);
        tvText = findViewById(R.id.tvActivityMessageText);
        btnMessageDelete = findViewById(R.id.btnActivityMessageDelete);
        messageManager = MessageManager.getInstance();
        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("dataImage") && getIntent().hasExtra("dataTitle") && getIntent().hasExtra("dataAuthor") && getIntent().hasExtra("dataDescription") && getIntent().hasExtra("dataText")){
            dataTitle = getIntent().getStringExtra("dataTitle");
            dataDescription = getIntent().getStringExtra("dataDescription");
            dataAuthor = getIntent().getStringExtra("dataAuthor");
            dataText = getIntent().getStringExtra("dataText");
            myImage = getIntent().getIntExtra("dataImage", 1);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        tvTitle.setText(dataTitle);
        tvAuthor.setText(dataAuthor);
        tvDescription.setText(dataDescription);
        imMainImageView.setImageResource(myImage);
        tvText.setText(dataText);
    }
}
