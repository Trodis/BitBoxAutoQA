package qa.bitbox.bitboxhandler;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.sikuli.script.Screen;

/**
 * Created by Trodis on 10.10.2015.
 */
public class ScreenshotRule extends TestWatcher
{
    Screen scr;
    public ScreenshotRule(Screen scr)
    {
        this.scr = scr;
    }

    @Override
    protected void failed(Throwable e, Description d)
    {
        System.out.println(d.getClass().getSimpleName());
        System.out.println(e.getStackTrace().getClass().getSimpleName());
        System.out.println(e.getStackTrace().getClass().getEnclosingMethod().getName());
    }
}
