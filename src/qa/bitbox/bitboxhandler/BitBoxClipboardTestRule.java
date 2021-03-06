package qa.bitbox.bitboxhandler;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import qa.bitbox.testcasehandler.ExceptionHandling;

import java.io.IOException;

import static org.junit.Assert.assertFalse;


public class BitBoxClipboardTestRule extends TestWatcher
{
    public Screen scr;
    public App bitbox;

    public BitBoxClipboardTestRule()
    {
        this.scr = new Screen();
    }

    @Override
    protected void starting(Description d)
    {
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println(System.getProperty("os.name").toLowerCase());
        if (OS.equals("windows 7"))
        {
            ImagePath.setBundlePath("images/Windows7");
        }
        else if (OS.equals("windows 8.1"))
        {
            ImagePath.setBundlePath("images/Windows8");
        }

        try
        {
            switch (d.getMethodName())
            {
                case "testTextToGuestAllow":
                    BitBoxUtil.setPolicyTextToGuest(Constants.ALLOW);
                    BitBoxUtil.setPolicyTextToHost(Constants.ALLOW);
                    break;
                case "testTextToGuestDeny":
                    BitBoxUtil.setPolicyTextToGuest(Constants.DENY);
                    BitBoxUtil.setPolicyTextToHost(Constants.ALLOW);
                    break;
                case "testTextToGuestAskUserYES":
                case "testTextToGuestAskUserNO":
                    BitBoxUtil.setPolicyTextToGuest(Constants.ASKUSER);
                    BitBoxUtil.setPolicyTextToHost(Constants.ALLOW);
                    break;
                case "testTextToHostAllow":
                    BitBoxUtil.setPolicyTextToGuest(Constants.ALLOW);
                    BitBoxUtil.setPolicyTextToHost(Constants.ALLOW);
                    break;
                case "testTextToHostDeny":
                    BitBoxUtil.setPolicyTextToGuest(Constants.ALLOW);
                    BitBoxUtil.setPolicyTextToHost(Constants.DENY);
                    break;
                case "testTextToHostAskUserYES":
                case "testTextToHostAskUserNO":
                    BitBoxUtil.setPolicyTextToGuest(Constants.ALLOW);
                    BitBoxUtil.setPolicyTextToHost(Constants.ASKUSER);
                    break;
            }

            this.scr = new Screen();
            BitBoxUtil.startBitBox();
            this.scr.wait("bitbox_status_bar.PNG", 120);
            this.bitbox = new App(QAWinUtil.getPIDFromBitBox());
            this.bitbox.focus();
        }
        catch (FindFailed | IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed(Throwable e, Description d)
    {
        ExceptionHandling.AssertionError(this.scr, d.getClassName(), d.getMethodName(), e.toString());
    }

    @Override
    protected void finished(Description e)
    {
        if(QAWinUtil.isRunning("bitb.exe"))
        {
            try
            {
                BitBoxUtil.killBitBox();
            }
            catch (IOException | InterruptedException killFailed)
            {
                killFailed.printStackTrace();
            }
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
        else
        {
            assertFalse(QAWinUtil.isRunning("bitb.exe"));
        }
    }

    public App getBitBox()
    {
        return this.bitbox;
    }

    public Screen getScreen()
    {
        return this.scr;
    }
}
