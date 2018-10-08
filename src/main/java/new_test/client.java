package new_test;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

public class client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 2004);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());

            dout.writeUTF("发自客户端!!!!!!!!!!!!!!!!!!!!!!!!!!");
            dout.flush();

            String income_message;
            if((income_message = din.readUTF()) != null) {
                System.out.println("收到服务端信息 ： "+income_message);
            }else{
                System.out.println("未收到来自服务端的验证消息，连接断开！");
                dout.close();
                din.close();
                socket.close();
            }

            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\模型\\http\\test_data1.csv"), "GB2312");
                BufferedReader reader = new BufferedReader(isr);
                String header = reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
                System.out.println("-----------------header---------------------");
                System.out.println(header);
                String line = null;
                int j = 0;
                String sent_message;
                String sent_valid_message = "TRUE";
                while((line=reader.readLine())!=null && (income_message = din.readUTF()) != null){
                    j++;
                    String [] item = line.split("，");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                    String last = item[item.length-1];//这就是你要的数据了
                    String[] last_ = last.split(",");

                    Map featureNames = new HashMap();

                    featureNames.put("customer_id",last_[0]);
                    featureNames.put("age",last_[1].isEmpty()?"NAN":Float.valueOf(last_[1].trim()).floatValue());
                    featureNames.put("sex",last_[2].isEmpty()?"NAN":Float.valueOf(last_[2].trim()).floatValue());
                    featureNames.put("gps_city",last_[3].isEmpty()?"NAN":last_[3].trim().toString());
                    featureNames.put("device_brand",last_[4].isEmpty()?"NAN":last_[4].trim().toString());
                    featureNames.put("geo_score_geo104",last_[5].isEmpty()?"NAN":Float.valueOf(last_[5].trim()).floatValue());
                    featureNames.put("cdzc003_geo104",last_[6].isEmpty()?"NAN":Float.valueOf(last_[6].trim()).floatValue());
                    featureNames.put("apply_time",last_[7].isEmpty()?"NAN":Float.valueOf(last_[7].trim()).floatValue());
                    featureNames.put("auth_contactnum_ratio_30d_mz",last_[8].isEmpty()?"NAN":Float.valueOf(last_[8].trim()).floatValue());
                    featureNames.put("mobile_match_score",last_[9].isEmpty()?"NAN":Float.valueOf(last_[9].trim()).floatValue());
                    featureNames.put("in_gray",last_[10].isEmpty()?"NAN":last_[10].trim().toString());
                    featureNames.put("huo_mou_score",last_[11].isEmpty()?"NAN":Float.valueOf(last_[11].trim()).floatValue());
                    featureNames.put("tx_score",last_[12].isEmpty()?"NAN":Float.valueOf(last_[12].trim()).floatValue());
                    featureNames.put("td_fraud_score",last_[13].isEmpty()?"NAN":Float.valueOf(last_[13].trim()).floatValue());

//                    System.out.println(featureNames.toString());

                    System.out.println("接受到新信息： "+ income_message);

                    sent_message = featureNames.toString();
                    dout.writeUTF(sent_message);
                    dout.flush();
                    System.out.println("发送了新数据："+sent_message);

                    if(j>90){
                        sent_valid_message = "FALSE";
                        dout.writeUTF(sent_valid_message);
                        dout.flush();
                    }else{
                        sent_valid_message = "TRUE";
                        dout.writeUTF(sent_valid_message);
                        dout.flush();
                    }

                    }
//                 income_message = din.readUTF();
//                 System.out.println("接受到新信息：----- "+ income_message);

            } catch (Exception e) {
                e.printStackTrace();
            }

//            dout.writeUTF("我准备开始发送数据了！");
//            dout.flush();

            dout.flush();
            dout.close();
            din.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}