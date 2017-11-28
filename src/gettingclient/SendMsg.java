package gettingclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SendMsg implements Runnable{
    Socket mySocket;
    public SendMsg(){}
    public SendMsg(Socket mySocket){
    this.mySocket=mySocket;
    }
public void setMsg(String msg){
    
}
    @Override
    public void run() {
        while(true){
        try {
            Scanner  sc = new Scanner(System.in);
            System.out.println("Передача новой игры");
            System.out.println("Введите название игры");
            String name = sc.nextLine();
            System.out.println("Введите жанр игры");
            String genre = sc.nextLine();
            System.out.println("Введите год игры");
            String year = sc.nextLine();
            System.out.println("Введите оценку игры");
            String mark = sc.nextLine();
            String xml="<?xml version = \"1.0\"?>"+
          "<class>"+
          "  <game rollno = \"393\">"+
          "    <name>\""+name+"\"</name>"+
          "   <genre>\""+genre+"\"</genre>"+
          "  <year>\""+year+"\"</year>"+
          " <mark>\""+mark+"\"</mark>"+
          "</game>"+
          "</class>";
            OutputStream outToServer  = mySocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(xml);
            
        } catch (IOException ex) {
            Logger.getLogger(SendMsg.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    }
    
}
