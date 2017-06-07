package com.sinoangel.ctrl.parentalcontrol.curriculumschedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.curriculumschedule.adapter.CurriculumAdapter;

/**
 * Created by Z on 2017/6/5.
 */

public class CurriculumScheduleActivity extends BaseActivity {

    private CurriculumAdapter curriculumAdapter = new CurriculumAdapter(this);
    private RecyclerView rv_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculumschedule);

        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(llm);

        rv_list.setAdapter(curriculumAdapter);

        getData();

    }

    private void getData() {
    }

}
