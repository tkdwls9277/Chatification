package com.example.chatification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText sEditId;
    private EditText sEditPw;
    private EditText sEditName;
    private EditText sEditAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sEditId = findViewById(R.id.sEditId);
        sEditPw = findViewById(R.id.sEditPw);
        sEditName = findViewById(R.id.sEditName);
        sEditAge = findViewById(R.id.sEditAge);

    }

    public void sOnClickBtn(View v) {
        switch (v.getId()) {
            case R.id.confirmBtn:
                break;
            case R.id.cancelBtn:
                break;
        }
    }
}
