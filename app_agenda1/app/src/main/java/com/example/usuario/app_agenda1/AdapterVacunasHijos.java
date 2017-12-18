package com.example.usuario.app_agenda1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by usuario on 17/11/2017.
 */

public class AdapterVacunasHijos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<VacunasHijos> items;

    public AdapterVacunasHijos(Activity activity, ArrayList<VacunasHijos> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_vacunas_hijos, null);
        }

        VacunasHijos dir = items.get(i);

        TextView nombreVacuna = (TextView) v.findViewById(R.id.nombreVacuna);
        nombreVacuna.setText(dir.getNombreVacuna());

        TextView esquemaIdeal = (TextView) v.findViewById(R.id.esquemaIdeal);
        esquemaIdeal.setText(dir.getEsquemaIdeal());

        TextView aplicado = (TextView) v.findViewById(R.id.aplicado);
        aplicado.setText(dir.getAplicado());

        TextView fecha = (TextView) v.findViewById(R.id.fecha);
        fecha.setText(dir.getFechaAplicacion());

        return v;
    }


}
