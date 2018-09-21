package new_test;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 2004);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());

            dout.writeUTF("开始发送用户数据-------->>>>>>>>>>>>>");
            dout.flush();

            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\模型\\http\\test_data.csv"), "GB2312");
                BufferedReader reader = new BufferedReader(isr);
                String header = reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
                System.out.println("-----------------header---------------------");
                System.out.println(header);
                String line = null;
                int j = 0;
                while((line=reader.readLine())!=null){
                    j++;
                    String [] item = line.split("，");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                    String last = item[item.length-1];//这就是你要的数据了

                    String[] last_ = last.split(",");
//                    ((Number)last_[1]).floatValue();
                    System.out.println("cust_id："+last_[0]);
                    System.out.println("TX_score_WOE："+Float.valueOf(last_[1].trim()).floatValue());
                    System.out.println("TD_Fraud_score_WOE："+Float.valueOf(last_[2].trim()).floatValue());
                    System.out.println("C_city_x_encoding_WOE："+Float.valueOf(last_[3].trim()).floatValue());
                    System.out.println("GEO_SCORE_geo104_WOE："+Float.valueOf(last_[4].trim()).floatValue());
                    System.out.println("huomou_score_WOE："+Float.valueOf(last_[5].trim()).floatValue());
                    System.out.println("sex_age_encoding_WOE："+Float.valueOf(last_[6].trim()).floatValue());
                    System.out.println("match_score_mz_WOE："+Float.valueOf(last_[7].trim()).floatValue());
                    System.out.println("auth_contactnum_ratio_30d_mz_WOE："+Float.valueOf(last_[8].trim()).floatValue());
                    System.out.println("CDZC003_geo104_WOE："+Float.valueOf(last_[9].trim()).floatValue());
                    System.out.println("brand_x_encoding_WOE："+Float.valueOf(last_[10].trim()).floatValue());
                    System.out.println("C_app_date_WOE："+Float.valueOf(last_[11].trim()).floatValue());
                    System.out.println("idcard_name_in_gray_mz_WOE："+Float.valueOf(last_[12].trim()).floatValue());
                    System.out.println("intercept："+Float.valueOf(last_[13].trim()).floatValue());

                    System.out.println("dijihang: "+j);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }

            dout.writeUTF("我准备发送数据了！");
            dout.flush();

            String inputLine;
            int i = 0;
            while ((inputLine = din.readUTF()) != null) {
                if(inputLine == "TRUE"){
                    System.out.println("接受到确认信息： "+inputLine);
                }

                dout.writeUTF("{cust_id:a1234,name:'小强',age:23}");
                dout.flush();
                i++;
                if (inputLine.equals("Bye"))
                    break;
            }
//            String str = din.readUTF();//in.readLine();
//            System.out.println("Message"+str);

            dout.flush();
            dout.close();
            din.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}