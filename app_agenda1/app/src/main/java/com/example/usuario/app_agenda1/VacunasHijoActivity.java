package com.example.usuario.app_agenda1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VacunasHijoActivity extends AppCompatActivity {
    private ListView lv;
   // private AdapterVacunasHijos adapter;
    //private ArrayList<VacunasHijos> vacunasHijos = new ArrayList<VacunasHijos>();
    private String hijoCi;
    private Activity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas_hijo);

        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            String hijoCiTpm= (String) extras.get("hijoCi");//Obtengo el nombre
            hijoCi= hijoCiTpm;//Obtengo el nombre
        }

       /* vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 1ra Dosis","Si","2"));
        vacunasHijos.add(new VacunasHijos(1,"1","OPV/IPV 1ra Dosis","Si","2"));
        vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 2da Dosis","No","4"));
        vacunasHijos.add(new VacunasHijos(1,"1","aOPV/IPV 2da Dosis","No","4"));*/
//        vacunasHijos.add(new VacunasHijos(1,"1","anti Rotavirus 1ra Dosis","Si","2"));

        lv = (ListView) findViewById(R.id.lista_vacunas);
       // lv.setAdapter(adapter);
        act=this;
        new ConecteToHttp().execute();
    }


    class ConecteToHttp extends AsyncTask<Void,Void,ArrayList<VacunasHijos>>
    {


        @Override
        protected ArrayList<VacunasHijos> doInBackground(Void... voids) {
            ArrayList<VacunasHijos>  vacunasHijos = new ArrayList<VacunasHijos>();
            try {
                vacunasHijos = conect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return vacunasHijos;

        }

        @Override
        protected void onPostExecute(ArrayList<VacunasHijos> vacunasHijos2) {
            Log.d("vacu",vacunasHijos2.toString());
            if(vacunasHijos2!=null) {
                Log.d("paso","no");
                AdapterVacunasHijos adapter = new AdapterVacunasHijos(act, vacunasHijos2);
                Log.d("paso","si");
                lv.setAdapter(adapter);
            }else{

            }

        }

//        @Override
//        protected void onPostExecute() {
//            Log.d("entro en pos","entro");
//            Log.d("log bakc",hijos.get(0).getNombre());
//            /*
//            Asignar los objetos de Json parseados al adaptador
//             */
//            if(hijos!=null) {
//                AdapterItem adapter = new AdapterItem(act, hijos);
//
//                lv.setAdapter(adapter);
//            }else{
//
//            }


        protected void onPreExecute() {
            //display progress dialog.

        }
        protected ArrayList conect() throws IOException, JSONException {
            Log.d("Inicio conect:","ok");

            //url del servidor restfull
            //URL url = new URL("http://localhost:8080/agenda_pedriatrica/webresources/entidades.hijovacuna/listHijoVacuna");
            URL url = new URL("http://172.20.10.4:8080/agenda_pedriatrica/webresources/entidades.hijovacuna/listHijoVacuna");
            //   URL url = new URL("http://192.168.0.15:8080/agenda_pedriatrica/webresources/entidades.usuario/validate");
            HttpURLConnection con = null;
            try {
                Log.d("avance","1");
                con = (HttpURLConnection)url.openConnection();

                // Construir los datos a enviar
                con.setRequestProperty("Content-Type","application/json");
                //creacion del objeto json que se enviara

                JSONObject cred = new JSONObject();
                //cred.put("email",userName.getText());
                cred.put("hijoCi",hijoCi);

                byte[] bytes=cred.toString().getBytes("UTF-8");

                // Activar método POST
                con.setDoOutput(true);

                // Tamaño previamente conocido
                con.setFixedLengthStreamingMode(bytes.length);
                //
                OutputStream os = con.getOutputStream();
                os.write(bytes);
                os.close();
                Log.d("avance","2");
                //leer datos de respuesta
                //declaracion
                InputStream inputStream;
                //verificacion de respuesta ok
                int status = con.getResponseCode();
                if (status != HttpURLConnection.HTTP_OK)
                    inputStream = con.getErrorStream();
                else
                    inputStream = con.getInputStream();
                //Log.d("respuesta",readStream(inputStream));
                //se parsea lo a json array
                JSONArray jsonArr = new JSONArray(readStream(inputStream));

                ArrayList<VacunasHijos>  vacunas=new ArrayList<VacunasHijos>();
                for(int i=0;i < jsonArr.length();i++){
                    Log.d("valor de i", String.valueOf(i));
                    JSONObject jsonTmp = jsonArr.getJSONObject(i);
                    VacunasHijos temp = new VacunasHijos();
                    temp.setAplicado(jsonTmp.getString("aplicado"));
                    temp.setCiHijo(jsonTmp.getString("ciHijo"));
                    temp.setEsquemaIdeal(jsonTmp.getString("esquemaIdeal"));
                    temp.setFechaAplicacion(jsonTmp.getString("fechaAplicacion"));
                    temp.setIdVacuna(jsonTmp.getInt("idVacuna"));
                    temp.setNombreVacuna(jsonTmp.getString("nombreVacuna"));
                    vacunas.add(temp);
                };

                return vacunas;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } finally {
                con.disconnect();
            }
        }
    }
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }




}
