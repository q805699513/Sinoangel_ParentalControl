package com.sinoangel.ctrl.parentalcontrol.curriculumschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
//        setContentView(R.layout.activity_curriculumschedule);

        int id = getIntent().getIntExtra("aa", 0);
        if (id == 0)
            setContentView(R.layout.zzz_item1);
        else if (id == 1)
            setContentView(R.layout.zzz_item4);
        else if (id == 2)
            setContentView(R.layout.zzz_item2);
        else
            setContentView(R.layout.zzz_item3);

//        rv_list = (RecyclerView) findViewById(R.id.rv_list);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rv_list.setLayoutManager(llm);
//
//        rv_list.setAdapter(curriculumAdapter);
//
//        getData();

    }

    private void getData() {
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.v1:
                Intent intent1 = new Intent(this, CurriculumScheduleActivity.class);
                intent1.putExtra("aa", 1);
                startActivity(intent1);
                break;
            case R.id.v2:
                Intent intent2 = new Intent(this, CurriculumScheduleActivity.class);
                intent2.putExtra("aa", 2);
                startActivity(intent2);
                break;
            case R.id.v3:
                Intent intent3 = new Intent(this, CurriculumScheduleActivity.class);
                intent3.putExtra("aa", 3);
                startActivity(intent3);
                break;
        }
    }
}
