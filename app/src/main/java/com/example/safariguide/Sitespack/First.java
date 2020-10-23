package com.example.safariguide.Sitespack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.safariguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class First extends AppCompatActivity {
    TextView tvTitle, tvDesc;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        tvTitle = findViewById(R.id.textView2);
        tvDesc = findViewById(R.id.textView3);
        mRef = FirebaseDatabase.getInstance().getReference();

       Bundle extras = getIntent().getExtras();
       if (extras!=null){
           String title = extras.getString("name");
           String desc = extras.getString("desc");
           tvTitle.setText(title);
           tvDesc.setText(desc);
       }

    }
}