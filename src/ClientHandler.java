import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class ClientHandler implements Runnable {
    Socket sc;
    ServerSocket ss;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String username, password;

    public ClientHandler(Socket so,ServerSocket server) {
        sc = so;
        ss=server;
    }

    public void run() {
        Object obj = null;
        try {
            ois = new ObjectInputStream(sc.getInputStream());
            oos = new ObjectOutputStream(sc.getOutputStream());
            obj = ois.readObject();
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
                if (authenticate()) {
                    while (true)
                    {
                        ois = new ObjectInputStream(sc.getInputStream());
                        //int index = active.indexOf("4");
                        Messagemanager msh = new Messagemanager();
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
        String url = "jdbc:mysql://localhost:3306/";
        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT Password FROM UserTable WHERE UserName='" + (username) + "'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
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
