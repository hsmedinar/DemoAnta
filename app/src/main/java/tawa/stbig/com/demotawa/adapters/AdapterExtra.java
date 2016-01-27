package tawa.stbig.com.demotawa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tawa.stbig.com.demotawa.R;
import tawa.stbig.com.demotawa.helper.imagen.CircleTransform;
import tawa.stbig.com.demotawa.object.ImageObject;

/**
 * Created by root on 19/01/16.
 */
public class AdapterExtra  extends RecyclerView.Adapter<AdapterExtra.ViewHolder>  {

    private Context context;
    private ArrayList<ImageObject> extras;


    public AdapterExtra(Context context, ArrayList<ImageObject> extras){
        this.context=context;
        this.extras=extras;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return extras.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(context)
                .load(extras.get(position).getImage())
                .resize(180, 180)
                .centerCrop()
                .transform(new CircleTransform())
                .into(holder.image);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_image, null, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView image;

        public ViewHolder(View itemView){
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
