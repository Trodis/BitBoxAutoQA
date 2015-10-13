package qa.bitbox;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.sikuli.basics.Settings;
import qa.bitbox.standalone.StandaloneTestSuite;
import qa.bitbox.bitboxhandler.TestEnvironment;

public class StandaloneTestSuitRunner
{
    public static void main(String[] args)
    {
        TestEnvironment.createTestResultFolder();
        Result standalone_result = JUnitCore.runClasses(StandaloneTestSuite.class);
        Settings.TypeDelay = 2;

        for (Failure fail : standalone_result.getFailures())
        {
            System.out.println(fail.toString());
        }
        System.out.println(standalone_result.wasSuccessful());
    }
}
