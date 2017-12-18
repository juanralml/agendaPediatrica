package com.example.usuario.app_agenda1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class NotificationService extends Service {
    MyTask myTask;
    @Override
    public void onCreate() {
        super.onCreate();
       // Toast.makeText(this, "Servicio creado!", Toast.LENGTH_SHORT).show();
        myTask = new MyTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myTask.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class MyTask extends AsyncTask<String, JSONArray, String> {
        private DateFormat dateFormat;
        private String date;
        private boolean cent;
        private JSONArray jsonOG;

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            cent = true;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params) {
            while (cent){
                try {
                    conect1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                date = dateFormat.format(new Date());
                try {

                    publishProgress(jsonOG);
                    // Stop 30s
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(JSONArray... values) {
            Log.d("entro aca","pre");
            NotificationCompat.Builder mBuilder;
            NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            int icono = R.mipmap.ic_launcher;
            Intent i=new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
        //    if(jsonOG !=null){
                mBuilder =new NotificationCompat.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(icono)
                        .setContentTitle("Alerta de Vacunas Pedientes")
                        .setContentText("Tiene uno o mas hijos con Vacunas Pedinetes en fechas sercanas.")
                       // .setVibrate(new long[] {100, 250, 100, 500})
                        .setAutoCancel(true);



                mNotifyMgr.notify(1, mBuilder.build());

           // }

        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
            cent = false;
        }


        protected void conect1() throws IOException, JSONException {
            //url del servidor restfull
            String result;
//            URL url = new URL("http://192.168.10.140:8080/agenda_pedriatrica/webresources/entidades.hijo/por_padre");
            URL url = new URL("http://192.168.0.15:8080/agenda_pedriatrica/webresources/entidades.hijo/notifiVac");
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection)url.openConnection();

                // Construir los datos a enviar
                con.setRequestProperty("Content-Type","application/json");
                //creacion del objeto json que se enviara
                JSONObject json2 = new JSONObject();
                json2.put("id",8);

                byte[] bytes=json2.toString().getBytes("UTF-8");


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
                if (status != HttpURLConnection.HTTP_OK) {

                    inputStream = con.getErrorStream();
                    Log.d("readStream(inputStream)", readStream(inputStream));
                    jsonOG = null;
                }
                else {

                    inputStream = con.getInputStream();
                    Log.d("readStream(inputStream)", readStream(inputStream));
                    JSONArray jsonArr = new JSONArray(readStream(inputStream));
                    jsonOG = jsonArr;
                    //se parsea lo a json array
                }
                //se contruye la lista de objetos Json

            } catch (IOException e) {
                e.printStackTrace();

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
