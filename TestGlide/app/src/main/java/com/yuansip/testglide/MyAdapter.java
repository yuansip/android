package com.yuansip.testglide;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] picUrls;
    private Activity context;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public MyAdapter(Activity context, String[] picUrls) {
        this.picUrls = picUrls;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            return new ItemViewHolder(view);
        }
        if (i == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false);
            return new HeadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int pos) {
        int type = getItemViewType(pos);
        if (type == VIEW_TYPE_ITEM) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.title.setOnClickListener(null);
            holder.gifView.getImageView().layout(0, 0, 0, 0);
            holder.gifView.load(picUrls[pos]);
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.util.Log.e("yuansip", "onclick pos=" + pos);
                    FragmentManager fm = context.getFragmentManager();
                    fm.executePendingTransactions();
                    fm.popBackStack("fragment_back", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    FragmentTransaction ft = fm.beginTransaction();
                    DetailFragment fragment = DetailFragment.newInstance(pos);
                    ft.add(R.id.main_fragment_container, fragment, "item");
                    ft.addToBackStack("fragment_back");
                    ft.commit();
                    fm.executePendingTransactions();
                }
            });
        } else {
            // HEADER
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (null == picUrls) {
            // return header
            return 0;
        }
        return picUrls.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public GifView gifView;
        public TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            gifView = (GifView) itemView.findViewById(R.id.gif);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View headView) {
            super(headView);
        }
    }
}
