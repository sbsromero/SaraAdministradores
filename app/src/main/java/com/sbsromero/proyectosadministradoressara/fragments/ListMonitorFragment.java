package com.sbsromero.proyectosadministradoressara.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.activities.AgregarMonitorActivity;
import com.sbsromero.proyectosadministradoressara.adapters.MonitorAdapter;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMonitorFragment extends Fragment  {

    private Realm realm;
    public List<Monitor> monitores;
    public RecyclerView recyclerListViewMonitor;
    public RecyclerView.LayoutManager layoutManager;
    public MonitorAdapter monitorAdapter;
    public MonitorListener monitorListener;
    public FloatingActionButton btnAgregarMentor;

    public ListMonitorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            monitorListener = (MonitorListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " should implement MonitorListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        monitorListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        monitorAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_monitor, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerFilter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.lineasAsesoria_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        realm = Realm.getDefaultInstance();
        monitores = realm.where(Monitor.class).findAll();

        recyclerListViewMonitor = view.findViewById(R.id.recyclerListViewMonitor);
        btnAgregarMentor = view.findViewById(R.id.floatAgregarMonitor);
        layoutManager = new LinearLayoutManager(getActivity());

        monitorAdapter = new MonitorAdapter(getContext(), monitores, R.layout.recycler_view_item, new MonitorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                enviarMonitorId(id);
            }
        });
        recyclerListViewMonitor.setAdapter(monitorAdapter);
        recyclerListViewMonitor.setLayoutManager(layoutManager);
        recyclerListViewMonitor.addItemDecoration(new DividerItemDecoration(
                getActivity(),
                ((LinearLayoutManager) layoutManager).getOrientation())
        );

        btnAgregarMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AgregarMonitorActivity.class);
                startActivity(intent);
                // Toast.makeText(view.getContext(),"Presiono",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void enviarMonitorId(int id){
        monitorListener.enviarDatosMonitor(id);

    }
    public interface MonitorListener{
        void enviarDatosMonitor(int id);
    }

}
