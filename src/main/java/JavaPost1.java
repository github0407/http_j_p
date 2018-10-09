/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaPost1 {
    public static final String ADD_URL = "http://localhost:8888/";
    public static void appadd() throws JSONException {
        try {
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject json = new JSONObject();
            json.put("id", "1401_1406");
            json.put("device_size", "480x720");
//            JSONObject json = new JSONObject(sendData);
            String jsonString = json.toString();
            byte[] jsonByte = jsonString.getBytes();
            System.out.println("jsonString is "+ jsonString);
            System.out.println("jsonByte is "+ jsonByte);
            out.write(jsonByte);
            out.flush();
            out.close();
            
            System.out.println("test");
            
             //读取响应
            DataInputStream inputStream = null;  
    	    String strInputStream ="";
    	    inputStream = new DataInputStream(new BufferedInputStream(connection.getInputStream()));   
    	    byte[] by = new byte[20480];
    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    int nbyte;
    	    while((nbyte=inputStream.read(by))!=-1){
    		  baos.write(by,0,nbyte);
    	    }
            
    	    strInputStream = new String(baos.toByteArray());
     	    JSONObject js = new JSONObject(strInputStream);
      	    System.out.println(js.toString());
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JSONException {
        appadd();
    }
 }