import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class ClientHandler implements Runnable {
    Socket sc;
    Server server;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Messagemanager msh;
    String username, password;

    public ClientHandler(Socket so,Server ss,Messagemanager ms) {
        sc = so;
        server=ss;
        msh=ms;
    }

    public void run() {
        Object obj = null;
        try {
            ois = new ObjectInputStream(sc.getInputStream());
            oos = new ObjectOutputStream(sc.getOutputStream());
            obj = ois.readObject();
            System.out.println("Reached");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (obj instanceof user) {
            user temp = (user) obj;
            username = temp.username;
            password = temp.password;
            try {
                if (authenticate())
                {
                    while (true) {
                        Socket receiver = null;
                        int flag = 0;
                        ois = new ObjectInputStream(sc.getInputStream());
                        obj = ois.readObject();
                        Message ms = (Message) obj;
                        String receirver = ms.getTo();
                        for (Pair<String, Socket> value : server.activelist)
                        {
                            String check = value.getKey();
                            if (check == receirver)
                            {
                                receiver = value.getValue();
                                flag = 1;
                                break;
                            }
                            System.out.print(value);
                        }
                        if (flag == 1)// IF USER IS ACTIVE
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
        String url = "jdbc:mysql://localhost:3306/Chat_App";
        Connection connection = DriverManager.getConnection(url,"root","password");
        String query = "SELECT Password FROM UserTable WHERE UserName='" + (username) + "'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        System.out.println("Authenticated");
        if (rs.next()) {
            String CheckPassword = rs.getString("Password");
            if (CheckPassword.equals(password)) {
                return true;
            } else {
                return false;
            }
        } else return false;

    }
}
