package com.sinoangel.ctrl.parentalcontrol.curriculumschedule;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.youku.cloud.player.VideoDefinition;
import com.youku.cloud.player.YoukuPlayerView;

public class CurriculumVedioActivity extends BaseActivity {

    private YoukuPlayerView youkuPlayerView;

    private String vid;
    private String password;
    private boolean local = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_vedio);

        readIntent();

        youkuPlayerView = (YoukuPlayerView) findViewById(R.id.baseview);
        youkuPlayerView.attachActivity(this);
        youkuPlayerView.setPreferVideoDefinition(VideoDefinition.VIDEO_HD);
        youkuPlayerView.setShowBackBtn(true);
        youkuPlayerView.goSmallScreen();

//        youkuPlayerView.setPlayerListener(new MyPlayerListener());

        youkuPlayerView.playYoukuVideo(vid);
    }

    private void readIntent() {
        Intent intent = getIntent();
        vid = intent.getStringExtra("vid");
        local = intent.getBooleanExtra("local", false);
        password = intent.getStringExtra("password");
    }

    @Override
    protected void onPause() {
        youkuPlayerView.onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        youkuPlayerView.onResume();
        super.onResume();
        // 必须重写的onResume()
    }

    @Override
    protected void onDestroy() {
        youkuPlayerView.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //后退键的处理，当全屏时，后退为变为小屏
        if (youkuPlayerView.isFullScreen()) {
            youkuPlayerView.goSmallScreen();
        } else {
            super.onBackPressed();
        }
    }
}
