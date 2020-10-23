package com.example.safariguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        Query query = mRef.child("Data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   // Model model = new Model();
                    //model.setImage(dataSnapshot.child("image").getValue().toString());
                    //model.setTitle(dataSnapshot.child("title").getValue().toString());
                   // model.setDescription(dataSnapshot.child("description").getValue().toString());
                    tvTitle.setText(dataSnapshot.child("title").getValue().toString());
                    tvDesc.setText(dataSnapshot.child("description").getValue().toString());

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}