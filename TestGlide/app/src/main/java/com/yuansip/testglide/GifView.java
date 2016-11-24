package com.yuansip.testglide;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.transcode.BitmapToGlideDrawableTranscoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

public class GifView extends FrameLayout {

    private ImageView mDimmer;
    private ImageView mImage;
    private Drawable mThumbnail;

    public GifView(Context context) {
        super(context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GifView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ImageView getImageView() {
        return mImage;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.gifview, this);
        mDimmer = (ImageView) findViewById(R.id.dimmer);
        mDimmer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
    }

    public void stop() {
        Drawable drawable = mImage.getDrawable();
        if (drawable == null || !(drawable instanceof GifDrawable)) {
            return;
        }
        GifDrawable gifDrawable = (GifDrawable) drawable;
        gifDrawable.stop();
        showPlayDimmer();
    }

    public void play() {
        Drawable drawable = mImage.getDrawable();
        if (drawable == null || !(drawable instanceof GifDrawable)) {
            return;
        }
        hideDimmer();
        GifDrawable gifDrawable = (GifDrawable) drawable;
        gifDrawable.start();
    }

    private void showPlayDimmer() {
        mDimmer.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
        mDimmer.setVisibility(View.VISIBLE);
    }

    private void showLoadingDimmer() {
        mDimmer.setImageDrawable(getResources().getDrawable(R.drawable.v5_progress_spinner));
        mDimmer.setVisibility(View.VISIBLE);
    }

    private void hideDimmer() {
        mDimmer.setVisibility(View.GONE);
    }

    public void load(final String url) {
        showLoadingDimmer();
        final Uri uri = Uri.parse(url);
        Glide.with(getContext())
                .load(uri)
                .listener(new RequestListener<Uri, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        GlideDrawableImageViewTarget glideTarget = (GlideDrawableImageViewTarget) target;
//                        ImageView iv = glideTarget.getView();
//                        int width = iv.getMeasuredWidth();
//                        int targetHeight = width * resource.getIntrinsicHeight() / resource.getIntrinsicWidth();
//                        if(iv.getLayoutParams().height != targetHeight) {
//                            iv.getLayoutParams().height = targetHeight;
//                            iv.requestLayout();
//                        }
                        glideTarget.setDrawable(resource);
                        GifView gifView = (GifView) glideTarget.getView().getParent();
                        gifView.stop();
                        return true;
//                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImage);
//        final BitmapRequestBuilder<Uri, GlideDrawable> thumbRequest = Glide
//                .with(getContext())
//                .load(uri)
//                .asBitmap()
//                .transcode(new BitmapToGlideDrawableTranscoder(getContext()), GlideDrawable.class)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .listener(new RequestListener<Uri, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        hideDimmer();
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        mThumbnail = resource;
//                        showPlayDimmer();
//                        return false;
//                    }
//                });

//        thumbRequest.into(mImage);
//        mDimmer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Drawable drawable = mImage.getDrawable();
//                if (drawable == null || !(drawable instanceof GifDrawable)) {
//                    Glide.with(getContext())
//                            .load(uri)
//                            .listener(new RequestListener<Uri, GlideDrawable>() {
//                                @Override
//                                public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                    return false;
//                                }
//                            })
//                            .thumbnail(thumbRequest)
//                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            .dontAnimate()
//                            .into(mImage);
//                } else {
//                    GifDrawable gifDrawable = (GifDrawable) drawable;
//                    gifDrawable.start();
//                }
//                hideDimmer();
//            }
//        });
    }
}
