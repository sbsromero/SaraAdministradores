package com.sbsromero.proyectosadministradoressara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import java.util.List;

/**
 * Created by USERPC on 13/11/2017.
 */

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.ViewHolder> {

    private Context context;
    private List<Monitor> list;
    private int layout;
    private OnItemClickListener itemClickListener;


    public MonitorAdapter(Context context, List<Monitor> list, int layout, OnItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewMonitor;
        TextView textViewLineaAseoria;
        TextView textViewNombre;
        TextView textViewSemestre;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewMonitor = (ImageView) itemView.findViewById(R.id.imageViewMonitor);
            textViewLineaAseoria = (TextView) itemView.findViewById(R.id.textViewLineaAsesoria);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombreMonitor);
            textViewSemestre = (TextView) itemView.findViewById(R.id.textViewSemestre);
        }

        public void bind(final Monitor monitor, final OnItemClickListener itemClickListener) {

            textViewLineaAseoria.setText(monitor.getLineaMonitoria());
            textViewNombre.setText(monitor.getNombre());
            textViewSemestre.setText(monitor.getSemestre());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(monitor.getId());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }
}
