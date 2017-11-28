import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GettingServer extends Thread {
    private ServerSocket serverSocket;
    public List<Socket> clients=new ArrayList<Socket>();
    public List<ClientHandler> clientsHandler=new ArrayList<ClientHandler>();
     
   public GettingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);     
   }
   void updateConnections(List<Socket> clients){
       for(int i=0;i<this.clientsHandler.size();i++){
                this.clientsHandler.get(i).setClients(clients);
           }
   }
   

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            clients.add(server);
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            ClientHandler clientHandler=new ClientHandler(server,clients);
            clientsHandler.add(clientHandler);
            Thread newClient=new Thread(clientHandler);  
             newClient.start();
             updateConnections(clients);
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 7777;
      try {
         Thread t = new GettingServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
    
}
