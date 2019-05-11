import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReciever implements Runnable
{
    public ChatWindowController controller;
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

            }
        }
    }

}
