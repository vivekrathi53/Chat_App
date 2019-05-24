import javafx.application.Platform;
import sun.net.ConnectionResetException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                temp.setReceivedTime(new Timestamp(System.currentTimeMillis()));//Receiver time is current time
                String q="INSERT INTO Local"+username+"Chats VALUES('"+(temp.getFrom())+"','"+(temp.getTo())+"','"+(temp.getContent())+"',"+(temp.getSentTime()==null?"null":("'"+temp.getSentTime()+"'"))+","+(temp.getReceivedTime()==null?"null":("'"+temp.getReceivedTime()+"'"))+","+(temp.getSeenTime()==null?"null":("'"+temp.getSeenTime()+"'"))+")";
                try {
                    PreparedStatement ps = connection.prepareStatement(q);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(temp.getFrom().equals(controller.currentUser.getText()))
                {

                        Platform.runLater(new Runnable()//To perform UI work from different Thread
                        {
                            @Override
                            public void run() {
                                System.out.println("Changing UI");
                                try{controller.addMessageToDisplay(temp);}
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                }

                controller.chats.add(temp);
                if(!controller.friends.contains(temp.getFrom()))
                {
                    Platform.runLater(new Runnable()//To perform UI work from different Thread
                    {
                        @Override
                        public void run() {
                            System.out.println(temp.getFrom());
                            controller.addChat(temp.getFrom());
                        }
                    });

                }

            }
            else if(obj instanceof SystemMessage)
            {
                SystemMessage temp = (SystemMessage) obj;
                if(temp.valid==1)// recieved Time LocalVivekTable for example tables are named with username in between
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
                        System.out.println("Received receiving time refreshing");
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
