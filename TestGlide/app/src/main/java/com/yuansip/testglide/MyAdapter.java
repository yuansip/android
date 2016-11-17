package com.yuansip.testglide;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static java.security.AccessController.getContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] picUrls;
    private Context context;

    public MyAdapter(Context context, String[] picUrls) {
        this.picUrls = picUrls;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        android.util.Log.e("yuansip", "createholder i=" + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        android.util.Log.e("yuansip", "onBindViewHolder i=" + i);
        viewHolder.gifView.getImageView().layout(0, 0, 0, 0);
        viewHolder.gifView.load(picUrls[i]);
//        Glide.with(context)
//                .load(picUrls[i])
//                .placeholder(R.drawable.place_holder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        if (null == picUrls) {
            return 0;
        }
        android.util.Log.e("yuansip", "cnt=" + picUrls.length);
        return picUrls.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public GifView gifView;

        public MyViewHolder(View itemView) {
            super(itemView);
            gifView = (GifView) itemView.findViewById(R.id.gif);
        }
    }
}
