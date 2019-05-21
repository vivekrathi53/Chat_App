import javafx.application.Platform;
import sun.net.ConnectionResetException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientReceiver implements Runnable
{
    public ChatWindowController controller;
    Connection connection;
    ObjectInputStream ois;
    String username;
    @Override
    public void run()
    {
        while(true)
        {
            Object obj=null;
            try
            {
                obj = ois.readObject();
            }
            catch (ConnectionResetException e)
            {
                e.printStackTrace();
                break;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
            if(obj instanceof Message)
            {
                Message temp = (Message)obj;
                String q="INSERT INTO Local"+username+"Chats VALUES('"+(temp.getFrom())+"','"+(temp.getTo())+"','"+(temp.getContent())+"',"+(temp.getSentTime()==null?"null":("'"+temp.getSentTime()+"'"))+","+(temp.getReceivedTime()==null?"null":("'"+temp.getReceivedTime()+"'"))+","+(temp.getSeenTime()==null?"null":("'"+temp.getSeenTime()+"'"))+")";
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
                if(temp.valid==1)// recieved Time
                {
                    String q="UPDATE Local"+username+"Chats SET ReceivedTime = '"+temp.time+"' WHERE ReceivedTime=null AND Receiver='"+temp.sender+"'";
                    try {
                        PreparedStatement ps = connection.prepareStatement(q);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                else if(temp.valid==2)// seen Time
                {
                    String q="UPDATE Local"+username+"Chats SET SeenTime = '"+temp.time+"' WHERE    SeenTime=null AND Receiver='"+temp.sender+"'";
                    try {
                        PreparedStatement ps = connection.prepareStatement(q);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(new Runnable()//To perform UI work from different Thread
                {
                    @Override
                    public void run() {
                        controller.refresh();
                    }
                });


            }
            else if(obj instanceof  Errors)//When is user is invalid
            {
                Errors temp=(Errors) obj;
                System.out.println(temp.errormessage);
            }
        }

    }
}
