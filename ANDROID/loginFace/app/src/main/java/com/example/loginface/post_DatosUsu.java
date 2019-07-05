package com.example.loginface;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class post_DatosUsu extends AsyncTask<Void, Void, String> {

    private Context httpContext;//contexto
    ProgressDialog progressDialog;//dialogo cargando
    public String resultadoapi = "";
    public String linkrequestAPI = "";//link  para consumir el servicio


    public post_DatosUsu(Context ctx, String linkAPI){
        this.httpContext = ctx;
        this.linkrequestAPI = linkAPI;
    }


    //ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(httpContext, "Procesando Solicitud", "por favor, espere");


        //
//progressDialog.setMessage("...");
//progressDialog.show();
    }
        @Override
        protected String doInBackground(Void... params) {
            String result= null;

            String wsURL = linkrequestAPI;
            URL url = null;
            try {
                // se crea la conexion al api
                url = new URL(wsURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //crear el objeto json para enviar por POST
                JSONObject dataTosend = new JSONObject();
               dataTosend.put("nombre", "dres");
               dataTosend.put("apellido", "valgon");
               dataTosend.put("telefono", "2345677");
                dataTosend.put("usuario", "gonza");
                dataTosend.put("passsword", "1234");


                //DEFINIR PARAMETROS DE CONEXION
                urlConnection.setReadTimeout(15000 );
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);


                //OBTENER EL RESULTADO DEL REQUEST
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(dataTosend));
                writer.flush();
                writer.close();
                os.close();

                int responseCode=urlConnection.getResponseCode();// conexion OK?
                if(responseCode== HttpURLConnection.HTTP_OK){
                    BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    StringBuffer sb= new StringBuffer("");
                    String linea="";
                    while ((linea=in.readLine())!= null){
                        sb.append(linea);
                        break;

                    }
                    in.close();
                    result= sb.toString();
                }
                else{
                    result= new String("Error: "+ responseCode);


                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return  result;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            resultadoapi=result;
            Toast.makeText(httpContext,resultadoapi,Toast.LENGTH_LONG).show();//mostrara una notificacion con el resultado del request

      /*  if (progressDialog != null) {
            progressDialog.dismiss();
        }  */
    }
/*
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
        }*/


    //Transformar JSON Obejct a String
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

}





