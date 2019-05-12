import java.sql.*;

public class MessageManager
{
    public void insert(Message message) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://http://192.168.0.100:3306/Chat_App";
        Connection connection = DriverManager.getConnection(url,"root","password");
        String sender=message.getFrom();
        String content=message.getContent();
        Timestamp t1=message.getSentTime();
        Timestamp t2=message.getReceivedTime();
        Timestamp t3=message.getSeenTime();
        int valid;
        Timestamp time;
        if(t1==null&&t2==null)
        {
            valid=2;
            time=t3;//Seen Time
        }
        if(t1==null&&t3==null)
        {
            valid=1;
            time=t2;//Receive Time
        }
        else
        {
            valid=0;
            time=t1;//Sent Time
        }
        String query1 = "INSERT INTO ReceivedTable VALUES (?, ?, ?, ?)";
        PreparedStatement preStat = connection.prepareStatement(query1);
        preStat.setString(1,sender);
        preStat.setInt(2, valid);
        preStat.setString(3,content);
        preStat.setTimestamp(4,time);
        preStat.executeUpdate();
    }
}
