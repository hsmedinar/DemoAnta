package tawa.stbig.com.demotawa.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import tawa.stbig.com.demotawa.R;
import tawa.stbig.com.demotawa.datos.Datos;
import tawa.stbig.com.demotawa.helper.DBitem;
import tawa.stbig.com.demotawa.http.HttpConnectionWeb;


/**
 * Created by root on 13/01/16.
 */
public class ApiExtra {
    private Context context;
    private HttpConnectionWeb cn;
    private Datos data;
    JSONArray images;

    public ApiExtra(Context context){
        this.context=context;
        cn = new HttpConnectionWeb(context.getString(R.string.img_url));
        data = new Datos(context);
    }


    public boolean requestExtras(){

        try {

            String json = cn.connect();

            if(!json.isEmpty()){

                JSONObject jObj = new JSONObject(json);

                images = jObj.getJSONArray("response");

                if(registerExtra(images))
                    return true;

            }

            return false;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public boolean registerExtra(JSONArray extras){

        try{

            for(int x=0;x<extras.length() -1 ;x++){

                JSONObject node = extras.getJSONObject(x);

                data.registerImage(node.getInt(DBitem.IMG_ID),
                        node.getString(DBitem.IMG_TITLE),
                        node.getString(DBitem.IMG_DESCRIPTION),
                        node.getString(DBitem.IMG_IMAGE),
                        node.getString(DBitem.IMG_CREATE),
                        node.getString(DBitem.IMG_UPDATE));
            }

            return true;
        }catch(Exception e){
            Log.e("error media", e.getMessage());
            return false;
        }

    }




}
