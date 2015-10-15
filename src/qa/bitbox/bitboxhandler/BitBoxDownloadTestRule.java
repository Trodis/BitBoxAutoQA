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


public class BitBoxDownloadTestRule extends TestWatcher
{
    public Screen scr;
    public App bitbox;

    public BitBoxDownloadTestRule()
    {
        this.scr = new Screen();
    }

    @Override
    protected void starting(Description d)
    {
        scr.mouseMove(0,0);
        try
        {
            switch (d.getMethodName())
            {
                case "testDownloadAllow":
                    BitBoxUtil.setPolicyDownload(Constants.ALLOW);
                    break;
                case "testDownloadDeny":
                    BitBoxUtil.setPolicyDownload(Constants.DENY);
                    break;
                case "testDownloadAskUserYES":
                case "testDownloadAskUserNO":
                    BitBoxUtil.setPolicyDownload(Constants.ASKUSER);
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
