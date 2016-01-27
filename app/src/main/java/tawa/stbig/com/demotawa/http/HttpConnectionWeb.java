package tawa.stbig.com.demotawa.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import tawa.stbig.com.demotawa.helper.Files;
import tawa.stbig.com.demotawa.helper.imagen.Utils;

/**
 * Created by helbert on 16/05/15.
 */
public class HttpConnectionWeb {

    private HttpURLConnection conexion;

    private URL url;
    private String TAG_HTTP = "HttpDebug";
    private String link;
    private HashMap<String, String> params;

    public HttpConnectionWeb(){
    }

    public HttpConnectionWeb(String link){
        this.link=link;

        params = new HashMap<>();
    }


    public String connect() throws IOException {

        String linea;

        StringBuilder construye = new StringBuilder();

        try{
            this.url= new URL(link + getPostDataString(params));
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
           // conexion.setDoInput(true);
           // conexion.setDoOutput(true);

           // Log.i(TAG_HTTP, "parametros " + getPostDataString(params));

           // PrintWriter printer = new PrintWriter(conexion.getOutputStream());
           // printer.print(getPostDataString(params));
           // printer.close();

            InputStreamReader input = new InputStreamReader(conexion.getInputStream());
            BufferedReader buffer = new BufferedReader(input);

            Log.i(TAG_HTTP, String.valueOf(conexion.getResponseCode()));

            if(conexion.getResponseCode()==HttpURLConnection.HTTP_OK){

                while((linea=buffer.readLine()) !=null){
                    construye.append(linea);
                }

                return construye.toString();

            }

            return "";

        }catch(Exception e){
            Log.e(TAG_HTTP, e.getMessage());
            return "";
        }

    }

    public void AddParam(String name, String value) {
        params.put(name, value);
    }

    private String getPostDataString(HashMap<String, String> paramsPost) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : paramsPost.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public Boolean downloadFile(String url){

       try {

           String characters[] = url.split("/");
           String nameImagen = characters[characters.length-1];

           Files fileDir = new Files();
           fileDir.setNameFiles(nameImagen);


           File f = new File (fileDir.getPathService());
           if (f.exists ()) return true;

            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();

            return true;
        } catch (Throwable ex){
            ex.printStackTrace();
            return false;
        }
    }


}

