package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.basics.Settings;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.testcasehandler.ExceptionHandling;


public class testWindowsLogoffAbort
{
    private Screen scr;
    private App bitbox;

    @Before
    public void init() throws FindFailed, InterruptedException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        Settings.OcrTextSearch = true;
        System.out.println(OS);
        if (OS.equals("windows 7"))
        {
            ImagePath.setBundlePath("images/Windows7");
        }
        else if (OS.equals("windows 8.1"))
        {
            ImagePath.setBundlePath("images/Windows8");
        }
        scr = new Screen();

        //Start Sequence
        BitBoxUtil.startBitBox();
        assertTrue(QAWinUtil.isRunning("bitb.exe"));
        assertTrue(scr.wait("bitbox_status_bar.PNG", 120) != null);

        bitbox = new App(QAWinUtil.getPIDFromBitBox());
    }

    @After
    public void clean() throws InterruptedException {
        if(QAWinUtil.isRunning("bitb.exe"))
        {
            BitBoxUtil.stopBitBox();
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
        else
        {
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
    }

    @Test
    public void testLogoffAndAbort() throws FindFailed, InterruptedException {
        try
        {
            scr.wait("firefox_menu_button.PNG", 120);
            QAWinUtil.logoff();
            scr.click("windows_button_logoff_abort.PNG", 5);
            assertTrue(QAWinUtil.isRunning("bitb.exe"));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Testcase: " + Thread.currentThread().getStackTrace()[1].getMethodName() + " FAILED!");
            clean();
        }
    }

}

