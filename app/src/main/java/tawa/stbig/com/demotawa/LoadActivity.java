package tawa.stbig.com.demotawa;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import tawa.stbig.com.demotawa.helper.Files;
import tawa.stbig.com.demotawa.http.HttpConnectionWeb;
import tawa.stbig.com.demotawa.object.ImageObject;

/**
 * Created by root on 26/01/16.
 */
public class LoadActivity extends Activity {


    private ImageObject imgObj=null;
    private HttpConnectionWeb cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Bundle extra = getIntent().getExtras();

        if(extra!=null){
            imgObj = (ImageObject) extra.get("entity");
            loadData(imgObj.getImage());
        }

    }

    public String getEncoded64ImageStringFromBitmap(String path) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    private void loadData(final String img){
        new Thread(new Runnable() {
            @Override
            public void run() {

                cn = new HttpConnectionWeb();
               cn.downloadFile(img);


            }
        }).start();
    }

;

}
