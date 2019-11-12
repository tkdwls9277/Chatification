package com.example.chatification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editId;
    private EditText editPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);

    }

    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                break;
            case R.id.signUpBtn:
                startActivity(new Intent(v.getContext(), SignUpActivity.class));
                break;
        }
    }
}
