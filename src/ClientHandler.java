import javafx.util.Pair;
import sun.net.ConnectionResetException;

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
    ObjectOutputStream oos2;
    ObjectInputStream ois;
    MessageManager msh;
    String username, password;
    Connection connection;

    public ClientHandler(Socket so, Server ss, MessageManager ms,ObjectOutputStream oos,ObjectInputStream ois,Connection connection)
    {
        sc = so;
        server=ss;
        msh=ms;
        this.oos=oos;
        this.ois=ois;
        this.connection=connection;
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

    public void run()
    {
        Object obj = null;
        try
        {
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
            try
            {
                if (authenticate())
                {
                    msh.oos=oos;
                    msh.remove(sc,username);
                    System.out.println("Fine");
                    while (true)
                    {
                        try {
                            obj = ois.readObject();
                        }catch (ConnectionResetException e)
                        {
                            e.printStackTrace();
                            break;
                        }
                        Message ms = (Message) obj;
                        String receirver = ms.getTo();
                        System.out.println("----------------"+receirver);
                        Socket receiver=find(receirver);
                        if (receiver!=null)// IF USER IS ONLINE
                        {
                            System.out.println("User is Active");
                            ms.setReceivedTime(ms.getSentTime());
                            ms.setSeenTime(ms.getSentTime());
                            oos2.writeObject(ms);
                            oos2.flush();
                        }
                        else// IF USER IS OFFLINE
                        {
                            msh.insert(receirver,ms);
                        }
                    }
                    ois.close();
                    oos.close();
                }
                else
                {
                    // Invalid authentication message already sent in Authenticate function itself
                }
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Signupclass temp=(Signupclass)obj;
            System.out.println("USER IS SIGN UP");
            try {
                msh.insertuser(temp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean authenticate() throws ClassNotFoundException, SQLException //To authentication
    {
        String query = "SELECT Password FROM UserTable WHERE UserName='" + (username) + "'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        if (rs.next())
        {
            String CheckPassword = rs.getString("Password");
            if (CheckPassword.equals(password))
            {
                Authentication auth = new Authentication(true,"Successful");
                try {
                    oos.writeObject(auth);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            else
            {
                Authentication auth = new Authentication(false,"Invalid Login Credientials");
                try {
                    oos.writeObject(auth);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        else
            return false;
    }
}
