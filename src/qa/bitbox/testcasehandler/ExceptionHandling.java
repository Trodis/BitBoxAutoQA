package qa.bitbox.testcasehandler;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.bitboxhandler.TestEnvironment;

/**
 * Created by Trodis on 08.10.2015.
 */
public class ExceptionHandling
{
    public static void ImageNotFound(Screen scr, FindFailed e, String testcase_name)
    {
        TestEnvironment.createTestCaseResultFolder(testcase_name);
        scr.saveScreenCapture(Constants.FAILEDPATH+testcase_name, e.getMessage());
    }
}
