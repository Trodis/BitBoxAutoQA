package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.omg.CORBA.Environment;
import org.sikuli.basics.Settings;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.testcasehandler.ExceptionHandling;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class testClipboard extends TestWatcher
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

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(scr);

    @Test
    public void testtextToGuestAllow() throws InterruptedException, IOException, UnsupportedFlavorException
    {
            Settings.TypeDelay = 2;
            App notepad = QAWinUtil.getNotepad();
            bitbox.focus();
            scr.type("l", Key.CTRL);
            scr.paste(Constants.CLIPBOARDTESTCONTENT_A);
            notepad.focus();
            scr.paste(Constants.CLIPBOARDTESTCONTENT_A);
            bitbox.focus();
            scr.type("l", Key.CTRL);
            scr.type("a", Key.CTRL );
            scr.type("c", Key.CTRL);
            assertFalse(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_A));
            notepad.close();
    }

}

