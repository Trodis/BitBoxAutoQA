package qa.bitbox.standalone;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.sikuli.script.App;
import org.sikuli.script.Screen;

import qa.bitbox.bitboxhandler.BitBoxRule;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class testDownload
{
    private App bitbox;
    private Screen scr;

    @Rule
    public BitBoxRule bitBoxRule = new BitBoxRule();

    @Before
    public void initReferences()
    {
        this.bitbox = bitBoxRule.getBitBox();
        this.scr = bitBoxRule.getScreen();
    }

    @Test
    public void testDownloadAllow() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }

    @Test
    public void testDownloadDeny() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }

    @Test
    public void testDownloadAskUser() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }
}
