package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.testcasehandler.ExceptionHandling;


public class testStartQuit
{
    private Region rg;
    private Screen scr;
    private App bitbox;

    @Before
    public void init() throws FindFailed, InterruptedException
    {
        ImagePath.setBundlePath("images");
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
    public void testStartAndQuitWithKey() throws FindFailed, InterruptedException {
        try
        {
            assertTrue(scr.wait("firefox_menu_button.PNG", 120) != null);
            bitbox.focus();
            scr.type("q", KeyModifier.CTRL);
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName());
            System.out.println("Testcase: " + this.getClass().getSimpleName() + " FAILED!");
            clean();
        }
    }

    @Test
    public void testStartAndQuitWithQuitButton() throws FindFailed, InterruptedException
    {
        try
        {
            assertTrue(scr.wait("firefox_menu_button.PNG", 120) != null);
            assertTrue(scr.click("firefox_menu_button.PNG", 1) == 1);
            assertTrue(scr.click("firefox_quit_button.PNG", 1) == 1);
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName());
            System.out.println("Testcase: " + this.getClass().getSimpleName() + " FAILED!");
            clean();
        }
    }

    @Test
    public void testStartAndQuitWithXButton() throws FindFailed, InterruptedException
    {
        try
        {
            assertTrue(scr.wait("firefox_menu_button.PNG", 120) != null);
            assertTrue(scr.click("bitbox_X_button.png", 1) == 1);
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
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
