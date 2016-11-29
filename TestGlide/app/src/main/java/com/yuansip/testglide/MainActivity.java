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
import static com.yuansip.testglide.ImageSource.gifUrls;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(this, gifUrls);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mHeader = findViewById(R.id.header);
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
    }
}
