package new_test;

import java.io.*;
import java.net.*;
import java.lang.*;

public class client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 2004);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());

//            dout.writeUTF("Hello");
//            dout.flush();

            dout.writeUTF("vfvfvfvf:{ehglegntg:[\"测试数据kjlllllllllllllllllllllllllllllljkhdsdfgdfsdfgsdfh发送," +
                    "xcvbdfghjyuijkkkkkkkkkkkkkljiuyityujiiiiiiiiiiiiiiiiiiiiiiiiiii" +
                    "][]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]][][]最好以json格式\"]}");
            dout.flush();

            System.out.println("send first mess");
            String inputLine;
//            String outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            int i = 0;
            while ((inputLine = din.readUTF()) != null) {
//                outputLine = kkp.processInput(inputLine);
                System.out.println(inputLine);
                dout.writeUTF("-----------------++++++"+i+"++++++++++++++++++++---------------");
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