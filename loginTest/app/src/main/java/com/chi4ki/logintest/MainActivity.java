package com.chi4ki.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chi4ki.logintest.mode0.registerActivity;
import com.chi4ki.logintest.mode1.mode1Activity;
import com.chi4ki.logintest.mode2.mode2Activity;
import com.chi4ki.logintest.mode3.mode3Activity;
import com.chi4ki.logintest.mode4.mode4Activity;
import com.chi4ki.logintest.utils.SPUtils;
import com.chi4ki.logintest.utils.http;
import com.chi4ki.logintest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity {
    private final String ip1="http://192.168.50.170:4523/mock/935584/login";
    EditText edtName,edtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String login = SPUtils.getString("login",null,MainActivity.this);
        if(login != null) {
            if (login.equals("1")) {
                Intent intent=new Intent(MainActivity.this, mode1Activity.class);
                startActivity(intent);
                finish();
            }
            if (login.equals("2")) {
                Intent intent=new Intent(MainActivity.this, mode2Activity.class);
                startActivity(intent);
                finish();
            }
            if (login.equals("3")) {
                Intent intent=new Intent(MainActivity.this, mode3Activity.class);
                startActivity(intent);
                finish();
            }
            if (login.equals("4")) {
                Intent intent=new Intent(MainActivity.this,mode4Activity.class);
                startActivity(intent);
                finish();
            }
        }
        edtName=getWindow().findViewById(R.id.userName);
        edtPwd=getWindow().findViewById(R.id.passward);
        Button btn_login = getWindow().findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new loginClickListener());
        View register = getWindow().findViewById(R.id.tv_register);
        register.setOnClickListener(new loginClickListener());
    }
    class loginClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Integer id=v.getId();
            if(id.equals(R.id.btn_login)){
                connect();
            }
            if(id.equals(R.id.tv_register)){
                startActivity(new Intent(MainActivity.this, registerActivity.class));
            }
        }
    }
    private void connect(){
        new Thread(()->{
            try{
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("userName",edtName.getText().toString());
                formBody.add("password",edtPwd.getText().toString());
                String text=http.sendPost(ip1, formBody);
                int type=http.parseJsonWithJsonObject_int(text,"type");
                boolean success=http.parseJsonWithJsonObject_boolean(text,"success");
                String token=http.parseJsonWithJsonObject_string(text,"token");
                if (success) {
                    if (type==1) {
                        Intent intent=new Intent(MainActivity.this,mode1Activity.class);
                        intent.putExtra("token",token);
                        SPUtils.putString("login","1",MainActivity.this);
                        startActivity(intent);
                    }
                    if (type==2) {
                        Intent intent=new Intent(MainActivity.this,mode2Activity.class);
                        intent.putExtra("token",token);
                        SPUtils.putString("login","2",MainActivity.this);
                        startActivity(intent);
                    }
                    if (type==3) {
                        Intent intent=new Intent(MainActivity.this,mode3Activity.class);
                        intent.putExtra("token",token);
                        SPUtils.putString("login","3",MainActivity.this);
                        startActivity(intent);
                    }
                    if (type==4) {
                        Intent intent=new Intent(MainActivity.this,mode4Activity.class);
                        intent.putExtra("token",token);
                        SPUtils.putString("login","4",MainActivity.this);
                        startActivity(intent);
                    }
                } else {
                    showMsg_thread("fail");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void showMsg_thread(String msg){
        runOnUiThread(()->Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show());
    }


}