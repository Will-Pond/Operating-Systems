import java.net.ServerSocket;
import java.net.Socket;

public class gameDaemon {

     static ServerSocket  server;

    static Socket toclientsocket;

    public static void main(String[] args)
    {
        try
        {
           server = new ServerSocket(12345);

           while(true)
          {
              toclientsocket = server.accept();
              gameThread conn_c = new gameThread(toclientsocket);
              conn_c.start();
          }

        } catch (Exception e)
        {
          System.out.println(e);
        }

    }
}
