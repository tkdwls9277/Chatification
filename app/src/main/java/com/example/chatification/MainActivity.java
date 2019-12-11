package com.example.chatification;

import android.content.Intent;
import android.os.AsyncTask;
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
                String id = editId.getText().toString();
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                intent.putExtra("u_id", id); // 임시로 intent로 보내자.

                startActivity(intent);
                break;
            case R.id.signUpBtn:
                startActivity(new Intent(v.getContext(), SignUpActivity.class));
                break;
        }
    }

    private class LogInTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            startActivity(new Intent(getApplicationContext(), MenuActivity.class)); // 안될지도

        }
    }
}
