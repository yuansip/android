package com.yuansip.testglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
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
    }
}
