package com.example.safariguide;

import android.content.Context;
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

import com.example.safariguide.Sitespack.First;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Sites extends Fragment implements RecyclerAdapter.ListItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter ;
    private DatabaseReference mRef;
    private ArrayList<Model> modelArrayList;
    Context mContext;
    RecyclerAdapter.ListItemClickListener onClickListener;
    Model model = new Model();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_sites, container, false);

        onClickListener = this;

        recyclerView = view.findViewById(R.id.rvSites);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerAdapter = new RecyclerAdapter(mContext, modelArrayList,this);

        mRef = FirebaseDatabase.getInstance().getReference();
        modelArrayList = new ArrayList<>();

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
                recyclerAdapter = new RecyclerAdapter(mContext, modelArrayList, onClickListener);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sites");
    }

    @Override
    public void onListItemClick(View v, int position) {
        if (position == 0){
            Intent i = new Intent(getActivity(), First.class);
            i.putExtra("name",modelArrayList.get(position).getTitle() );
            i.putExtra("desc", modelArrayList.get(position).getDescription());
            startActivity(i);
        }
        else if (position==1){
            Intent i = new Intent(getActivity(),First.class);
            i.putExtra("name",modelArrayList.get(position).getTitle() );
            i.putExtra("desc", modelArrayList.get(position).getDescription());
            startActivity(i);
        }
    }


}
