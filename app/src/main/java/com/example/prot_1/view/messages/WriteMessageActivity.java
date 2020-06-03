package com.example.prot_1.view.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prot_1.R;
import com.example.prot_1.control.MessageManager;
import com.example.prot_1.model.data.MessageData;
import com.example.prot_1.model.data.MessageHeader;

import java.sql.Timestamp;

public class WriteMessageActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etDescription, etText;
    CheckBox cbMessage, cbNews, cbGuide;
    Button btnSave;
    //0 == message, 1 == news; 2 == guide;
    private int writeTopic = 0;
    MessageManager messageManager;


    public void onClickBtnSave(View view){
        Timestamp tStamp = new Timestamp(System.currentTimeMillis());
        MessageHeader mshHeader = new MessageHeader(etTitle.getText().toString(), etAuthor.getText().toString(), tStamp, tStamp);
        MessageData msg = new MessageData(mshHeader, etDescription.getText().toString(), etText.getText().toString(), writeTopic);
        messageManager.getViewMessageList().addElement(msg);
        messageManager.writeMessageToDB(msg);
        finish();
    }

    public void onClickBtnMessage(View view){
        writeTopic = 0;
        resetCheckboxes();
    }
    public void onClickBtnNews(View view){
        writeTopic = 1;
        resetCheckboxes();
    }
    public void onClickBtnGuide(View view){
        writeTopic = 2;
        resetCheckboxes();
    }

    private void resetCheckboxes(){
        if(writeTopic != 0){
            cbMessage.setChecked(false);
        }
        if(writeTopic != 1){
            cbNews.setChecked(false);
        }
        if(writeTopic != 2){
            cbGuide.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        etTitle = findViewById(R.id.etActivityWriteTitle);
        etAuthor = findViewById(R.id.etActivityWriteAuthor);
        etDescription = findViewById(R.id.etActivityWriteDescription);
        etText = findViewById(R.id.etActivityWriteText);
        btnSave = findViewById(R.id.btnActivityWriteSave);
        cbMessage = findViewById(R.id.cbMessage);
        cbNews = findViewById(R.id.cbNews);
        cbGuide = findViewById(R.id.cbGuide);
        messageManager = MessageManager.getInstance();
    }
}
