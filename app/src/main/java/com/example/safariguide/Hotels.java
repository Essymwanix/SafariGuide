package com.example.safariguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Belal on 18/09/16.
 */


public class Hotels extends Fragment implements RecyclerAdapter.ListItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private DatabaseReference mRef;
    private ArrayList<Model> modelArrayList;
    RecyclerAdapter.ListItemClickListener onClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        recyclerView = view.findViewById(R.id.rvHotels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mRef = FirebaseDatabase.getInstance().getReference();
        modelArrayList = new ArrayList<>();

        //clear();

        GetDataFromFirebase();
        return view;
    }
    private void GetDataFromFirebase()
    {
        Query query = mRef.child("Data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model model = new Model();
                    model.setImage(dataSnapshot.child("image").getValue().toString());
                    model.setTitle(dataSnapshot.child("title").getValue().toString());
                    model.setDescription(dataSnapshot.child("description").getValue().toString());

                    modelArrayList.add(model);

                }
                recyclerAdapter = new RecyclerAdapter(getContext(), modelArrayList, onClickListener);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



            @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Hotels");
    }

    @Override
    public void onListItemClick(View v, int position) {
        if (position == 0){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lakenakurulodge.com"));
            startActivity(i);
        }
        else if (position==1){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mountelgonhotel.com"));
            startActivity(i);
        }
    }
}
