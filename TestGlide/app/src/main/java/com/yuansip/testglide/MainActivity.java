package com.yuansip.testglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static android.R.attr.action;
import static android.R.attr.startY;
import static android.R.attr.y;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private View mHeader;
    private boolean mHeadAnimOut;
    private boolean mHeadAnimIn;
    private Animation mHeadIn;
    private Animation mHeadOut;
//    private final String[] gifUrls = {
//            "http://img4q.duitang.com/uploads/blog/201501/17/20150117192149_Jj5we.gif",
//            "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif",
//            "http://attach.scimg.cn/month_1303/19/235b13b13b25f9a80db73f4d13595c7d_orig.gif",
//            "http://s1.dwstatic.com/group1/M00/97/AF/ba42fc1a995c02c9268e406836634d5f.gif",
//            "http://img.zcool.cn/community/01019856dfcaf332f875520f91c7f7.gif",
//            "http://img.zcool.cn/community/013f2856df80f96ac72531cb40e46e.gif",
//            "http://img05.tooopen.com/products/20141215/EC17D785-1E06-F2C9-8A4B-4CBE9D0C8B08.gif",
//            "http://photocdn.sohu.com/20150808/mp26389744_1439008079309_5.gif",
//            "http://n.sinaimg.cn/translate/20160803/jO8f-fxutfpf1158182.gif",
//            "http://photocdn.sohu.com/20151209/mp47467865_1449652374858_6.gif",
//            "http://img1.gamersky.com/image2014/06/20140608lk_4/2.gif",
//            "http://img0.utuku.china.com/uploadimg/culture/20160122/949b061f-7dff-4362-a858-032216ef5257.gif"
//    };
    private final String[] gifUrls = {
            "http://img4q.duitang.com/uploads/blog/201501/17/20150117192149_Jj5we.gif",
            "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif",
            "http://attach.scimg.cn/month_1303/19/235b13b13b25f9a80db73f4d13595c7d_orig.gif",
            "http://s1.dwstatic.com/group1/M00/97/AF/ba42fc1a995c02c9268e406836634d5f.gif",
            "http://img.zcool.cn/community/01019856dfcaf332f875520f91c7f7.gif",
            "http://img.zcool.cn/community/013f2856df80f96ac72531cb40e46e.gif",
            "http://img05.tooopen.com/products/20141215/EC17D785-1E06-F2C9-8A4B-4CBE9D0C8B08.gif",
            "http://photocdn.sohu.com/20150808/mp26389744_1439008079309_5.gif",
            "http://n.sinaimg.cn/translate/20160803/jO8f-fxutfpf1158182.gif",
            "http://photocdn.sohu.com/20151209/mp47467865_1449652374858_6.gif",
            "http://img1.gamersky.com/image2014/06/20140608lk_4/2.gif"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(this, gifUrls);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mHeader = findViewById(R.id.header);
        mHeadIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.header_in);
        mHeadIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mHeadAnimIn = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHeadAnimIn = false;
                mHeader.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mHeadOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.header_out);
        mHeadOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mHeadAnimOut = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHeadAnimOut = false;
                mHeader.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            private float lastY = -1.0f;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float rawY = motionEvent.getRawY();
                if (action == MotionEvent.ACTION_DOWN) {
                    lastY = motionEvent.getRawY();
                    return false;
                }
                if (action == MotionEvent.ACTION_MOVE) {
                    if (lastY < 0) {
                        lastY = rawY;
                        return false;
                    }
                    float translateY = mHeader.getTranslationY();
                    float diffY = rawY - lastY;
                    float newTranslateY = translateY + diffY;
                    if (newTranslateY > 0) {
                        newTranslateY = 0;
                    } else if (newTranslateY < -mHeader.getHeight()) {
                        newTranslateY = -mHeader.getHeight();
                    }
                    lastY = rawY;
                    if (translateY < 0 && translateY > -mHeader.getHeight()) {
                        mHeader.setTranslationY(newTranslateY);
                        mRecyclerView.setTranslationY(newTranslateY);
                        return true;
                    } else if (translateY >= 0) {
                        if (diffY > 0) {
                            return false;
                        }
                        mHeader.setTranslationY(newTranslateY);
                        mRecyclerView.setTranslationY(newTranslateY);
                        return true;
                    } else {
                        if (diffY < 0) {
                            return false;
                        }
                        mHeader.setTranslationY(newTranslateY);
                        mRecyclerView.setTranslationY(newTranslateY);
                        return true;
                    }
                } else if (action == MotionEvent.ACTION_UP || action == motionEvent.ACTION_CANCEL) {
                    lastY = -1.0f;
                }
                return false;
            }
        });
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();
//                if (firstVisibleItemPosition <= 1) {
//                    return;
//                }
//                if (dy < -30 && !mHeadAnimIn && (mHeader.getVisibility() != View.VISIBLE)) {
//                    mHeader.clearAnimation();
//                    mHeader.startAnimation(mHeadIn);
//                } else if (dy > 30 && !mHeadAnimOut && (mHeader.getVisibility() == View.VISIBLE)) {
//                    mHeader.clearAnimation();
//                    mHeader.startAnimation(mHeadOut);
//                }
//            }
//        });
    }
}
