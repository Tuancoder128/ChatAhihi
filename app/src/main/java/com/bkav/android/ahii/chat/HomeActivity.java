package com.bkav.android.ahii.chat;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName, edtPassWord;
    public String mUserName, mPassWord;
    private Button mLogin;
    private boolean mCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


    }

    public void init() {
        edtUserName = (EditText) findViewById(R.id.edtUser);
        edtPassWord = (EditText) findViewById(R.id.edtPassword);

        mUserName = edtUserName.getText().toString();
        mPassWord = edtPassWord.getText().toString();

        mLogin = (Button) findViewById(R.id.btnLogin);
        mLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();

                break;
        }
    }

    private void login() {
        MyAsync myAsync = new MyAsync();
        if (edtUserName.getText().toString().trim().length() > 0 &&
                edtPassWord.getText().toString().length() > 0) {
            myAsync.execute(mUserName, mPassWord);
        } else {
            Toast.makeText(this, "Wrong username or password.Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyAsync extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(HomeActivity.this, "Chúc các bạn nghe nhạc vui vẻ ...", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            if (params.length > 1) {
                mCheck = true;
            } else {
                mCheck = false;
            }

            return mCheck;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Intent musicIntent = new Intent(HomeActivity.this, MusicActivity.class);
                startActivities(new Intent[]{musicIntent});
            }
            super.onPostExecute(aBoolean);
        }


        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }

}
