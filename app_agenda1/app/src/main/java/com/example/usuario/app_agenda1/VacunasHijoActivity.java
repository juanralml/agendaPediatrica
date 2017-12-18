package com.example.usuario.app_agenda1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class VacunasHijoActivity extends AppCompatActivity {
    private ListView lv;
    private AdapterVacunasHijos adapter;
    private ArrayList<VacunasHijos> vacunasHijos = new ArrayList<VacunasHijos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas_hijo);
        vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 1ra Dosis","Si","2"));
        vacunasHijos.add(new VacunasHijos(1,"1","OPV/IPV 1ra Dosis","Si","2"));
        vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 2da Dosis","No","4"));
        vacunasHijos.add(new VacunasHijos(1,"1","aOPV/IPV 2da Dosis","No","4"));
//        vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 1ra Dosis","Si","2"));
        AdapterVacunasHijos adapter = new AdapterVacunasHijos(this, vacunasHijos);
        lv = (ListView) findViewById(R.id.lista_vacunas);
        lv.setAdapter(adapter);

    }
}
