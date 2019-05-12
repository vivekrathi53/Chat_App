import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;

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
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            if(obj instanceof Message)
            {
                Message temp = (Message)obj;

            }
            else if(obj instanceof SystemMessage)
            {

            }
        }
    }

}
