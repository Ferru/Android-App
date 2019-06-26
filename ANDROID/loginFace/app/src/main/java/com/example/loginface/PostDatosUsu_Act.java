package com.example.loginface;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostDatosUsu_Act extends AsyncTask<String, Void, String> {


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return postDatos(params[0]);
        } catch (IOException ex) {

            return "Error de conexión";

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String postDatos(String urlRuta) throws IOException, JSONException {

        JSONObject datos_Enviar =  new JSONObject();
        datos_Enviar.put("nombre:","Ruben");
        datos_Enviar.put("apellido:","Valencia");
        datos_Enviar.put("telefono:","32145667");
        datos_Enviar.put("usuario:","Devandres");
        datos_Enviar.put("password:","dev123");

        URL url = new URL(urlRuta);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(1000);
        urlConnection.setConnectTimeout(1000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.connect();

        OutputStream outputStream =  urlConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream));
        bufferedWriter.write(datos_Enviar.toString());
        bufferedWriter.flush();

        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));







        return null;
    }


}
