package com.example.pruebaapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmpleadoAdapter extends BaseAdapter {

    ArrayList<Empleado> lista;

    public EmpleadoAdapter(ArrayList<Empleado> lista) {
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Empleado getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empleado_list, parent, false);
        } else {
            view = convertView;
        }

        Empleado empleado = getItem(position);

        TextView txtNombre = view.findViewById(R.id.textViewNombre);
        TextView txtEmail = view.findViewById(R.id.textViewEmail);
        TextView txtTelefono = view.findViewById(R.id.textViewTelefono);
        TextView txtDomicilio = view.findViewById(R.id.textViewDomicilio);

        txtNombre.setText(empleado.getNombre().toString());
        txtDomicilio.setText(empleado.getDomicilio().toString());
        txtEmail.setText(empleado.getEmail().toString());
        txtTelefono.setText(empleado.getTelefono().toString());


        return view;
    }
}
