import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.*;

public class ClientHandler implements Runnable,Serializable
{
    Socket sc;
    Server server;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    MessageManager msh;
    String username, password;

    public ClientHandler(Socket so, Server ss, MessageManager ms)
    {
        sc = so;
        server=ss;
        msh=ms;
    }

    public Socket find(String sender)
    {
        int flag=0;
        Socket temp=null;
        for (Pair<String, Socket> value : server.activelist)
        {
            String check = value.getKey();
            if (check == sender)
            {
                temp = value.getValue();
                flag = 1;
                break;
            }
            System.out.print(value);
        }
        return temp;
    }

    public void run()
    {
        Object obj = null;
        try
        {
            ois = new ObjectInputStream(sc.getInputStream());
            oos = new ObjectOutputStream(sc.getOutputStream());
            obj = ois.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        if (obj instanceof user)
        {
            user temp = (user) obj;
            username = temp.username;
            password = temp.password;
            try {
                if (authenticate())
                {
                    msh.remove(sc,username);
                    while (true)
                    {
                        ois = new ObjectInputStream(sc.getInputStream());
                        obj = ois.readObject();
                        Message ms = (Message) obj;
                        String receirver = ms.getTo();
                        Socket receiver=find(receirver);
                        if (receiver!=null)// IF USER IS ACTIVE
                        {
                            System.out.println("User is Active");
                            ms.setReceivedTime(ms.getSentTime());
                            ms.setSeenTime(ms.getSentTime());
                            oos=new ObjectOutputStream(receiver.getOutputStream());
                            oos.writeObject(ms);
                            oos.flush();
                            oos.close();
                        }
                        else
                        {
                            msh.insert(ms);
                        }
                    }
                }
                else
                {
                    String mess = "Invalid Username";
                    oos.writeObject(mess);
                    oos.flush();
                    oos.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean authenticate() throws ClassNotFoundException, SQLException //To authentication
    {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://http://192.168.0.100:3306/Chat_App";
        Connection connection = DriverManager.getConnection(url,"root","password");
        String query = "SELECT Password FROM User Table WHERE UserName='" + (username) + "'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        if (rs.next())
        {
            String CheckPassword = rs.getString("Password");
            if (CheckPassword.equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            return false;
    }
}
