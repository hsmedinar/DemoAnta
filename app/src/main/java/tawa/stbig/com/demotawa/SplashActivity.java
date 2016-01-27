package tawa.stbig.com.demotawa;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import tawa.stbig.com.demotawa.api.ApiExtra;

/**
 * Created by root on 21/01/16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        loadData();
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiExtra api = new ApiExtra(SplashActivity.this);
                boolean response = api.requestExtras();

                Message msg = new Message();
                msg.obj=response;
                responseWebNegociacion.sendMessage(msg);

            }
        }).start();
    }

    private Handler responseWebNegociacion = new Handler(){
        public void handleMessage(Message msg){

            if(Boolean.valueOf(msg.obj.toString())){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent,
                        ActivityOptions
                                .makeSceneTransitionAnimation(SplashActivity.this).toBundle());
            }
        };
    };


}
