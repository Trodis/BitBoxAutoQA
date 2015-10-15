package qa.bitbox.standalone;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import qa.bitbox.bitboxhandler.BitBoxDownloadTestRule;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.testcasehandler.ExceptionHandling;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;



public class testDownload
{
    private App bitbox;
    private Screen scr;

    @Rule
    public BitBoxDownloadTestRule bitBoxDownloadTestRule = new BitBoxDownloadTestRule();

    @Before
    public void initReferences()
    {
        this.bitbox = bitBoxDownloadTestRule.getBitBox();
        this.scr = bitBoxDownloadTestRule.getScreen();
    }

    @Test
    public void testDownloadAllow() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        Settings.ClickDelay = 3;
        scr.type("l", Key.CTRL);
        scr.paste(Constants.EXTRASMALLFILE);
        scr.type(Key.ENTER);

        try
        {
            scr.wait("firefox_download_save_dialog.PNG", 10).click("firefox_download_save_dialog_ok_button.png");
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        File download = new File(Constants.DOWNLOADPATH + "5MB.zip");
        int counter = 1;
        boolean download_finished = download.exists();
        while (!download_finished && counter < 60)
        {
            download_finished = download.exists();
            Thread.sleep(1000);
            counter++;
        }

        assertTrue(download_finished);
        download.delete();
        bitbox.close();
    }


    /*
    @Test
    public void testDownloadDeny() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }
    */

    @Test
    public void testDownloadAskUserYES() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        scr.type("l", Key.CTRL);
        scr.paste(Constants.EXTRASMALLFILE);
        scr.type(Key.ENTER);

        try
        {
            scr.wait("firefox_download_save_dialog.PNG", 10).click("firefox_download_save_dialog_ok_button.png");
            scr.wait("bitbox_question_symbol.PNG", 10);
            scr.type("J");
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        File download = new File(Constants.DOWNLOADPATH + "5MB.zip");
        int counter = 1;
        boolean download_finished = download.exists();
        while (!download_finished && counter < 60)
        {
            download_finished = download.exists();
            Thread.sleep(1000);
            counter++;
        }

        assertTrue(download_finished);
        download.delete();
    }

    @Test
    public void testDownloadAskUserNO() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        scr.type("l", Key.CTRL);
        scr.paste(Constants.EXTRASMALLFILE);
        scr.type(Key.ENTER);

        try
        {
            scr.wait("firefox_download_save_dialog.PNG", 10).click("firefox_download_save_dialog_ok_button.png");
            scr.wait("bitbox_question_symbol.PNG", 10);
            scr.type("N");
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        File download = new File(Constants.DOWNLOADPATH + "5MB.zip");
        assertFalse(download.exists());
        download.delete();
    }
}
