package qa.bitbox.testcasehandler;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.bitboxhandler.TestEnvironment;

public class ExceptionHandling
{
    public static void ImageNotFound(Screen scr, FindFailed e, String testcase_name, String testname)
    {
        TestEnvironment.createTestCaseResultFolder(testcase_name, testname);
        scr.saveScreenCapture(Constants.FAILEDPATH+"\\"+testcase_name+"\\"+testname, e.getMessage());
    }

    public static void BitBoxNotStopping(Screen scr, AssertionError e, String testcase_name, String testname)
    {
        TestEnvironment.createTestCaseResultFolder(testcase_name, testname);
        scr.saveScreenCapture(Constants.FAILEDPATH+testcase_name+testname, e.getMessage());
    }

    public static void AssertionError(Screen scr, String testcase_name, String testname, String error)
    {
        TestEnvironment.createTestCaseResultFolder(testcase_name, testname);
        scr.saveScreenCapture(Constants.FAILEDPATH + "\\" + testcase_name + "\\" + testname, error);
    }


}
