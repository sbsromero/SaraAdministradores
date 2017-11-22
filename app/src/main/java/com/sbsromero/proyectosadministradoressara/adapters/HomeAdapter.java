package com.sbsromero.proyectosadministradoressara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbsromero.proyectosadministradoressara.R;

import java.util.List;

/**
 * Created by USERPC on 30/10/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<String> options;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    public HomeAdapter(List<String> options,int layout, OnItemClickListener itemClickListener) {
        this.options = options;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(options.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewOption;
        public TextView textViewNameOption;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameOption = (TextView) itemView.findViewById(R.id.textViewNameOption);
            imageViewOption = (ImageView) itemView.findViewById(R.id.imageViewOption);
        }

        public void bind(final String option, final OnItemClickListener listener) {
            imageViewOption.setImageResource(R.mipmap.ic_launcher);
            textViewNameOption.setText(option);
            /*Picasso.with(context).load(movie.getImg()).fit().into(imageViewPoster);
            imageViewPoster.setImageResource(movie.getImg());*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(option, getAdapterPosition());
                }
            });
        }
    }

    //interfaz que tenemos que crear (propio)
    public interface OnItemClickListener {
        void onItemClick(String option, int position);
    }
}

