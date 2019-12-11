package com.example.chatification;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

                new Thread(new SignUp()).start();

                finish();
                break;
            case R.id.cancelBtn:
                finish();
                break;
        }
    }

    private class SignUp implements Runnable {

        @Override
        public void run() {

            Log.e("SignUp", "run");
            String param = "u_id=" + sEditId.getText() + "&u_pw=" + sEditPw.getText() + "&u_name=" + sEditName.getText() + "&u_age=" + sEditAge.getText();

            try {
                URL url = new URL(ServerCommunication.IP + "/insert.php");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //conn.setRequestProperty("Content-Type", "application/json"); // JSON 형태로 전송, APCP할때는 이런 코드 안써도 저절로 JSON 형태로 전송했는데....
                conn.setRequestMethod("POST"); // POST 방식
                conn.setConnectTimeout(5000); // 웹과 접속을 하고 답변을 받는 데 응답이이 없으면 5초 뒤에 끊는 다는 것
                conn.setDoInput(true); // InputStream으로 서버로부터 응답을 받겠다는 옵션
                conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
                conn.connect();

                // 안드로이드 to 서버
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                InputStream is;
                BufferedReader in;
                String data;

                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK)
                    is = conn.getInputStream();
                else
                    is = conn.getErrorStream();

                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();
                Log.e("RECV DATA", data);

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                Log.e("SignUp","" + e);
                e.printStackTrace();
            }

        }
    }
}
