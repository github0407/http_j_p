package new_test;

import java.io.*;
import java.net.*;
import java.lang.*;

public class client {

    public static void main(String[] args) {

        try{
            Socket socket=new Socket("10.1.5.102",2004);

            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            DataInputStream din=new DataInputStream(socket.getInputStream());

//            dout.writeUTF("Hello");
//            dout.flush();

            dout.writeUTF("vfvfvfvf");
            dout.flush();

            System.out.println("send first mess");
            String inputLine;
//            String outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            while ((inputLine = din.readUTF()) != null) {
//                outputLine = kkp.processInput(inputLine);
                System.out.println(inputLine);
                if (inputLine.equals("Bye"))
                    break;
            }
//            String str = din.readUTF();//in.readLine();
//            System.out.println("Message"+str);

            dout.close();
            din.close();
            socket.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

}