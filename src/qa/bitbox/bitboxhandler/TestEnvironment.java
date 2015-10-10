package qa.bitbox.bitboxhandler;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Trodis on 08.10.2015.
 */
public class TestEnvironment
{
    public static void createTestCaseResultFolder(String testcase_name, String test_name)
    {
        File dir = new File(Constants.FAILEDPATH + "\\" + testcase_name + "\\" + test_name);
        if(dir.exists())
        {
            System.out.println("Directory: " + dir.getName() + " already exists!");
        }
        else
        {
            dir.mkdirs();
        }
    }

    public static void createTestResultFolder()
    {
        File dir = new File(Constants.FAILEDPATH);
        if(dir.exists())
        {
            System.out.println("Directory: " + dir.getName() + " already exists!");
        }
        else
        {
            dir.mkdir();
        }
    }
}
