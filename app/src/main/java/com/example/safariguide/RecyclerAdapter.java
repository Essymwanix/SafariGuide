package com.example.safariguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Model> modelArrayList;
    final private ListItemClickListener mOnClickListener;

    public RecyclerAdapter(Context mContext, ArrayList<Model> modelArrayList, ListItemClickListener onClickListener) {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
        this.mOnClickListener = onClickListener;
    }



    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(modelArrayList.get(position).getTitle());
        holder.desc.setText(modelArrayList.get(position).getDescription());
        Picasso.get().load(modelArrayList.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title;
        TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.textView);
            desc = itemView.findViewById(R.id.textViewDesc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           // int position = getAdapterPosition();
            mOnClickListener.onListItemClick(view,getAdapterPosition());
        }
    }
    interface ListItemClickListener{
        void onListItemClick(View v, int position);
    }
}
