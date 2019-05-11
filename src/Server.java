import javafx.util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{

    public static void main(String[] args)
    {
        ArrayList<Pair<String, Socket>> activelist=new ArrayList<Pair<String, Socket>>();
        try
        {
            ServerSocket ss=new ServerSocket(8999);
            while(true)
            {
                Socket sc = ss.accept();//request is received
                ClientHandler auth=new ClientHandler(sc,ss);
                Thread t=new Thread(auth);
                t.start();
                activelist.add(new Pair<String, Socket>("", sc));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
