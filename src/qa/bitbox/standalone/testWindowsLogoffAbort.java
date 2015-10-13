package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.testcasehandler.ExceptionHandling;


public class testWindowsLogoffAbort
{
    private Screen scr;
    private App bitbox;

    @Rule
    public BitBoxRule bitBoxRule = new BitBoxRule();


    @Test
    public void testLogoffAndAbort() throws FindFailed, InterruptedException
    {
        scr.wait("firefox_menu_button.PNG", 120);
        QAWinUtil.logoff();
        scr.click("windows_button_logoff_abort.PNG", 5);
        assertTrue(QAWinUtil.isRunning("bitb.exe"));
    }

}

