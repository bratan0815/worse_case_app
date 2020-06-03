package com.example.prot_1.view.main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.prot_1.R;
import com.example.prot_1.view.messages.MessagesFragment;
import com.example.prot_1.view.network.NetworkFragment;
import com.example.prot_1.view.osm.OSMFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        myDatabase = this.openOrCreateDatabase("Message.db", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS messages (title VARCHAR, author VARCHAR, description VARCHAR, text VARCHAR, icon INT(1))");
        myDatabase.close();
        myDatabase = this.openOrCreateDatabase("Tracking.db", MODE_PRIVATE, null);
        myDatabase.close();

    }
    private void setupViewPager(ViewPager viewPager){
        SegmentStatePagerAdapter adapter = new SegmentStatePagerAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(new MessagesFragment(), "MessagesFragment");
        adapter.addFragment(new NetworkFragment(), "NetworkFragment");
        adapter.addFragment(new OSMFragment(), "OSMFragment");
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int FragmentNumber){
        mViewPager.setCurrentItem(FragmentNumber);
    }
}
