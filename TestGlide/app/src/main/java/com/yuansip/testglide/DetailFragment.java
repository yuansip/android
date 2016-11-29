package com.yuansip.testglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DetailFragment extends Fragment implements View.OnClickListener {

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ImageSource.gifUrls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getActivity()).inflate(
                    R.layout.pager_item, null);
            GifView gif= (GifView) view.findViewById(R.id.gif);
            gif.load(ImageSource.gifUrls[position]);
            container.addView(view);
            return view;
        }
    }

    private static final String ARG_URL_INDEX = "urlIndex";

    private int mUrlIndex;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int urlIndex) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_URL_INDEX, urlIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrlIndex = getArguments().getInt(ARG_URL_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        root.findViewById(R.id.fragment_back_button).setOnClickListener(this);
        ViewPager vp = (ViewPager) root.findViewById(R.id.pager);
        vp.setAdapter(new ViewPagerAdapter());
        vp.setCurrentItem(mUrlIndex);
        vp.setOffscreenPageLimit(3);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fragment_back_button) {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
