import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{

    public  ArrayList<Pair<String, Socket>> activelist;
    public ArrayList<Pair<ObjectInputStream, ObjectOutputStream>> activeUserStreams;
    public MessageManager msh;
    public static void main(String[] args) throws Exception {
        Server server=new Server();
        server.activelist=new ArrayList<Pair<String, Socket>>();
        server.activeUserStreams=new ArrayList<>();
        server.msh = new MessageManager(server);
        try
        {
            ServerSocket ss=new ServerSocket(9155);
            while(true)
            {
                Socket sc = ss.accept();//request is received
                ClientHandler auth=new ClientHandler(sc,server,server.msh);
                Thread t=new Thread(auth);
                server.activelist.add(new Pair<String, Socket>("", sc));
                ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
                server.activeUserStreams.add(new Pair<>(ois,oos));
                auth.oos=oos;
                auth.ois=ois;
                t.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
