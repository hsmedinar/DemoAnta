package tawa.stbig.com.demotawa.fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;
import tawa.stbig.com.demotawa.LoadActivity;
import tawa.stbig.com.demotawa.R;
import tawa.stbig.com.demotawa.adapters.AdapterExtra;
import tawa.stbig.com.demotawa.datos.Datos;
import tawa.stbig.com.demotawa.helper.imagen.CircleTransform;
import tawa.stbig.com.demotawa.listener.ItemClickSupport;
import tawa.stbig.com.demotawa.listener.ListenerImage;
import tawa.stbig.com.demotawa.object.ImageObject;

/**
 * Created by root on 21/01/16.
 */
public class FragmentImageList extends Fragment {

    private Activity activity;
    private Context context;
    private static View view;
    private RecyclerView recyclerView;
    private Datos data;
    private AdapterExtra adapter;
    private ImageView imgBig;
    private ListenerImage listenImage;

    private int imageSelected=0;
    private ImageObject imageZoom=null;


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
            listenImage = (ListenerImage) a;
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


        view = inflater.inflate(R.layout.fragment_lista, null, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_images);
        imgBig = (ImageView) view.findViewById(R.id.imageBig);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        data= new Datos(context);
        adapter = new AdapterExtra(context,data.listarImages());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SlideInRightAnimationAdapter(adapter));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager lm = ((LinearLayoutManager) recyclerView.getLayoutManager());
                imageSelected = lm.findFirstCompletelyVisibleItemPosition();

                if (imageSelected != -1) {
                    showImage(imageSelected);
                    imageZoom = data.listarImages().get(imageSelected);
                }


            }
        });

        imgBig.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {


                    if (imageZoom != null) {

                        Toast.makeText(context, "Double Click Event", Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", imageZoom);

                        Intent intent = new Intent(context, LoadActivity.class);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }


                    return super.onDoubleTap(e);
                }

            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                if (imageZoom != null) {
                    Toast.makeText(context, imageZoom.getTitle(), Toast.LENGTH_SHORT).show();
                    listenImage.selectedImage(imageZoom);
                }

            }
        });


        showImage(0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void showImage(int position){
        Picasso.with(context)
                .load(data.listarImages().get(position).getImage())
                .resize(270, 270)
                .centerCrop()
                .transform(new CircleTransform())
                .into(imgBig);
    }


}
