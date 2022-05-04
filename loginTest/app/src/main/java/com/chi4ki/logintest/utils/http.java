package com.chi4ki.logintest.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class http {
    public static String sendGet(String url) {
        String text="";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.d("tag", "response.code()==" + response.code());
                Log.d("tag", "response.message()==" + response.message());
                text=response.body().string();
                Log.d("tag", "res==" + text);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return text;
    }
    public static String sendPost(String url, FormBody.Builder formBody) {
        String text="";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody.build())
                    .build();
            Response response = null;
            response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.d("tag", "response.code()==" + response.code());
                Log.d("tag", "response.message()==" + response.message());
                text=response.body().string();
                Log.d("tag", "res==" + text);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return text;
    }
    public static String sendPost(String url,FormBody.Builder formBody,String tokenName,String token) {
        String text="";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader(tokenName,token)
                    .post(formBody.build())
                    .build();
            Response response = null;
            response=client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.d("tag", "response.code()==" + response.code());
                Log.d("tag", "response.message()==" + response.message());
                Log.d("tag", "res==" + response.body().string());
                text=response.body().string();
                Log.d("tag", "res==" + text);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return text;
    }
    public static String parseJsonWithJsonObject_string(String responseData, String search) throws IOException {
        String str="";
        try{
            JSONObject jsonObject=new JSONObject(responseData);
            str=jsonObject.getString(search);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }
    public static boolean parseJsonWithJsonObject_boolean(String responseData, String search) throws IOException {
        boolean boo=false;
        try{
            JSONObject jsonObject=new JSONObject(responseData);
            boo=jsonObject.getBoolean(search);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return boo;
    }
    public static int parseJsonWithJsonObject_int(String responseData, String search) throws IOException {
        int num=0;
        try{
            JSONObject jsonObject=new JSONObject(responseData);
            num=jsonObject.getInt(search);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }
    /**
     *pat:正则表达式
     *text:检索文本
     */
    public static String search(String pat, String text) {
        Pattern r = Pattern.compile(pat);
        Matcher m = r.matcher(text);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }
}
