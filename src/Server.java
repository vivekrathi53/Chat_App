import javafx.util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{

    public  ArrayList<Pair<String, Socket>> activelist;
    public MessageManager msh;
    public static void main(String[] args)
    {
        Server server=new Server();
        server.activelist=new ArrayList<Pair<String, Socket>>();
        server.msh = new MessageManager(server);
        try
        {
            ServerSocket ss=new ServerSocket(9175);
            while(true)
            {
                Socket sc = ss.accept();//request is received
                ClientHandler auth=new ClientHandler(sc,server,server.msh);
                Thread t=new Thread(auth);
                t.start();
                server.activelist.add(new Pair<String, Socket>("", sc));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
