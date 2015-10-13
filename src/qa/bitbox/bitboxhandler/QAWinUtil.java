package qa.bitbox.bitboxhandler;

import org.sikuli.script.App;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
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
        catch (IOException e)
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

    public static App getNotepad()
    {
        return new App("notepad.exe");
    }

    public static String getTextFromClipboard() throws IOException, UnsupportedFlavorException
    {
        Toolkit defaulttool = Toolkit.getDefaultToolkit();
        Clipboard systemclipboard = defaulttool.getSystemClipboard();

        Object clipboardtext = systemclipboard.getData(DataFlavor.stringFlavor);

        return (String) clipboardtext;
    }

    public static String[] getRegValue(String regpath)
    {
        try
        {
            Process p = Runtime.getRuntime().exec("reg.exe query " + regpath + " /v type");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String out;
            while((out = input.readLine()) != null)
            {
                if (out.trim().startsWith("type"))
                {
                    return out.trim().replaceAll("\\s+", " ").split(" ");
                }
                else
                {
                    continue;
                }
            }
        }
        catch (IOException | IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void setRegValue(String regpath, String value) throws IOException
    {
        Runtime.getRuntime().exec("runas.exe /u:administrator /savecred \"reg add " +
                regpath + " /f /v type /d " + value + "\"");
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
