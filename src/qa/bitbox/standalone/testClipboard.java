package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.Description;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;
import qa.bitbox.bitboxhandler.Constants;
import qa.bitbox.testcasehandler.ExceptionHandling;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class testClipboard
{
    private App bitbox;
    private Screen scr;


    @Rule
    public BitBoxClipboardTestRule bitBoxRule = new BitBoxClipboardTestRule();

    @Before
    public void initReferences()
    {
        this.bitbox = bitBoxRule.getBitBox();
        this.scr = bitBoxRule.getScreen();
    }

    @AfterClass
    public static void resetPolicy() throws IOException
    {
        BitBoxUtil.setPolicyTextToGuest(Constants.ALLOW);
        BitBoxUtil.setPolicyTextToHost(Constants.ALLOW);
    }

    @Test
    public void testTextToGuestAllow() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();
        scr.type("l", Key.CTRL);
        scr.paste(Constants.CLIPBOARDTESTCONTENT_A);
        notepad.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        bitbox.focus();
        scr.type("l", Key.CTRL);
        scr.type("a", Key.CTRL );
        scr.type("c", Key.CTRL);
        notepad.close();
        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_A));
    }

    @Test
    public void testTextToGuestDeny() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.paste(Constants.CLIPBOARDTESTCONTENT_A);

        try
        {
            scr.wait("bitbox_info_symbol.PNG", 5);
            scr.type(Key.ENTER);
            scr.waitVanish("bitbox_info_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        notepad.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        bitbox.focus();
        scr.type("l", Key.CTRL);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);
        notepad.close();

        assertTrue(Constants.CLIPBOARDTESTCONTENT_B.equals(QAWinUtil.getTextFromClipboard()));
    }

    @Test
    public void testTextToGuestAskUserYES() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.paste(Constants.CLIPBOARDTESTCONTENT_A);

        try
        {
            scr.wait("bitbox_question_symbol.PNG", 5);
            scr.type("J"); //Key: J
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        notepad.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.close();
        bitbox.focus();
        scr.type("l", Key.CTRL);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_A));
    }


    @Test
    public void testTextToGuestAskUserNO() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.paste(Constants.CLIPBOARDTESTCONTENT_A);

        try
        {
            scr.wait("bitbox_question_symbol.PNG", 5);
            scr.type("N"); //Key: N
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        notepad.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.close();
        bitbox.focus();
        scr.type("l", Key.CTRL);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_B));
    }

    @Test
    public void testTextToHostAllow() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.type(Constants.CLIPBOARDTESTCONTENT_A);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);
        notepad.focus();
        scr.type("v", Key.CTRL);
        bitbox.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.focus();
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        notepad.close();
        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_A));
    }

    @Test
    public void testTextToHostDeny() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.type(Constants.CLIPBOARDTESTCONTENT_A);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);
        notepad.focus();
        scr.type("v", Key.CTRL);

        try
        {
            scr.wait("bitbox_info_symbol.PNG", 5);
            scr.type(Key.ENTER);
            scr.waitVanish("bitbox_info_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        bitbox.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.focus();
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        notepad.close();
        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_B));
    }

    @Test
    public void testTextToHostAskUserYES() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.type(Constants.CLIPBOARDTESTCONTENT_A);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);
        notepad.focus();
        scr.type("v", Key.CTRL);

        try
        {
            scr.wait("bitbox_question_symbol.PNG", 5);
            scr.type("J"); //Key: J
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        bitbox.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.focus();
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        notepad.close();
        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_A));
    }

    @Test
    public void testTextToHostAskUserNO() throws InterruptedException, IOException, UnsupportedFlavorException
    {
        App notepad = QAWinUtil.getNotepad();

        scr.type("l", Key.CTRL);
        scr.type(Constants.CLIPBOARDTESTCONTENT_A);
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);
        notepad.focus();
        scr.type("v", Key.CTRL);

        try
        {
            scr.wait("bitbox_question_symbol.PNG", 5);
            scr.type("N");
            scr.waitVanish("bitbox_question_symbol.PNG", 5);
        }
        catch (FindFailed e)
        {
            ExceptionHandling.ImageNotFound(scr, e, this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        bitbox.focus();
        scr.paste(Constants.CLIPBOARDTESTCONTENT_B);
        notepad.focus();
        scr.type("a", Key.CTRL);
        scr.type("c", Key.CTRL);

        notepad.close();
        assertTrue(QAWinUtil.getTextFromClipboard().equals(Constants.CLIPBOARDTESTCONTENT_B));
    }

}

