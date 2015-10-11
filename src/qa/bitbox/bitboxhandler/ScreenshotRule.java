package qa.bitbox.bitboxhandler;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.sikuli.script.Screen;
import qa.bitbox.testcasehandler.ExceptionHandling;

/**
 * Created by Trodis on 10.10.2015.
 */
public class ScreenshotRule extends TestWatcher
{
    Screen scr;
    public ScreenshotRule()
    {
        this.scr = new Screen();
    }

    @Override
    protected void failed(Throwable e, Description d)
    {
        ExceptionHandling.AssertionError(scr , d.getClassName(), d.getMethodName(), e.toString());
    }
}
