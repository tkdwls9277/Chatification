package com.example.chatification;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction fta;

    private String id;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        id = getIntent().getStringExtra("u_id");

        Bundle bundle = new Bundle(1);
        bundle.putString("u_id", id);

        ChattingFragment chattingFragment = new ChattingFragment();
        chattingFragment.setArguments(bundle);

        fm = getSupportFragmentManager();
        fta = fm.beginTransaction();
        fta.add(R.id.frameLayout, chattingFragment);
        fta.commit();
    }

    public void onClickImageView(View v) { // 미완
        switch (v.getId()) {
            case R.id.profile:
                fta.replace(R.id.frameLayout, new ProfileFragment());
                fta.commit();
                break;
            case R.id.chatting:
                fta.replace(R.id.frameLayout, new ChattingFragment());
                fta.commit();
                break;
            case R.id.board:
                fta.replace(R.id.frameLayout, new BoardFragment());
                fta.commit();
                break;
        }
    }
}
