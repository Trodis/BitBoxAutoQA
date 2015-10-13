package qa.bitbox.bitboxhandler;

import org.sikuli.natives.OSUtil;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

/**
 * Created by Trodis on 07.10.2015.
 */
public class BitBoxUtil
{
    public static void killBitBox() throws InterruptedException
    {
        App.open("C:\\Program Files (x86)\\Sirrix AG\\BitBox\\bin\\BitBoxClient.exe -q kill");
        boolean bitbox_isrunning = QAWinUtil.isRunning("bitb.exe");

        int counter = 0;
        while (bitbox_isrunning && counter < 5)
        {
            Thread.sleep(1000);
            counter++;
        }
    }

    public static void startBitBox() throws InterruptedException
    {

        App.open("C:\\Program Files (x86)\\Sirrix AG\\BitBox\\bin\\BitBoxClient.exe start");
        boolean bitbox_isrunning = QAWinUtil.isRunning("bitb.exe");

        int counter = 0;
        while (!bitbox_isrunning && counter < 5)
        {
            Thread.sleep(1000);
            bitbox_isrunning = QAWinUtil.isRunning("bitb.exe");
            counter++;
        }
    }

    public static void settRegValueTextToGuest(String value) throws IOException
    {
        QAWinUtil.setRegValue(Constants.SET_REGPOLICYTTG, value);
    }

    public static void setRegValueTextToHost(String value) throws IOException
    {
        QAWinUtil.setRegValue(Constants.SET_REGPOLICYTTH, value);
    }

    public static void setRegValueDownload(String value) throws IOException
    {
        QAWinUtil.setRegValue(Constants.SET_REGPOLICYDOWNLOAD, value);
    }

    public static void setRegValuePrint(String value) throws IOException
    {
        QAWinUtil.setRegValue(Constants.SET_REGPOLICYPRINT, value);
    }

    public static void setRegValueUpload(String value) throws IOException
    {
        QAWinUtil.setRegValue(Constants.SET_REGPOLICYUPLOAD, value);
    }

    public static String getRegValueTextToGuest()
    {
        String [] policy = QAWinUtil.getRegValue(Constants.GET_REGPOLICYTTG);
        return policy[policy.length-1];
    }

    public static String getRegValueTextToHost()
    {
        String [] policy = QAWinUtil.getRegValue(Constants.GET_REGPOLICYTTH);
        return policy[policy.length-1];
    }

    public static String getRegValueDownload()
    {
        String [] policy = QAWinUtil.getRegValue(Constants.GET_REGPOLICYDOWNLOAD);
        return policy[policy.length-1];
    }

    public static String getRegValuePrint()
    {
        String [] policy = QAWinUtil.getRegValue(Constants.GET_REGPOLICYPRINT);
        return policy[policy.length-1];
    }

    public static String getRegValueUpload()
    {
        String [] policy = QAWinUtil.getRegValue(Constants.GET_REGPOLICYUPLOAD);
        return policy[policy.length-1];
    }
}
