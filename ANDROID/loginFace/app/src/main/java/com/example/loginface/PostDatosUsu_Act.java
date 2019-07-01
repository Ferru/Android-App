package com.example.loginface;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class PostDatosUsu_Act extends AsyncTask<Void,Void,String>{
    //variables del hilo
    private Context httpContext;//contexto
    ProgressDialog progressDialog;


    public String resultadoapi="";
    public String linkrequestAPI="";//link  para consumir el servicio rest
    public String  nUsu ="";
    public String apUsu="";
    public String telU ="";
    public String Usu ="";
    public String passUsuU ="";



    //constructor del hilo (Asynctask)
    public PostDatosUsu_Act(Context ctx, String linkAPI, String  nUsuA, String apUsuA, String telUA, String UsuA,String passUsuUA ) {
        this.httpContext = ctx;
        this.linkrequestAPI = linkAPI;
        this.nUsu = nUsuA;
        this.apUsu = apUsuA;
        this.telU = telUA;
        this.Usu = UsuA;
        this.passUsuU = passUsuUA;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Procesando Solicitud", "por favor, espere");
    }



    @Override
    protected String doInBackground(Void... params) {
        String result= null;

        String wsURL = linkrequestAPI;
        URL url;
        try {
            // se crea la conexion al api:
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //crear el objeto json para enviar por POST
            JSONObject datos_Enviar = new JSONObject();
            datos_Enviar.put("nombre",nUsu);
            datos_Enviar.put("apellido",apUsu);
            datos_Enviar.put("telefono",telU);
            datos_Enviar.put("usuario",Usu);
            datos_Enviar.put("password",passUsuU);


            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTENER EL RESULTADO DEL REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(datos_Enviar));
            writer.flush();
            writer.close();
            os.close();


            int responseCode=urlConnection.getResponseCode();// conexion OK?
            if(responseCode== HttpURLConnection.HTTP_OK){
                BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer sb= new StringBuffer("");
                String linea= "";
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


   /* public String postDatos(String urlRuta) throws IOException, JSONException {

        JSONObject datos_Enviar =  new JSONObject();
        datos_Enviar.put("nombre:","DRES");
        datos_Enviar.put("apellido:","GONZALEZ");
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

*/

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoapi=s;

        Toast.makeText(httpContext,resultadoapi, Toast.LENGTH_LONG).show();//mostrara una notificacion con el resultado del request
    }
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
