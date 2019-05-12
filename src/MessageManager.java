import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;

public class MessageManager
{
    Server server;
    ObjectOutputStream oos;

    public MessageManager(Server ss) {
        server = ss;
    }

    public void insert(Message message) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://http://192.168.0.100:3306/Chat_App";
        Connection connection = DriverManager.getConnection(url, "root", "password");
        String sender = message.getFrom();
        String content = message.getContent();
        Timestamp t1 = message.getSentTime();
        Timestamp t2 = message.getReceivedTime();
        Timestamp t3 = message.getSeenTime();
        int valid;
        Timestamp time;
        if (t1 == null && t2 == null) {
            valid = 2;
            time = t3;//Seen Time
        }
        if (t1 == null && t3 == null) {
            valid = 1;
            time = t2;//Receive Time
        } else {
            valid = 0;
            time = t1;//Sent Time
        }
        String query1 = "INSERT INTO ReceivedTable VALUES (?, ?, ?, ?)";
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
        for (Pair<String, Socket> value : server.activelist) {
            String check = value.getKey();
            if (check == sender) {
                temp = value.getValue();
                flag = 1;
                break;
            }
            System.out.print(value);
        }
        return temp;
    }

    public void remove(Socket user,String username) throws SQLException, IOException {
        String table = username + "Table";
        String query = "SELECT * FROM '" + (table) + "'";
        String url = "jdbc:mysql://http://192.168.0.100:3306/Chat_App";
        Connection connection = DriverManager.getConnection(url, "root", "password");
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery(query);
        while (result.next()) {
            String sender = result.getString("Sender");
            int valid = result.getInt("Valid");
            Timestamp time = result.getTimestamp("Time");
            String content = result.getString("Message");
            String branch = result.getString("branch");
            System.out.println("Name - " + sender);
            System.out.println("content - " + content);
            System.out.println("valid - " + valid);
            if (valid == 1 || valid == 0)
            {
                oos = new ObjectOutputStream(user.getOutputStream());
                SystemMessage sm=new SystemMessage(sender ,valid, time);
            }
            else
            {
                Socket receiver = find(sender);
                if (receiver != null)// IF USER IS ACTIVE
                {
                    oos = new ObjectOutputStream(receiver.getOutputStream());
                    System.out.println("User is Active");
                    Message ms=new Message(username,sender,content,time,null,null);
                    oos.writeObject(ms);
                    oos.flush();
                    oos.close();
                }
            }
        }
    }
}
