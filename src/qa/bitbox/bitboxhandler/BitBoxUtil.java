package qa.bitbox.bitboxhandler;

import org.sikuli.natives.OSUtil;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

/**
 * Created by Trodis on 07.10.2015.
 */
public class BitBoxUtil
{
    public static void stopBitBox() throws InterruptedException
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
}
