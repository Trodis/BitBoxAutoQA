package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
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
        System.out.println(OS);
        if (OS.equals("windows 7"))
        {
            ImagePath.setBundlePath("images/Windows7");
        }
        else if (OS.equals("windows 8"))
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
            assertTrue(scr.wait("firefox_menu_button.PNG", 120) != null);
            bitbox.focus();
            QAWinUtil.logoff();
            assertTrue(scr.wait("windows_button_logoff_abort.PNG", 10).click() == 1);
            //assertTrue(scr.wait("bitbox_ch"));
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName());
            System.out.println("Testcase: " + this.getClass().getSimpleName() + " FAILED!");
            clean();
        }
    }

}

