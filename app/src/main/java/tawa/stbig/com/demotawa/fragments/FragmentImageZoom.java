package tawa.stbig.com.demotawa.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import tawa.stbig.com.demotawa.R;
import tawa.stbig.com.demotawa.helper.imagen.CircleTransform;
import tawa.stbig.com.demotawa.listener.ListenerImage;
import tawa.stbig.com.demotawa.object.ImageObject;

/**
 * Created by root on 25/01/16.
 */
public class FragmentImageZoom extends Fragment {

    private Activity activity;
    private Context context;
    private static View view;
    private ImageView imgBig;
    private ListenerImage returnImage;
    private ImageObject imgObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=activity.getApplicationContext();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            returnImage = (ListenerImage) a;
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }


        view = inflater.inflate(R.layout.fragment_zoom, null, false);
        imgBig = (ImageView) view.findViewById(R.id.imageBig);

        loadData();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                returnImage.returnImage();
            }
        }, 3000);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void loadData() {

        imgObject= (ImageObject) getArguments().getSerializable("entity");

        if(imgObject!=null){
            showImage(imgObject.getImage());
        }
    }

    private void showImage(String img){
        Picasso.with(context)
                .load(img)
                .resize(270, 270)
                .centerCrop()
                .transform(new CircleTransform())
                .into(imgBig);
    }

}
