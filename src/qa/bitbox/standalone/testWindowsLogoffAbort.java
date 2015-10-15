package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;


public class testWindowsLogoffAbort
{
    private Screen scr;
    private App bitbox;

    @Rule
    public BitBoxGeneralTestRule bitBoxGeneralTestRule = new BitBoxGeneralTestRule();


    @Test
    public void testLogoffAndAbort() throws FindFailed, InterruptedException
    {
        scr.wait("firefox_menu_button.PNG", 120);
        QAWinUtil.logoff();
        scr.click("windows_button_logoff_abort.PNG", 5);
        assertTrue(QAWinUtil.isRunning("bitb.exe"));
    }

}

