package com.example.usuario.app_agenda1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;


public class SonsActivity extends AppCompatActivity {
    private TextView jsonReturn;
    private ListView lv;
    private AdapterItem adapter;
    private ArrayList<Hijos> hijosG;
    private List hijosL;
    JsonReader reader;
    private Activity act;
    class ConecteToHttp extends AsyncTask<Void,Void,ArrayList<Hijos>>
    {


        @Override
        protected ArrayList doInBackground(Void... voids) {
            ArrayList<Hijos> hijos = new ArrayList<Hijos>();
            try {
                hijos = conect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("log bakc",hijos.get(0).getNombre());
            return hijos;

        }

        @Override
        protected void onPostExecute(ArrayList<Hijos> hijos) {
            Log.d("entro en pos","entro");
            Log.d("log bakc",hijos.get(0).getNombre());
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(hijos!=null) {
                AdapterItem adapter = new AdapterItem(act, hijos);

                lv.setAdapter(adapter);
            }else{

            }
        }

        protected void onPreExecute() {
            //display progress dialog.

        }
        protected ArrayList conect() throws IOException, JSONException {
//            URL url = new URL("http://192.168.10.140:8080/agenda_pedriatrica/webresources/entidades.hijo/por_padre");
            URL url = new URL("http://192.168.0.25:8080/agenda_pedriatrica/webresources/entidades.hijo/por_padre");
             HttpURLConnection con = null;
            try {
                con = (HttpURLConnection)url.openConnection();

                // Construir los datos a enviar
                con.setRequestProperty("Content-Type","application/json");
                //creacion del objeto json que se enviara
                JSONObject json2 = new JSONObject();
                json2.put("id",8);
                JSONObject cred = new JSONObject();
                cred.put("padreId",json2);
                byte[] bytes=cred.toString().getBytes("UTF-8");


                // Activar método POST
                con.setDoOutput(true);

                // Tamaño previamente conocido
                con.setFixedLengthStreamingMode(bytes.length);
                //
                OutputStream os = con.getOutputStream();
                os.write(bytes);
                os.close();

                //leer datos de respuesta
                //declaracion
                InputStream inputStream;
                //verificacion de respuesta ok
                int status = con.getResponseCode();
                if (status != HttpURLConnection.HTTP_OK)
                    inputStream = con.getErrorStream();
                else
                    inputStream = con.getInputStream();
                //se parsea lo a json array
                JSONArray jsonArr = new JSONArray(readStream(inputStream));

                //se contruye la lista de objetos Json
                hijosG=new ArrayList<Hijos>();
                for(int i=0;i < jsonArr.length();i++){
                    Log.d("valor de i", String.valueOf(i));
                    JSONObject jsonTmp = jsonArr.getJSONObject(i);
                    Hijos temp = new Hijos();
                    temp.setNombre(jsonTmp.getString("nombres"));
                    temp.setEdad(jsonTmp.getString("nombres"));
                    temp.setSexo(jsonTmp.getString("sexo"));
                    temp.setHijoId(jsonTmp.getString("ci"));
                    hijosG.add(temp);
                };



//


                return hijosG;


            } catch (IOException e) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sons);

        new ConecteToHttp().execute();
        act = this;
        lv = (ListView) findViewById(R.id.lista_hijos);



    }
}
