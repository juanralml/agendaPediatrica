package com.example.usuario.app_agenda1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private SignInButton logingoogle;
    private static final int RC_SIGN_IN = 9001;
    GoogleApiClient mGoogleApiClient = null;
    private String TAG = "mirar";
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private String email = null;
    private TextView textValidation;
//    private Button singOutbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logingoogle = (SignInButton) findViewById(R.id.logingoogle);
        textValidation = (TextView) findViewById(R.id.textValidation);
//        singOutbutton = (Button) findViewById(R.id.signOut);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        logingoogle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signIn();
            }
        });

//        singOutbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logout();
//            }
//        });

    }


    class ConecteToHttp extends AsyncTask<Void,Void,Usuario>
    {


        @Override
        protected Usuario doInBackground(Void... voids) {
            Usuario user = new Usuario();
            try {
                user = conect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;

        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if(usuario == null){
                Log.d("error","usuario no hay");
                return;
            }else{
                if(usuario.getEmail()!=null){
                    Log.d("Post exdcute:",usuario.toString());
                    Intent SonActivity = new Intent(getApplicationContext(),SonsActivity.class);
                    SonActivity.putExtra("idPadre",usuario.getId());
                    Log.d(TAG,"usuario loguegado");
                    startActivity(SonActivity);

                }else{
                    Log.d(TAG,"Usuario No Logueado");
                    logout();
                    textValidation.setVisibility(View.VISIBLE);
                }

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
        protected Usuario conect() throws IOException, JSONException {
            Log.d("Inicio conect:","ok");
            Usuario tmpUser = new Usuario();
            //url del servidor restfull
            //URL url = new URL("http://192.168.10.131:8080/agenda_pedriatrica/webresources/entidades.usuarios/validate");
            URL url = new URL("http://172.20.10.4:8080/agenda_pedriatrica/webresources/entidades.usuarios/validate");
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
                Log.d(TAG,email);
                cred.put("email",email);

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

                //se contruye la lista de objetos Json
               if(jsonArr.length() != 0){
                    JSONObject jsonTmp = jsonArr.getJSONObject(0);
                    tmpUser.setCi(jsonTmp.getString("ci"));
                    tmpUser.setId(jsonTmp.getInt("id"));
                    tmpUser.setEmail(jsonTmp.getString("email"));
                    tmpUser.setLastName(jsonTmp.getString("lastName"));
                    tmpUser.setName(jsonTmp.getString("name"));
                    tmpUser.setUserName(jsonTmp.getString("userName"));
                }
                Log.d("Respuesta",tmpUser.toString());

                return tmpUser;
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
    //LOGUEO POR GOOGLE
    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Mirar:", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d(TAG,acct.getEmail());
            //mStatusTextView.setText("Ingreso coomo:"+acct.getDisplayName());
            // updateUI(true);
            email=acct.getEmail();
            new ConecteToHttp().execute();
            Log.d("Mirar:", "succes");
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
            Log.d("Mirar:", "false");
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // [END signIn]


    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("mirar", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }*/
    }

    private void showProgressDialog() {
        /*if (mProgressDialog == null) {
            Log.d(TAG,"cargando dialog");
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Cargando");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();*/
    }

    private void hideProgressDialog() {
        /*if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }**/
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
//            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
           // mStatusTextView.setText("Saliendo");

//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    public void logout() {
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {

//                FirebaseAuth.getInstance().signOut();
                if(mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d(TAG, "User Logged out");
                                //Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                              //  startActivity(intent);
                               // finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.d(TAG, "Google API Client Connection Suspended");
            }
        });
    }

}
