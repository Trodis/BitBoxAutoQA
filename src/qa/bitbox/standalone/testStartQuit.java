package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.testcasehandler.ExceptionHandling;


public class testStartQuit
{
    private Screen scr;
    private App bitbox;

    @Before
    public void init() throws InterruptedException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.equals("windows 7"))
        {
            ImagePath.setBundlePath("images//Windows7");
        }
        else if (OS.equals("windows 8.1"))
        {
            ImagePath.setBundlePath("images//Windows8");
        }

        //Start Sequence
        BitBoxUtil.startBitBox();
        if(QAWinUtil.isRunning("bitb.exe"))
        {
            scr = new Screen();
            try
            {
                scr.wait("bitbox_status_bar.PNG", 120);
            }
            catch (FindFailed e)
            {
                ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName(),
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                System.out.println("Testcase: " + Thread.currentThread().getStackTrace()[1]
                        .getMethodName() + " FAILED!");
                clean();
            }
                bitbox = new App(QAWinUtil.getPIDFromBitBox());
        }
        else
        {
            System.out.println(Constants.BITBOXSTARTERROR);
        }
    }

    @After
    public void clean() throws InterruptedException
    {
        if(QAWinUtil.isRunning("bitb.exe"))
        {
            BitBoxUtil.stopBitBox();
        }
        else
        {
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
    }

    @Test
    public void testStartAndQuitWithKey() throws InterruptedException {
        try
        {
            scr.wait("firefox_menu_button.PNG", 120);
            bitbox.focus();
            scr.type("q", KeyModifier.CTRL);
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Testcase: " + Thread.currentThread().getStackTrace()[1]
                    .getMethodName() + " FAILED!");
            clean();
        }
    }

    @Test
    public void testStartAndQuitWithQuitButton() throws InterruptedException
    {
        try
        {
            scr.wait("firefox_menu_button.PNG", 120);
            scr.click("firefox_menu_button.PNG", 1);
            scr.click("firefox_quit_button.PNG", 1);
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Testcase: " + Thread.currentThread().getStackTrace()[1]
                    .getMethodName() + " FAILED!");
            clean();
        }
    }

    @Test
    public void testStartAndQuitWithXButton() throws InterruptedException
    {
        try
        {
            scr.wait("firefox_menu_button.PNG", 120);
            scr.find("bitbox_window_decoration.png").click("decoration_x_button.png");
            assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Test: " + Thread.currentThread().getStackTrace()[1]
                    .getMethodName() + " FAILED!");
        }
    }


}
