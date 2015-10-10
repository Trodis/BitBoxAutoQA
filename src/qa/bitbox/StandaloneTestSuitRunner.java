package qa.bitbox;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.sikuli.natives.WinUtil;
import org.sikuli.script.App;
import org.sikuli.script.TextRecognizer;
import qa.bitbox.bitboxhandler.QAWinUtil;
import qa.bitbox.standalone.StandaloneTestSuite;
import qa.bitbox.bitboxhandler.TestEnvironment;

import java.util.Map;

/**
 * Created by Trodis on 08.10.2015.
 */
public class StandaloneTestSuitRunner
{
    public static void main(String[] args)
    {
        TestEnvironment.createTestResultFolder();
        Result standalone_result = JUnitCore.runClasses(StandaloneTestSuite.class);

        for (Failure fail : standalone_result.getFailures())
        {
            System.out.println(fail.toString());
        }
        System.out.println(standalone_result.wasSuccessful());
    }
}
