package com.example.loginface;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class post_DatosUsu extends AsyncTask<String, Void, String> {



        //ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

       /*
        progressDialog.setMessage("Inserting data...");
        progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return postData(params[0]);
            } catch (IOException ex) {
                return "Error";
            } catch (JSONException ex) {
                return "Datos invalidos!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



      /*  if (progressDialog != null) {
            progressDialog.dismiss();
        }  */      }

        private String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            try {
                //Creando los datos para enviar al server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("nombre", "dres");
                dataToSend.put("apellido", "valgon");
                dataToSend.put("telefono", "2345677");
                dataToSend.put("usuario", "gonza");
                dataToSend.put("passsword", "1234");

                //inicializando la request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 );
                urlConnection.setConnectTimeout(10000 );
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                //Escribir los datos
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Leer la respuesta
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

            return result.toString();
        }
    }


