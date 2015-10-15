package qa.bitbox;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.sikuli.basics.Settings;
import org.sikuli.script.ImagePath;
import qa.bitbox.standalone.StandaloneTestSuite;
import qa.bitbox.bitboxhandler.TestEnvironment;

public class StandaloneTestSuitRunner
{
    public static void main(String[] args)
    {
        TestEnvironment.createTestResultFolder();
        Result standalone_result = JUnitCore.runClasses(StandaloneTestSuite.class);
        Settings.TypeDelay = 2;
        Settings.ClickDelay = 3;

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


        for (Failure fail : standalone_result.getFailures())
        {
            System.out.println(fail.toString());
        }
        System.out.println(standalone_result.wasSuccessful());
    }
}
