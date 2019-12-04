package com.example.chatification;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction fta;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fm = getSupportFragmentManager();
        fta = fm.beginTransaction();
        fta.add(R.id.frameLayout, new ChattingFragment()); // add말고 replace로 바꿔야할 수도
        fta.commit();
    }

    public void onClickImageView(View v) {
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
