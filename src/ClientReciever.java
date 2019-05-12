import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientReciever implements Runnable
{
    public ChatWindowController controller;
    Connection connection;
    ObjectInputStream ois;

    @Override
    public void run()
    {
        while(true)
        {
            Object obj=null;
            try
            {
                obj = ois.readObject();
            } catch (Exception e)
            {
                return ;
            }
            if(obj instanceof Message)
            {
                Message temp = (Message)obj;
                String q="INSERT INTO LocalChats VALUES('"+(temp.getFrom())+"','"+(temp.getTo())+"','"+(temp.getContent())+"','"+(temp.getSentTime())+"','"+(temp.getReceivedTime())+"','"+(temp.getSeenTime())+"')";
                try {
                    PreparedStatement ps = connection.prepareStatement(q);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(temp.getFrom().equals(controller.currentUser))
                {
                    try {
                        controller.addMessageToDisplay(temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                controller.chats.add(temp);
            }
            else if(obj instanceof SystemMessage)
            {
                SystemMessage temp = (SystemMessage) obj;
                if(temp.valid==1)// recieved
                {
                    String q="INSERT INTO LocalChats members (ReceivedTime) Values('"+temp.time+"') Where Receiver = '"+temp.sender+"' AND ReceiverTime = NULL";
                    try {
                        PreparedStatement ps = connection.prepareStatement(q);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                else if(temp.valid==2)// seen
                {
                    String q="INSERT INTO LocalChats members (SeenTime) Values('"+temp.time+"') Where Receiver = '"+temp.sender+"' AND SeenTime = NULL";
                    try {
                        PreparedStatement ps = connection.prepareStatement(q);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                controller.refresh();
            }
        }
    }

}
