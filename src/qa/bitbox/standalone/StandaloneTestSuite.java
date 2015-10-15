package qa.bitbox.standalone;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        testStartQuit.class,
        testClipboard.class,
        testDownload.class
})
public class StandaloneTestSuite
{}
