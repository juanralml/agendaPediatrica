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
 * Created by usuario on 06/11/2017.
 */

public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Hijos> items;

    public AdapterItem(Activity activity, ArrayList<Hijos> items) {
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
            v = inf.inflate(R.layout.item_hijos, null);
        }

        Hijos dir = items.get(i);

        TextView nombre = (TextView) v.findViewById(R.id.nombreHijo);
        nombre.setText(dir.getNombre());

        TextView edad = (TextView) v.findViewById(R.id.edadHijo);
        edad.setText(dir.getEdad());

        TextView sexo = (TextView) v.findViewById(R.id.sexoHijo);
        sexo.setText(dir.getSexo());

        return v;
    }
}
