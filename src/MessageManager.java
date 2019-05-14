import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class MessageManager
{
    Server server;
    ObjectOutputStream oos;
    ObjectOutputStream oos2;
    Connection connection;

    public MessageManager(Server ss) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/Chat_App";
        connection = DriverManager.getConnection(url, "root", "password");
        server = ss;
    }

    public void insert(String users,Object obj) throws ClassNotFoundException, SQLException
    {
        int valid=-1;
        String sender = null,content=null;
        Timestamp time=null;
        if(obj instanceof Message )
        {
            Message message=(Message)obj;
            valid=0;//sent message
            sender = message.getFrom();
            content = message.getContent();
            time = message.getSentTime();
        }
        else if(obj instanceof SystemMessage)
        {
            SystemMessage sm=(SystemMessage)obj;
            valid=sm.valid;
            sender=sm.sender;
            time=sm.time;
            content=null;
        }
        String table=users+"Table";
        String query1 = "INSERT INTO "+(table)+" VALUES (?, ?, ?, ?)";
        PreparedStatement preStat = connection.prepareStatement(query1);
        preStat.setString(1, sender);
        preStat.setInt(2, valid);
        preStat.setString(3, content);
        preStat.setTimestamp(4, time);
        preStat.executeUpdate();
    }

    public Socket find(String sender) {
        int flag = 0;
        Socket temp = null;
        int i=0;
        for (Pair<String, Socket> value : server.activelist) {
            String check = value.getKey();
            if (check == sender)
            {
                temp = value.getValue();
                flag = 1;
                break;
            }
            //System.out.print(value);
            i++;
        }
        System.out.println(temp);
        if(i<server.activeUserStreams.size())
        {
            oos2=server.activeUserStreams.get(i).getValue();
        }
        return temp;
    }

    public void remove(Socket user,String username) throws SQLException, IOException, ClassNotFoundException {
        String table = username + "Table";
        String query = "SELECT * FROM " + (table) + "";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        while (result.next()) {
            String sender = result.getString("Sender");
            int valid = result.getInt("Valid");
            Timestamp time = result.getTimestamp("Time");
            String content = result.getString("Message");
            System.out.println("Name - " + sender);
            System.out.println("content - " + content);
            System.out.println("valid - " + valid);
            if (valid == 1 || valid == 2)// Message Sent is Received or Seen
            {//1 Received Time 2 Seen Time

                SystemMessage sm=new SystemMessage(sender ,valid, time);
                oos.writeObject(sm);//USER GET INFO OF RECEIVING  AND SEEN TIME
                oos.flush();
            }
            else
            {
                Socket receiver = find(sender);
                Message ms=new Message(username,sender,content,time,null,null);
                oos.writeObject(ms);//Sent message to User
                oos.flush();

                LocalDateTime datetime1 = LocalDateTime.now();//To get Local time
                time= Timestamp.valueOf(datetime1);
                //System.out.println(datetime1);
                if (receiver != null)// if Sender is online
                {
                    System.out.println("User is Active");
                    SystemMessage sm=new SystemMessage(username ,1,time );
                    oos2.writeObject(sm);//Sender get Info of receiving time
                    oos2.flush();

                }
                else//if sender is offline
                {

                    SystemMessage sm=new SystemMessage(username,1,time);//Sender get Receiving time of his message
                    insert(sender,sm);
                }
            }
        }
    }
}
