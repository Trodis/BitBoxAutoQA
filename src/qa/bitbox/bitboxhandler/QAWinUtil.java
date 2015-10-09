package qa.bitbox.bitboxhandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Trodis on 09.10.2015.
 */
public class QAWinUtil
{
    public static boolean isRunning(String process_name)
    {
        boolean found = false;
        try
        {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null)
            {
                if(line.split(",")[0].replace("\"", "").equals(process_name))
                {
                    found = true;
                }
            }
            input.close();
            return found;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return found;
    }

    public static int getPIDFromBitBox()
    {
        try
        {
            Process p = Runtime.getRuntime().exec("tasklist.exe /fi \"IMAGENAME eq VirtualBox.exe\" /fi " +
                    "\"MEMUSAGE gt 10000\" /fo csv /nh");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            return Integer.parseInt(input.readLine().split(",")[1].replace("\"", ""));
        }
        catch (IOException | IndexOutOfBoundsException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static void logoff()
    {
        try
        {
            Runtime.getRuntime().exec("logoff.exe");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
