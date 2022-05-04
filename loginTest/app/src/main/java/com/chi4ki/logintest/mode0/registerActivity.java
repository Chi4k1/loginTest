package com.chi4ki.logintest.mode0;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chi4ki.logintest.R;
import com.chi4ki.logintest.utils.http;

import okhttp3.FormBody;

public class registerActivity extends AppCompatActivity {
    private final String ip="http://192.168.50.170:4523/mock/935584/register";
    private String type="0";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_login = getWindow().findViewById(R.id.btn_register);
        btn_login.setOnClickListener(new registerClickListener());
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());
    }
    //下拉框的监听
    private class  ProvOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapter,View view,int position,long id) {
            String sInfo=adapter.getItemAtPosition(position).toString();
            if(sInfo.equals("1")) type="1";
            if(sInfo.equals("2")) type="2";
            if(sInfo.equals("3")) type="3";
            if(sInfo.equals("4")) type="4";
            if(sInfo.equals("请选择你的身份")) type="0";
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            String sInfo="null";
        }
    }
    class registerClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btn_register){
                EditText userName = getWindow().findViewById(R.id.userName);
                EditText password = getWindow().findViewById(R.id.passward_first);
                EditText password2 = getWindow().findViewById(R.id.passward_confirm);
                String name=userName.getText().toString();
                String pwd=password.getText().toString();
                String pwd2=password2.getText().toString();
                boolean judge=true;
                if(name.equals("")){
                    showMsg("请输入用户名");
                    judge=false;
                }else if(pwd.equals("")){
                    showMsg("请输入密码");
                    judge=false;
                }else if(pwd2.equals("")){
                    showMsg("请确认密码");
                    judge=false;
                }else if(!pwd2.equals(pwd)){
                    showMsg("密码不一致");
                    judge=false;
                }else if(type.equals("0")){
                    showMsg("请选择您的身份");
                    judge=false;
                }
                if(judge){
                    new Thread(()->{
                        try {
                            FormBody.Builder formBody = new FormBody.Builder();
                            formBody.add("userName",name);
                            formBody.add("password",pwd);
                            formBody.add("role",type);
                            String text = http.sendPost(ip, formBody);
                            boolean status=http.parseJsonWithJsonObject_boolean(text,"success");
                            Log.d("tag",status+"\n"+text);
                            if (status) {
                                showMsg_thread("你已经成功创建账号,快去登陆吧");
                                finish();
                            } else showMsg_thread("注册失败");
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        }
    }
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void showMsg_thread(String msg){
        runOnUiThread(()->Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show());
    }

}
